package com.heero.redis.main.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.heero.redis.main.service.MainService;
import com.heero.redis.user.service.UserService;

/**
 * 主页面控制层
 * 
 * @author lilin
 */
@Controller
@RequestMapping("main")
public class MainController {
    /**
     * 主页业务层接口
     */
    @Resource
    private MainService mainService;
    
    /**
     * 用户service层接口
     */
    @Resource
    private UserService userService;

    /**
     * 主页
     * 
     * @param session session
     * @param map 参数
     * @return 跳转到主页
     */
    @RequestMapping("index")
    public String index(HttpSession session, ModelMap map) {
        map.addAttribute("onlineUser", mainService.getOnlineUser());
        map.addAttribute("userInfo", userService.getUserInfoBySessionId((String)session.getAttribute("sid")));
        return "main";
    }
}
