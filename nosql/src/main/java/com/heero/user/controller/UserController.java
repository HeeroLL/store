package com.heero.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heero.user.po.UserInfo;
import com.heero.user.service.UserService;
import com.heero.user.vo.ResultMessageVo;

/**
 * 用户登录控制层
 * 
 * @author lilin
 */
@Controller
@RequestMapping("user")
public class UserController {
    /**
     * 用户service层接口
     */
    @Resource
    private UserService userService;

    /**
     * 用户登录
     * 
     * @param userInfo 用户信息
     * @return 登录结果信息
     */
    @ResponseBody
    @RequestMapping("login")
    public ResultMessageVo login(UserInfo userInfo) {
        return userService.login(userInfo);
    }

    /**
     * 用户注册
     * 
     * @param userInfo 用户信息
     * @return 注册结果信息
     */
    @ResponseBody
    @RequestMapping("reg")
    public ResultMessageVo reg(UserInfo userInfo) {
        return userService.reg(userInfo);
    }
}
