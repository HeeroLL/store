package com.isell.ei.shop.pinduoduo.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;

public class PinduoduoControllerTest {
    
    private static final String SEND_URL = "http://localhost:8080/bis/pinduoduo/orderGoodsInfo";
    
    @Test
    public void testmOrderSearch() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("mType", "mOrderSearch");
        paramMap.put("OrderStatus", "1"); //订单状态，状态有3种。1，表示已付款（含货到付款）；0，表示未付款；-1表示问题单 只能填1！
        
        String result = HttpUtils.httpPost(SEND_URL, JsonUtil.writeValueAsString(paramMap));
        System.out.println(result);
    }
    
    @Test
    public void testmOrderSearch2() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("mType", "mGetOrder");
        paramMap.put("OrderNO", "160304-15586694964"); // 外部订单号
        
        String result = HttpUtils.httpPost("http://service.i-cooltest.com/pinduoduo/orderGoodsInfo", JsonUtil.writeValueAsString(paramMap));
        System.out.println(result);
    }
    
    @Test
    public void testmGetOrder() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("mType", "mGetOrder");
        paramMap.put("OrderNO", "160304-33946906673"); // 外部订单号151231-22964254854 
        
        String result = HttpUtils.httpPost("http://service.i-cooltest.com/pinduoduo/getOrder", JsonUtil.writeValueAsString(paramMap));
        System.out.println(result);
    }
    
    @Test
    public void testmSndGoods() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("mType", "mSndGoods");
        paramMap.put("OrderNO", "151218-45326163336"); // 外部订单号
        paramMap.put("SndStyle", "韵达快运"); // 发货方式（中文 例：申通 圆通等）
        paramMap.put("BillID", "3100821617933"); // 货运单号
        
        String result = HttpUtils.httpPost(SEND_URL, JsonUtil.writeValueAsString(paramMap));
        System.out.println(result);
    }
}
