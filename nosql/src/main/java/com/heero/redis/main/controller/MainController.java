package com.heero.redis.main.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.heero.redis.main.service.MainService;

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
     * 主页
     * 
     * @param map 参数
     * @return 跳转到主页
     */
    @RequestMapping("index")
    public String index(ModelMap map) {
        map.addAttribute("onlineUser", mainService.getOnlineUser());
        return "main";
    }
}
