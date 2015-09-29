package com.isell.ei.weixinop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.weixinop.bean.WeixinTocken;
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
    
    /**
     * 获取用户授权code
     * 
     * @param redirectUrl 重定向的url
     * @param map 返回值
     * @return 获取用户授权code的url
     */
    @RequestMapping("getAuthCode")
    public String getAuthCode(String redirectUrl, ModelMap map) {
        map.addAttribute("result", weixinopService.getAuthCode(redirectUrl));
        return "result";
    }
    
    /**
     * 根据用户授权code获取用户openid
     * 
     * @param code 用户授权code
     * @return 用户openid等信息
     */
    @RequestMapping("getOpenid")
    @ResponseBody
    public JsonData getOpenid(String code) {
        JsonData jsonData = new JsonData();
        WeixinTocken weixinTocken = weixinopService.getOpenid(code);
        jsonData.setData(weixinTocken);
        if (weixinTocken.getErrmsg() != null) {
            jsonData.setSuccess(false);
            jsonData.setMsg(weixinTocken.getErrmsg());
        }
        return jsonData;
    }
}
