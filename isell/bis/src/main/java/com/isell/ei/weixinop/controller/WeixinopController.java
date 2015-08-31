package com.isell.ei.weixinop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.web.JsonData;
import com.isell.ei.weixinop.service.WeixinopService;

/**
 * 微信开放平台(公众号)控制层
 * 
 * @author lilin
 * @version [版本号, 2015年7月29日]
 */
@Controller
@RequestMapping("weixin")
public class WeixinopController {
    
    /**
     * 微信开放平台（公众号）开发服务接口
     */
    @Resource
    private WeixinopService weixinopService;
    
    /**
     * 获取微信配置信息
     * 
     * @param url 请求微信接口的url
     * @return weixinConfig
     */
    @RequestMapping("getWeixinConfig")
    @ResponseBody
    public JsonData getWeixinConfig(String url) {
        JsonData jsonData = new JsonData();
        jsonData.setData(weixinopService.getWeixinConfig(url));
        return jsonData;
    }
}
