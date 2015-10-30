package com.isell.ps.jooge.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;

/**
 * 珊瑚玉测试类
 * 
 * @author lilin
 * @version [版本号, 2015年10月16日]
 */
public class JoogeGatewayControllerTest {
    /**
     * 艾售KEY
     */
    private static final String APPKEY = "shanhuyun2isell";
    
    /**
     * 加密密钥
     */
    private static final String APPSECRET = "3e38778c37bb4558a3f38515d07e80fa";
    
    private Map<String, String> getParam(Map<String, Object> paramMap, String method) {
        Map<String, String> param = new HashMap<String, String>();
        
        param.put("param", JsonUtil.writeValueAsString(paramMap));
        
        param.put("appkey", APPKEY);
        param.put("method", method);
        param.put("timestamp", "2015-10-19 13:32:21");
        param.put("v", "1.0");
        
        StringBuilder paramStr = new StringBuilder();
        paramStr.append(APPSECRET); // 开头拼上密钥
        paramStr.append("appkey").append(param.get("appkey"));
        paramStr.append("method").append(param.get("method"));
        paramStr.append("param").append(param.get("param"));
        paramStr.append("timestamp").append(param.get("timestamp"));
        paramStr.append("v").append(param.get("v"));
        paramStr.append(APPSECRET); // 结尾拼上密钥
        String sign = Coder.encodeMd5(paramStr.toString()).toLowerCase();
        param.put("sign", sign);
        
        return param;
    }
    
    @Test
    public void testGetOrderList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("StartModified", "2015-09-09 00:00:00");
        paramMap.put("EndModified", "2015-09-10 00:00:00");
        paramMap.put("PageSize", 10);
        paramMap.put("PageNo", 1);
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/jooge/gateway", getParam(paramMap, "order.list.get"));
        
        System.out.println(result);
    }
    
    @Test
    public void testGetOrderDetail() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("Id", "CO201509221113049241");
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/jooge/gateway", getParam(paramMap, "order.detail.get"));
        
        System.out.println(result);
    }
    
    @Test
    public void testSendOrder() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("Id", "CO201509221113049241");
        paramMap.put("LogisticCompany", "中通速递");
        paramMap.put("LogisiticNumber", "testCode");
        
        String result = HttpUtils.httpPost("http://localhost:8080/bis/jooge/gateway", getParam(paramMap, "order.send"));
        
        System.out.println(result);
    }
    
    @Test
    public void testGetProductList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("StartModified", "2015-07-04 00:00:00");
        paramMap.put("EndModified", "2015-07-05 00:00:00");
        paramMap.put("PageSize", 10);
        paramMap.put("PageNo", 1);
        
        String result =
            HttpUtils.httpPost("http://service.i-coolshop.com/jooge/gateway", getParam(paramMap, "merch.list.get"));
        
        System.out.println(result);
    }
    
    @Test
    public void testGetProductDetail() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("Id", "233");
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/jooge/gateway", getParam(paramMap, "merch.detail.get"));
        
        System.out.println(result);
    }
}
