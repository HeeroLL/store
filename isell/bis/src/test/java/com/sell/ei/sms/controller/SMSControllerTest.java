package com.sell.ei.sms.controller;

import org.junit.Test;

import com.sell.util.HttpUtils;

/**
 * SMS接口测试类
 * 
 * @author lilin
 * @version [版本号, 2015年7月6日]
 */
public class SMSControllerTest {
    
    @Test
    public void testSendMessage() {
        String json = "{\"to\":\"15951227110\",\"param\":\"【这只是一个测试！】\"}";
        String result = HttpUtils.httpPost("http://localhost:8080/bis/sms/sendMessage", json);
        System.out.println("result=" + result);
    }
    
}
