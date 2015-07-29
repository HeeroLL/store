package com.sell.ei.weixinop.controller;

import org.junit.Test;

import com.sell.core.util.HttpUtils;

/**
 * 微信开放平台（公众号）测试
 * 
 * @author lilin
 * @version [版本号, 2015年7月29日]
 */
public class WeixinopControllerTest {
    
    @Test
    public void testGetWeixinConfig() {
        String result = HttpUtils.httpGet("http://localhost:8080/bis/weixin/getWeixinConfig?url=http://www.xiaocoon.com/weixinShareTest");
        System.out.println(result);
    }
    
}
