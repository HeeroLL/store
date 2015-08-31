package com.isell.ei.logistics.kuaidi100.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;

public class KuaidiControllerTest {
    
    private static final String host = "http://localhost:18080/bis";
    
    private static final String API_URL = host + "/logistics/kuaidi/jsonService";
    
    private static final String WEB_URL = host + "/logistics/kuaidi/webService";
    
    private static final String WAP_URL = host + "/logistics/kuaidi/wapService";
    
    @Test
    public void testJsonService() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("com", "yuantong");
        paramMap.put("nu", "200093247451");
        
        String result = HttpUtils.httpPost(API_URL, paramMap);
        System.out.println(result);
    }
    
    @Test
    public void testWebService() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("com", "yuantong");
        paramMap.put("nu", "200093247451");
        
        String result = HttpUtils.httpPost(WEB_URL, paramMap);
        System.out.println(result);
    }
    
    @Test
    public void testWapService() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("com", "yuantong");
        paramMap.put("nu", "200093247451");
        paramMap.put("callbackurl", "http://m.xiaocoon.com");
        
        String result = HttpUtils.httpPost(WAP_URL, paramMap);
        System.out.println(result);
    }
}
