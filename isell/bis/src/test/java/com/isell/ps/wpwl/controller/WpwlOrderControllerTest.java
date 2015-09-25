package com.isell.ps.wpwl.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;

/**
 *  杭州沃朴物联科技有限公司（防伪）商定的订单相关HTTP服务测试
 * 
 * @author lilin
 * @version [版本号, 2015年9月25日]
 */
public class WpwlOrderControllerTest {
    
    @Test
    public void testGetOrderByOrderNo() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("service", "wpwl_order_getOrderByOrderNo");
        paramMap.put("jsonObj", "{\"orderNo\":\"880350384879600241\"}");
        paramMap.put("accessCode", "wpwl");
        paramMap.put("authCode", Coder.encryptBASE64(Coder.encryptMD5(paramMap.get("jsonObj") + "82dd8b166b9748598076e3c2393386fc")));
        
        String result = HttpUtils.httpPost("http://localhost:8080/bis/gateway", paramMap);
        //String result = HttpUtils.httpPost("http://service.i-coolshop.com/gateway", paramMap);
        System.out.println(result);
    }
    
}
