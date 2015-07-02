package com.sell.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HttpUtilsTest {
    
    @Test
    public void testInstace() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("verifyCode", "gBWGwSga2cpavGJYg85e7Q==");
        paramMap.put("xml",
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Request lang=\"zh-CN\" service=\"RouteService\"><Head>BSPdevelop</Head><Body><RouteRequest method_type=\"1\" tracking_number=\"444029276937\" tracking_type=\"1\"/></Body></Request>");
        
        HttpUtils http = HttpUtils.getInstance(HttpUtils.HTTP_CONNECTION_TYPE_HTTPS);
        System.err.println(http.sendPost("https://bsp-oisp.test.sf-express.com/bsp-oisp/sfexpressService", paramMap));
        System.err.println(http.sendGet("https://www.baidu.com"));
        
        http.closeConn();
    }
    
    @Test
    public void testStatic() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("verifyCode", "gBWGwSga2cpavGJYg85e7Q==");
        paramMap.put("xml",
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Request lang=\"zh-CN\" service=\"RouteService\"><Head>BSPdevelop</Head><Body><RouteRequest method_type=\"1\" tracking_number=\"444029276937\" tracking_type=\"1\"/></Body></Request>");
        
        System.err.println(HttpUtils.httpsPost("https://bsp-oisp.test.sf-express.com/bsp-oisp/sfexpressService",
            paramMap));
        System.err.println(HttpUtils.httpsGet("https://www.baidu.com"));
    }
}
