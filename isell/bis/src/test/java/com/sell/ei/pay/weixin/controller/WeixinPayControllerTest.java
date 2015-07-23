package com.sell.ei.pay.weixin.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.sell.core.util.DateUtil;
import com.sell.core.util.HttpUtils;
import com.sell.core.util.Identities;
import com.sell.core.util.JaxbUtil;
import com.sell.core.util.JsonUtil;
import com.sell.ei.pay.weixin.bean.PayResultInfo;
import com.sell.ei.pay.weixin.service.WeixinPayService;
import com.sell.ei.pay.weixin.util.WeixinUtil;

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
        map.put("body", "aaa");
        map.put("aody", "aac");
        map.put("cody", "aav");
        map.put("eody", "aab");
        map.put("dody", "aan");
        
        String result = HttpUtils.httpPost(URL + "unifiedorder", JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
    
    @Test
    public void testSendPayResult() {
        PayResultInfo payResultInfo = new PayResultInfo();
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
        payResultInfo.setSign(WeixinUtil.encryptString(WeixinUtil.generateSign(payResultInfo), WeixinPayService.KEY));
        
        String result = HttpUtils.httpPost(URL + "sendPayResult", JaxbUtil.convertToXml(payResultInfo), "application/xml");
        System.out.println(result);
    }
}
