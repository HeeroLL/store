package com.isell.ei.pay.weixin.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.DateUtil;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.Identities;
import com.isell.core.util.JaxbUtil;
import com.isell.core.util.JsonUtil;
import com.isell.ei.pay.weixin.bean.WeixinPayResultInfo;
import com.isell.ei.pay.weixin.service.WeixinPayService;
import com.isell.ei.pay.weixin.util.WeixinPayUtil;

/**
 * 微信支付测试类
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
public class WeixinPayControllerTest {
    
    private static final String host = "http://localhost:8080";
    
    private static final String URL = host + "/bis/pay/weixin/";
    
    @Test
    public void testUnifiedorder() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("body", "九朵云 马油");
        map.put("out_trade_no", "AYS001");
        map.put("total_fee", 10000);
        map.put("spbill_create_ip", "127.0.0.1");
        map.put("notify_url", host + "/bis/testPayResult");
        map.put("trade_type", "JSAPI");
        map.put("openid", "testOpenid");
        
        String result = HttpUtils.httpPost(URL + "unifiedorder", JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
    
    @Test
    public void testOrderquery() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("out_trade_no", "AYS001");
        
        String result = HttpUtils.httpPost(URL + "orderquery", JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
    
    @Test
    public void testCloseorder() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("out_trade_no", "AYS001");
        
        String result = HttpUtils.httpPost(URL + "closeorder", JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
    
    @Test
    public void testSendPayResult() {
        WeixinPayResultInfo payResultInfo = new WeixinPayResultInfo();
        payResultInfo.setReturnCode("SUCCESS");
        payResultInfo.setAppid(WeixinPayService.APPID);
        payResultInfo.setMchId(WeixinPayService.MCH_ID);
        payResultInfo.setNonceStr(Identities.uuid());
        payResultInfo.setResultCode("SUCCESS");
        payResultInfo.setOpenid("testOpenId");
        payResultInfo.setIsSubscribe("Y");
        payResultInfo.setTradeType("JSAPI");
        payResultInfo.setBankType("CMC");
        payResultInfo.setTotalFee(10000);
        payResultInfo.setCashFee(10000);
        payResultInfo.setTransactionId("testWeixinOrder");
        payResultInfo.setOutTradeNo("testXiaocoonOrder");
        payResultInfo.setTimeEnd(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
        payResultInfo.setAttach(host + "/bis/testPayResult");
        // 生成校验码
        payResultInfo.setSign(WeixinPayUtil.encryptString(WeixinPayUtil.generateSign(payResultInfo), WeixinPayService.KEY));
        
        String result = HttpUtils.httpPost(URL + "sendPayResult", JaxbUtil.convertToXml(payResultInfo), "application/xml");
        System.out.println(result);
    }
}