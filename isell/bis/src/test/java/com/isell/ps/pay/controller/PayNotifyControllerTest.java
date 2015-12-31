package com.isell.ps.pay.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;

public class PayNotifyControllerTest {
    
    @Test
    public void testYijifuNotify() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderNo", "CO2015102915430814881446104698494");
        map.put("tradeNo", "20151029000096069862");
        map.put("notifyTime", "2015-10-29 15:43:33");
        map.put("resultCode", "EXECUTE_SUCCESS");
        map.put("sign", "fa07a43b4f8c034a2fd06eda134e88ca");
        map.put("resultMessage", "执行成功");
        map.put("outOrderNo", "CO201510291543081488");
        map.put("version", "2.0");
        map.put("protocol", "httpPost");
        map.put("accountDay", "19870613");
        map.put("service", "commonTradePay");
        map.put("success", "true");
        map.put("signType", "MD5");
        map.put("partnerId", "20140411020055684571");
        map.put("tradeType", "FASTPAYTRADE");
        
        String result = HttpUtils.httpPost("http://localhost:8080/bis/payNotify/yijifu", map);
        System.out.println(result);
    }
    
    @Test
    public void testEhkingNotify() {
        String json = "{\"completeDateTime\":\"2015-12-02 16:11:59\",\"hmac\":\"cbfb602c4d166a0c2d317e9fa1c47eaf\",\"merchantId\":\"120140230\",\"orderAmount\":\"1\",\"orderCurrency\":\"CNY\",\"remark\":\"\",\"requestId\":\"CO201512021609552409\",\"serialNumber\":\"50934a405307493bbc476b64440add89\",\"status\":\"SUCCESS\",\"totalRefundAmount\":\"0\",\"totalRefundCount\":\"0\"}";
        String result = HttpUtils.httpPost("http://localhost:8080/bis/payNotify/ehking", json);
        System.out.println(result);
    }
}
