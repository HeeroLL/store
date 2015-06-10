package com.zebone.btp.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.Constant;
import com.zebone.btp.core.web.BaseController;
import com.zebone.btp.sso.util.UserNamePasswordUtil;

/**
 * 安全控制。主要负责登录、退出操作，及在线用户统计等。
 * 
 * @author 宋俊杰
 * 
 */
@Controller
public class SecurityController extends BaseController {
	// private static final Log log =
	// LogFactory.getLog(SecurityController.class);
	@Autowired
	private DefaultSessionManager defaultSessionManager;
	@Autowired
	private SecurityManager securityManager;

	/**
	 * 显示登录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showLoginForm(Model model) {
		return "login";
	}

	/**
	 * SSO单点登录系统入口（如果使用单点登录则摒弃原系统的页面登录）
	 * 
	 * @author lilin
	 * @date 2013-1-7
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @param ticket
	 *            ticket
	 */
	@RequestMapping(value = "ssoLogin", method = RequestMethod.POST)
	public void ssoLogin(HttpServletRequest request,
			HttpServletResponse response, String ticket) {
		String username = UserNamePasswordUtil.getUserName(ticket);
		String password = UserNamePasswordUtil.getPassword(ticket);

		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password, false);
		try {
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			throw new RuntimeException("用户名或者密码不正确", e);
		}
		// 得到当前访问用户的Session ID
		Serializable currentSessionId = SecurityUtils.getSubject().getSession()
				.getId();
		// 得到所有活动用户的Session
		Collection<Session> list = this.defaultSessionManager.getSessionDAO()
				.getActiveSessions();
		if (list != null) {
			// 根据当前登录的用户名从活动用户中查找，是否此用户已经登录过了，如果登录过，让先前登录的用户退出。
			for (Session session : list) {
				Serializable sessionId = session.getId();
				// 需要排除掉当前访问用户，
				if (!currentSessionId.equals(sessionId)) {
					Subject subject = new Subject.Builder(securityManager)
							.sessionId(sessionId).buildSubject();
					UserDetails userDetails = (UserDetails) subject
							.getPrincipal();
					if (userDetails != null
							&& userDetails.getLoginName().equals(username)) {
						subject.logout();
					}
				}
			}
		}
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		String skin = userDetails.getSkin();
		if (StringUtils.isBlank(skin)) {
			skin = Constant.DEFAULT_SKIN;
		}
		request.getSession().setAttribute("skin", skin);
		String ip = request.getRemoteAddr();
		// 将登陆成功用户的ip地址设置到登陆用户信息中。
		userDetails.setIp(ip);

		// 登录成功将请求转发到主页
		try {
			request.getRequestDispatcher("/home.zb").forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 具体执行登录操作的方法。如果用户已经在另外地方登录，先前的用户将被系统退出。
	 * 
	 * @param model
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> login(HttpServletRequest request,
			@RequestParam("username") String username,
			@RequestParam("password") String password, Model model) {
		Map<String, Object> message = new HashMap<String, Object>();
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			message.put("msg", "请填写用户名和密码");
		}

		// add by lilin 2013-1-8 begin
		// 采用程序手动加密（为了兼容SSO登录的方式）
		password = new Md5Hash(password).toHex();
		// add by lilin 2013-1-8 end
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password, false);
		try {
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			message.put("msg", "用户名或者密码不正确");
			message.put("success", false);
			return message;
		}
		// 得到当前访问用户的Session ID
		Serializable currentSessionId = SecurityUtils.getSubject().getSession()
				.getId();
		// 得到所有活动用户的Session
		Collection<Session> list = this.defaultSessionManager.getSessionDAO()
				.getActiveSessions();
		if (list != null) {
			/*
			 * 根据当前登录的用户名从活动用户中查找，是否此用户已经登录过了， 如果登录过，让先前登录的用户退出。
			 */
			for (Session session : list) {
				Serializable sessionId = session.getId();
				// 需要排除掉当前访问用户，
				if (!currentSessionId.equals(sessionId)) {
					Subject subject = new Subject.Builder(securityManager)
							.sessionId(sessionId).buildSubject();
					UserDetails userDetails = (UserDetails) subject
							.getPrincipal();
					if (userDetails != null
							&& userDetails.getLoginName().equals(username)) {
						subject.logout();
					}
				}
			}
		}
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		String skin = userDetails.getSkin();
		if (StringUtils.isBlank(skin)) {
			skin = Constant.DEFAULT_SKIN;
		}
		request.getSession().setAttribute("skin", skin);
		String ip = request.getRemoteAddr();
		// 将登陆成功用户的ip地址设置到登陆用户信息中。
		userDetails.setIp(ip);
		message.put("success", true);
		return message;
	}

	@RequestMapping("logout")
	@ResponseBody
	public Map<String, Object> logout() {
		SecurityUtils.getSubject().logout();
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("success", true);
		return message;
	}

	/**
	 * 得到当前活动用户数量。包括没有登录用户和登录用
	 * 
	 * @return 直接返回数量值。不跳转到页面
	 */
	@RequestMapping("activeUsersCount")
	public @ResponseBody
	int getActiveUsersCount() {
		Collection<Session> list = this.defaultSessionManager.getSessionDAO()
				.getActiveSessions();
		int onlineUserCount = 0;
		if (list != null) {
			onlineUserCount = list.size();
		}
		return onlineUserCount;
	}

	/**
	 * 得到当前已经登录的用户数量
	 * 
	 * @return 直接返回数量值。不跳转到页面
	 */
	@RequestMapping("loginedUserCount")
	public @ResponseBody
	int getLoginedUserCount() {
		Collection<Session> list = this.defaultSessionManager.getSessionDAO()
				.getActiveSessions();
		int loginedCount = 0;
		if (list == null) {
			return loginedCount;
		}
		for (Session session : list) {
			// 此Session Key 用作存储用户是否认证通过
			Boolean isAuthenticated = (Boolean) session
					.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
			if (isAuthenticated != null && isAuthenticated) {
				loginedCount++;
			}
		}
		return loginedCount;
	}

	@RequestMapping("securityTest")
	public String securityTest() {
		return "securityTest";
	}

}
