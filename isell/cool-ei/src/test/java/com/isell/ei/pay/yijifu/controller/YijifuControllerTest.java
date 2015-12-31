package com.isell.ei.pay.yijifu.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;

public class YijifuControllerTest {
    
    private static final String host = "http://localhost:8080";
    
    private static final String PAY_URL = host + "/bis/pay/yijifu/webPay";
    
    private static final String ORDER_URL = host + "/bis/pay/yijifu/paymentBillV2Order";
    
    private static final String QUERY_URL = host + "/bis/pay/yijifu/realNameQuery";
    
    @Test
    public void testWebPay() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", "CO201510131317306109" + System.currentTimeMillis());
        map.put("goodsClauses", "[{\"name\":\"jiuduoyun\"}]");
        //map.put("goodsClauses", "jiuduoyun");
        map.put("tradeAmount", "0.01");
        map.put("outOrderNo", "CO201510131317306109");
        
        String result = HttpUtils.httpPost(PAY_URL, JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
    
    @Test
    public void testPaymentBillV2Order() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", "CO201510131317306110" + System.currentTimeMillis());
        map.put("outOrderNo", "CO201510131317306110"); // 外部订单号
        map.put("payerName", "张三"); // 支付人姓名
        map.put("payerId", "320401198405273212"); // 支付人证件号码
        map.put("goodsAmount", "100"); // 货款金额
        map.put("taxAmount", "0"); // 税款金额
        map.put("freightAmount", "5"); // 物流金额
        map.put("tradeNo", "asdjkfhadfjhewiou"); // 支付交易号
        map.put("paymentType", "WECHAT"); // 支付方式 ALIPAY:支付宝 WECHAT:微信支付
        
        String result = HttpUtils.httpPost(ORDER_URL, JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
    
    @Test
    public void testRealNameQuery() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("realName", "张三");
        map.put("certNo", "320402198408273737");
        
        String result = HttpUtils.httpPost(QUERY_URL, JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
}
