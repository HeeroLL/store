package com.sell.ei.logistics.ecm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.sell.core.util.Coder;
import com.sell.core.util.HttpUtils;
import com.sell.core.util.JsonUtil;
import com.sell.ei.logistics.ecm.vo.EcmCommodities;
import com.sell.ei.logistics.ecm.vo.EcmCommodity;
import com.sell.ei.logistics.ecm.vo.EcmOrder;
import com.sell.ei.logistics.ecm.vo.EcmOrders;

/**
 * 费舍尔ECM接口测试类
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class EcmControllerTest {
    // private static final String host = "http://121.40.79.3:8585";
    private static final String host = "http://localhost:18080/bis";
    
    private static final String URL = host + "/logistics/ecm/";
    
    @Test
    public void testSendCommodity() {
        EcmCommodity commodity = new EcmCommodity();
        commodity.setCommodityCode("150402010001");
        commodity.setCommodityName("Guerisson奇迹马油霜 70g");
        commodity.setCommoditySpec("罐");
        commodity.setUnit("122");
        commodity.setWeight(0.07);
        commodity.setTradeCountryCode("133");
        commodity.setTradeCountryName("韩国");
        
        EcmCommodities ecmCommodities = new EcmCommodities();
        ecmCommodities.setCommoditys(new ArrayList<EcmCommodity>());
        ecmCommodities.getCommoditys().add(commodity);
        
        String jsonObj = JsonUtil.writeValueAsString(ecmCommodities);
        String result = HttpUtils.httpPost(URL + "sendCommodity", jsonObj);
        System.out.println(result);
    }
    
    @Test
    public void testPushSaleOrder() {
        EcmOrders orders = new EcmOrders();
        EcmOrder order = new EcmOrder();
        order.setOrderCode("001"); // 客户编码AYC
        order.setOrderDate(new Date());
        order.setReceiverName("张三");
        order.setMobile("13512345678");
        order.setProvince("江苏省");
        order.setCity("常州市");
        order.setDistrict("天宁区");
        order.setReceiverAddress("朝阳新村10幢501");
        order.setReceiverZip("213000");
        order.setPayType("03");
        order.setPayNumber("lianlian154");
        order.setOrderTotalAmount(100.00);
        order.setOrderGoodsAmount(100.00);
        order.setOrderTaxAmount(0.00);
        order.setFeeAmount(0.00);
        order.setTradeTime(new Date());
        order.setTotalAmount(100.00);
        order.setPurchaserId("21");
        order.setId("21");
        order.setName("张三");
        order.setTelNumber("13512345678");
        order.setPaperType("01");
        order.setAddress("朝阳新村10幢501");
        
        EcmCommodity commodity = new EcmCommodity();
        commodity.setCommodityCode("150402010001");
        commodity.setCommodityName("Guerisson奇迹马油霜 70g");
        commodity.setCommodityBarcode("1"); // 商品条形码
        commodity.setQty(1);
        commodity.setWeight(0.07);
        commodity.setTradePrice(100.00);
        commodity.setTradeTotal(100.00);
        commodity.setDeclPrice(100.00);
        commodity.setDeclTotalPrice(100.00);
        commodity.setCodeTs("09000000"); // 货主商品在海关备案的行邮税号
        
        order.setOrderDtls(new ArrayList<EcmCommodity>());
        order.getOrderDtls().add(commodity);
        
        orders.setOrders(new ArrayList<EcmOrder>());
        orders.getOrders().add(order);
        
        String jsonObj = JsonUtil.writeValueAsString(orders);
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accessCode", "xiaocoon");
        paramMap.put("jsonObj", jsonObj);
        paramMap.put("authCode", Coder.encryptBASE64(Coder.encryptMD5(jsonObj + "4d64418014b240d99cb84e7e6cade671")));
        
        System.out.println(paramMap);
        String result = HttpUtils.httpPost(URL + "pushSaleOrder", paramMap);
        System.out.println(result);
    }
    
    @Test
    public void testSendOrderStatus() {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        paramMap.put("ip", "127.0.0.1"); // bis的外网ip
        paramMap.put("v", "1.0"); // 接口版本号
        // paramMap.put("appKey", APP_KEY); // ECM给的key
        paramMap.put("sessionKey", "2015AYCAV1");
        paramMap.put("datetime", "20150721141922");
        
        paramMap.put("sign", "5ABC0DD1C58794B36553C1DCC523D99F"); // 校验码
        paramMap.put("JSON_OBJ",
            "eyJzdGF0dXNMaXN0IjpbeyJsb2dpc3RpY3NOTyI6IiIsInN0YXR1cyI6W3sib3JkZXJNYWtlRGF0ZSI6bnVsbCwib3JkZXJDb21tZW50Ijoi55u05o6l5pS+6KGM77yM5Liq5Lq655Sz5oql5Y2V55Sz5oql5oiQ5YqfIiwib3JkZXJTdGF0dXMiOm51bGx9XSwib3JkZXJDb2RlIjoiQVlDMDAxIiwiY2FycmllciI6IiJ9XX0="); // BASE64编码后的jsonObj
        
        String result = HttpUtils.httpPost(URL + "sendOrderStatus", paramMap);
        System.out.println(result);
    }
    
    @Test
    public void testSendShipOrder() {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        paramMap.put("ip", "127.0.0.1"); // bis的外网ip
        paramMap.put("v", "1.0"); // 接口版本号
        // paramMap.put("appKey", APP_KEY); // ECM给的key
        paramMap.put("sessionKey", "2015AYCAV1");
        paramMap.put("datetime", "20150721143229");
        
        paramMap.put("sign", "E2F59FF455CBB92564BD80EA24CE6267"); // 校验码
        paramMap.put("JSON_OBJ",
            "eyJTSElQUE9SREVSUyI6W3sic2hpcHBpbmdUaW1lIjoiMjAxNS0wNy0yMSAxNDozMjoyOSIsImRldGFpbHMiOlt7ImNvbW1vZGl0eUFydE5vIjoiIiwiY29tbW9kaXR5U3BlYyI6IjAwNyIsInF0eSI6MS4wLCJjb21tb2RpdHlOYW1lIjoi5Lmd5py15LqRIOmprOayuSIsImNvbW1vZGl0eUNvZGUiOiIxNTA5ODc2NTAwMDEifSx7ImNvbW1vZGl0eUFydE5vIjoiMTUwOTg3NjUwMDAxIiwiY29tbW9kaXR5U3BlYyI6IjHnvZAiLCJxdHkiOjEuMCwiY29tbW9kaXR5TmFtZSI6IuS5neacteS6kSDpqazmsrkiLCJjb21tb2RpdHlDb2RlIjoiMTUwOTg3NjUwMDAxIn0seyJjb21tb2RpdHlBcnRObyI6IiIsImNvbW1vZGl0eVNwZWMiOiLmsLTmnpzlkbMiLCJxdHkiOjEuMCwiY29tbW9kaXR5TmFtZSI6IuS5neacteS6kSDpqazmsrkiLCJjb21tb2RpdHlDb2RlIjoiMTUwOTg3NjUwMDAxIn1dLCJvcmRlckNvZGUiOiJBWUMwMDEiLCJyZWFsV2VpZ2h0IjowLjEwLCJleHByZXNzTm8iOiIxMTQwNTI4NTYwNjA1IiwiY29tcGFueU5vIjoiRU1TIn1dfQ=="); // BASE64编码后的jsonObj
        
        String result = HttpUtils.httpPost(URL + "sendShipOrder", paramMap);
        System.out.println(result);
    }
    
    @Test
    public void testJson() {
        String s1 =
            "eyJzdGF0dXNMaXN0IjpbeyJsb2dpc3RpY3NOTyI6IiIsInN0YXR1cyI6W3sib3JkZXJNYWtlRGF0ZSI6bnVsbCwib3JkZXJDb21tZW50Ijoi55u05o6l5pS+6KGM77yM5Liq5Lq655Sz5oql5Y2V55Sz5oql5oiQ5YqfIiwib3JkZXJTdGF0dXMiOm51bGx9XSwib3JkZXJDb2RlIjoiQVlDMDAxIiwiY2FycmllciI6IiJ9XX0=";
        String s2 =
            "eyJTSElQUE9SREVSUyI6W3sic2hpcHBpbmdUaW1lIjoiMjAxNS0wNy0yMSAxNDozMjoyOSIsImRldGFpbHMiOlt7ImNvbW1vZGl0eUFydE5vIjoiIiwiY29tbW9kaXR5U3BlYyI6IjAwNyIsInF0eSI6MS4wLCJjb21tb2RpdHlOYW1lIjoi5Lmd5py15LqRIOmprOayuSIsImNvbW1vZGl0eUNvZGUiOiIxNTA5ODc2NTAwMDEifSx7ImNvbW1vZGl0eUFydE5vIjoiMTUwOTg3NjUwMDAxIiwiY29tbW9kaXR5U3BlYyI6IjHnvZAiLCJxdHkiOjEuMCwiY29tbW9kaXR5TmFtZSI6IuS5neacteS6kSDpqazmsrkiLCJjb21tb2RpdHlDb2RlIjoiMTUwOTg3NjUwMDAxIn0seyJjb21tb2RpdHlBcnRObyI6IiIsImNvbW1vZGl0eVNwZWMiOiLmsLTmnpzlkbMiLCJxdHkiOjEuMCwiY29tbW9kaXR5TmFtZSI6IuS5neacteS6kSDpqazmsrkiLCJjb21tb2RpdHlDb2RlIjoiMTUwOTg3NjUwMDAxIn1dLCJvcmRlckNvZGUiOiJBWUMwMDEiLCJyZWFsV2VpZ2h0IjowLjEwLCJleHByZXNzTm8iOiIxMTQwNTI4NTYwNjA1IiwiY29tcGFueU5vIjoiRU1TIn1dfQ==";
        String json1 = new String(Coder.decryptBASE64(s1));
        String json2 = new String(Coder.decryptBASE64(s2));
        System.out.println(json1);
        System.out.println(json2);
        
    }
}
