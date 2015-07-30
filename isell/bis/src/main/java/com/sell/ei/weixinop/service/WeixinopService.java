package com.sell.ei.weixinop.service;

import com.sell.ei.weixinop.bean.WeixinConfig;

/**
 * 微信开放平台（公众号）开发服务接口
 * 
 * @author lilin
 * @version [版本号, 2015年7月29日]
 */
public interface WeixinopService {
    /**
     * 微信公众账号ID
     */
    String APPID = "wxe863e749f59e9d7f";
    
    /**
     * 微信公众号密钥
     */
    String SECRET = "fa680c94cf63719cec68d369ccef2242";
    
    /**
     * 微信获取token的URL
     */
    String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID
        + "&secret=" + SECRET;
    
    /**
     * 获取ticket的URL
     */
    String GET_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";
    
    /**
     * 存放微信ticket的key
     */
    String WEIXIN_TICKET_KEY = "weixin_ticket_key";
    
    /**
     * 获取微信ticket
     * 
     * @return ticket
     */
    String getTicket();
    
    /**
     * 获取微信配置信息
     * 
     * @param url 请求微信接口的url
     * @return weixinConfig
     */
    WeixinConfig getWeixinConfig(String url);
}
