/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-6       sso登录控制层
 */
package com.zebone.btp.sso.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.sso.pojo.SsoAccountInfo;
import com.zebone.btp.sso.pojo.SysAccountInfo;
import com.zebone.btp.sso.pojo.SysRegInfo;
import com.zebone.btp.sso.service.SsoLoginService;
import com.zebone.btp.sso.service.SysRegService;
import com.zebone.btp.sso.util.UserNamePasswordUtil;

/**
 * sso登录控制层
 *
 * @author lilin
 * @version [版本号, 2013-1-6]
 */
@Controller
@RequestMapping("sso")
public class SsoLoginController
{
    /**
     * 单点登录账号登录service
     */
    @Autowired
    private SsoLoginService ssoLoginService;

    /**
     * 系统注册Service
     */
    @Autowired
    private SysRegService sysRegService;

    /**
     * 显示登录页面
     *
     * @param model model
     * @return url
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model)
    {
        return "sso/ssologin";
    }

    /**
     * 登录
     *
     * @param request request
     * @param username 用户名
     * @param password 密码
     * @return url
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    // @ResponseBody
    public String login(HttpServletRequest request, String username, String password)
    {
        // Map<String, Object> message = new HashMap<String, Object>();
        // 进行MD5加密
        password = new Md5Hash(password).toHex();

        SsoAccountInfo ssoAccountInfo = ssoLoginService.login(username, password);

        request.setAttribute("flag", "null");
        if (ssoAccountInfo == null)
        {
            request.setAttribute("flag", "false");
        }
        else
        {
            request.getSession().setAttribute("userInfo", ssoAccountInfo);
            request.setAttribute("flag", "true");
        }

        return "sso/ssologin";
        //
        // if (ssoAccountInfo == null)
        // {
        // message.put("msg", "用户名或者密码不正确");
        // message.put("success", false);
        // return message;
        // }
        //
        // request.getSession().setAttribute("userInfo", ssoAccountInfo);
        //
        // message.put("success", true);
        // return message;
    }

    /**
     * 跳转主页
     *
     * @param request request
     * @param modelMap modelMap
     * @return url
     */
    @RequestMapping("/home")
    public String login(HttpServletRequest request, ModelMap modelMap)
    {
        SsoAccountInfo ssoAccountInfo = (SsoAccountInfo)request.getSession().getAttribute("userInfo");

        if (ssoAccountInfo == null)
        {
            return "sso/ssologin";
        }

        // 获取该账号对应的所有子系统的列表
        List<SysRegInfo> sysRegInfoList = ssoLoginService.getSysAccountInfoList(ssoAccountInfo.getAccountId());
        modelMap.addAttribute("sysRegInfoList", sysRegInfoList);

        return "sso/syslist";
    }

    /**
     * 用户注销
     *
     * @param request request
     * @param modelMap modelMap
     * @return url
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, ModelMap modelMap)
    {
        SsoAccountInfo ssoAccountInfo = (SsoAccountInfo)request.getSession().getAttribute("userInfo");

        if (ssoAccountInfo == null)
        {
            return "sso/ssologin";
        }

        request.getSession().removeAttribute("userInfo");
        // 销毁session
        request.getSession().invalidate();

        // 获取该账号对应的所有子系统的列表
        List<SysRegInfo> sysRegInfoList = ssoLoginService.getSysAccountInfoList(ssoAccountInfo.getAccountId());
        modelMap.addAttribute("sysRegInfoList", sysRegInfoList);

        return "sso/logout";
    }

    /**
     * 跳转到子系统页面
     *
     * @param request request
     * @param regId 子系统主键
     * @param modelMap modelMap
     * @return url
     */
    @RequestMapping("/toSubSystem")
    public String toSubSystem(HttpServletRequest request, String regId, ModelMap modelMap)
    {
        SsoAccountInfo ssoAccountInfo = (SsoAccountInfo)request.getSession().getAttribute("userInfo");

        if (ssoAccountInfo == null)
        {
            return "sso/ssologin";
        }

        SysRegInfo sysRegInfo = sysRegService.findSysRegInfoById(regId);

        modelMap.addAttribute("sysRegInfo", sysRegInfo);

        return "sso/subsys";
    }

    /**
     * 返回加密后的用户名密码
     *
     * @param request request
     * @param sysId 系统标识
     * @return url
     */
    @RequestMapping("/submit")
    @ResponseBody
    public String submit(HttpServletRequest request, String sysId)
    {
        // 从session中获取用户信息
        SsoAccountInfo ssoAccountInfo = (SsoAccountInfo)request.getSession().getAttribute("userInfo");
        if (ssoAccountInfo == null)
        {
            return "error";
        }

        // 获取该子系统的用户信息
        SysAccountInfo sysAccountInfo = ssoLoginService.getSysAccountInfo(ssoAccountInfo.getAccountId(), sysId);
        if (sysAccountInfo == null)
        {
            return "error";
        }

        return UserNamePasswordUtil.generateTicket(sysAccountInfo.getAccount(), sysAccountInfo.getPassword());
    }
}
