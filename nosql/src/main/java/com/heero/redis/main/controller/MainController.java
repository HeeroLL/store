package com.heero.redis.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录控制层
 * 
 * @author lilin
 */
@Controller
@RequestMapping("main")
public class MainController {
    /**
     * 主页
     * @return 跳转到主页
     */
    @RequestMapping("index")
    public String index() {
        return "main";
    }
}
