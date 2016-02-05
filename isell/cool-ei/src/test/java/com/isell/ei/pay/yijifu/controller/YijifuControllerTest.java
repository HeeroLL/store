package com.isell.ei.pay.yijifu.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;

public class YijifuControllerTest {
    
    private static final String host = "http://localhost:8080";
    
    private static final String PAY_URL = host + "/bis/pay/yijifu/webPay";
    
    private static final String ORDER_URL = host + "/bis/pay/yijifu/singlePaymentUpload";
    
    private static final String QUERY_URL = host + "/bis/pay/yijifu/realNameQuery";
    
    private static final String TRADEREFUND_URL = host + "/bis/pay/yijifu/tradeRefund";
    
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
    public void testTradeRefund() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("outOrderNo", "CO201601251345512366772");
        map.put("tradeNo", "20160125000046411661");
        map.put("refundReason", "退款");
        
        String result = HttpUtils.httpPost(TRADEREFUND_URL, JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
    
    @Deprecated
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
        map.put("realName", "金晓翔");
        map.put("certNo", "330719197912230033");
        
        String result = HttpUtils.httpPost(QUERY_URL, JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
    
    @Test
    public void testSinglePaymentUpload() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("tradeNo", "[\"20160119000041549024\"]"); // 易极付支付交易号
        map.put("outOrderNo", "CO201601191021276252416"); // 订单号
        map.put("eshopEntCode", "3117964017"); // 商户海关备案号
        map.put("eshopEntName", "上海艾售电子商务有限公司"); // 商户海关备案名称
        map.put("customsCode", "ZZ_4604"); // 易极付发送海关编码 ZZ_4604:郑州关区 NB_3100:宁波关区
        map.put("payerId", "320402198408273738"); // 支付人证件号码
        map.put("payerName", "李霖"); // 支付人姓名
        map.put("goodsAmount", "68.00"); // 支付金额
        map.put("ieType", "IMPORT"); // 进出口标示
        
        String result = HttpUtils.httpPost(ORDER_URL, JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
}
