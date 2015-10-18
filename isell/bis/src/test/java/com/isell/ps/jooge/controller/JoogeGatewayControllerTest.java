package com.isell.ps.jooge.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ps.jooge.bean.JoogeParam;

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
    
    private JoogeParam getParam(Map<String, Object> paramMap, String method) {
        JoogeParam param = new JoogeParam();
        param.setAppkey(APPKEY);
        param.setMethod(method);
        param.setTimestamp("2015-01-01 00:00:00");
        param.setV("1.0");
        
        param.setParam(JsonUtil.writeValueAsString(paramMap));
        StringBuilder paramStr = new StringBuilder();
        paramStr.append(APPSECRET); // 开头拼上密钥
        paramStr.append("appkey").append(param.getAppkey());
        paramStr.append("method").append(param.getMethod());
        paramStr.append("param").append(param.getParam());
        paramStr.append("timestamp").append(param.getTimestamp());
        paramStr.append("v").append(param.getV());
        paramStr.append(APPSECRET); // 结尾拼上密钥
        String sign = Coder.encodeMd5(paramStr.toString()).toLowerCase();
        param.setSign(sign);
        
        return param;
    }
    
    @Test
    public void testGetOrderList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("StartModified", "2015-10-12 16:00:00");
        paramMap.put("EndModified", "2015-10-13 00:00:00");
        paramMap.put("PageSize", 10);
        paramMap.put("PageNo", 1);
        JoogeParam param = getParam(paramMap, "order.list.get");
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/jooge/gateway", JsonUtil.writeValueAsString(param));
        
        System.out.println(result);
    }
    
    @Test
    public void testGetOrderDetail() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("Id", "CO201509190847162398");
        JoogeParam param = getParam(paramMap, "order.detail.get");
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/jooge/gateway", JsonUtil.writeValueAsString(param));
        
        System.out.println(result);
    }
    
    @Test
    public void testSendOrder() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("Id", "CO201509221113049241");
        paramMap.put("LogisticCompany", "shengtong");
        paramMap.put("LogisiticNumber", "testCode");
        JoogeParam param = getParam(paramMap, "order.send");
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/jooge/gateway", JsonUtil.writeValueAsString(param));
        
        System.out.println(result);
    }
    
    @Test
    public void testGetProductList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("StartModified", "2015-07-04 00:00:00");
        paramMap.put("EndModified", "2015-07-05 00:00:00");
        paramMap.put("PageSize", 10);
        paramMap.put("PageNo", 1);
        JoogeParam param = getParam(paramMap, "merch.list.get");
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/jooge/gateway", JsonUtil.writeValueAsString(param));
        
        System.out.println(result);
    }
    
    @Test
    public void testGetProductDetail() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("Id", "233");
        JoogeParam param = getParam(paramMap, "merch.detail.get");
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/jooge/gateway", JsonUtil.writeValueAsString(param));
        
        System.out.println(result);
    }
}
