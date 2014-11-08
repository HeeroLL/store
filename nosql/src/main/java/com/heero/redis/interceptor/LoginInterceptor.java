package com.heero.redis.interceptor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.heero.redis.user.po.UserInfo;
import com.heero.redis.user.service.UserService;

/**
 * 登录拦截器，实现权限控制等功能
 * 
 * @author lilin
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * stringRedisTemplate
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * 用户业务接口
     */
    @Resource
    private UserService userService;
    
    /**
     * 定义忽略拦截的package列表
     */
    private List<String> ignorePackageList;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 忽略指定的包的url
        if (CollectionUtils.isNotEmpty(ignorePackageList)) {
            for (String pkg : ignorePackageList) {
                HandlerMethod method = (HandlerMethod) handler;
                if (method.getBean().getClass().getPackage().getName().startsWith(pkg)) {
                    return true;
                }
            }
        }
        // 权限判断
        if (request.getCookies() == null) {
            response.sendRedirect("/nosql/index.html");
            return false;
        }
        for (Cookie cookie : request.getCookies()) {
            if ("nosql.sessionid".equals(cookie.getName())) {
                // 从缓存中取session
                String onlineUserKey = "user:online:" + cookie.getValue();
                String userId = stringRedisTemplate.boundValueOps(onlineUserKey).get();
                if (userId != null) {
                    if (request.getSession().getAttribute("userInfo") == null) {
                        request.getSession().setAttribute("userInfo", userService.getUserInfo(userId));
                    }
                    
                    // 续期
                    stringRedisTemplate.expire(onlineUserKey, UserInfo.SESSION_TIME, TimeUnit.SECONDS);
                    // System.out.println("续期成功");
                    return true;
                }
            }
        }
        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
        }
        // System.out.println("in LoginInterceptor");
        response.sendRedirect("/nosql/index.html");
        return false;
    }

    public void setIgnorePackageList(List<String> ignorePackageList) {
        this.ignorePackageList = ignorePackageList;
    }
}
