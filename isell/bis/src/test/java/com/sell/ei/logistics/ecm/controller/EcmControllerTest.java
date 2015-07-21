package com.sell.ei.logistics.ecm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.sell.core.util.Coder;
import com.sell.core.util.DateUtil;
import com.sell.core.util.HttpUtils;
import com.sell.core.util.JsonUtil;
import com.sell.ei.logistics.ecm.service.EcmService;
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
    
    @Test
    public void testSendCommodity() {
        EcmCommodities commodities = new EcmCommodities();
        EcmCommodity commodity = new EcmCommodity();
        commodity.setCommodityCode("15098765" + "0001");
        commodity.setCommodityName("九朵云 马油");
        commodity.setCommoditySpec("007"); // 个
        commodity.setUnit("007"); // 个
        commodity.setWeight(0.1); // 重量 KG
        commodity.setTradeCountryCode("133");
        commodity.setTradeCountryName("韩国");
        
        commodities.setCommoditys(new ArrayList<EcmCommodity>());
        commodities.getCommoditys().add(commodity);
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/logistics/ecm/sendCommodity",
                JsonUtil.writeValueAsString(commodities));
        System.out.println(result);
    }
    
    @Test
    public void testPushSaleOrder() {
        EcmOrders orders = new EcmOrders();
        EcmOrder order = new EcmOrder();
        order.setOrderCode("AYC001"); // 客户编码AYC
        order.setOrderDate(new Date());
        order.setReceiverName("李霖");
        order.setMobile("13512345678");
        order.setProvince("江苏省");
        order.setCity("常州市");
        order.setDistrict("天宁区");
        order.setReceiverAddress("朝阳新村10幢501");
        order.setReceiverZip("213000");
        order.setPayType("01");
        order.setPayCompanyCode("AYC");
        order.setPayNumber("lianlian154");
        order.setPaperNumber("320402198408273738");
        order.setOrderTotalAmount(100.00);
        order.setOrderGoodsAmount(100.00);
        order.setOrderTaxAmount(0.00);
        order.setFeeAmount(0.00);
        order.setTradeTime(new Date());
        order.setTotalAmount(100.00);
        order.setPurchaserId("21");
        order.setId("21");
        order.setName("李霖");
        order.setTelNumber("13512345678");
        order.setAddress("朝阳新村10幢501");
        
        EcmCommodity commodity = new EcmCommodity();
        // commodity.setWarehouseCode(null);
        commodity.setCommodityCode("15098765" + "0001");
        commodity.setCommodityName("九朵云 马油");
        commodity.setCommodityBarcode("1"); // 商品条形码
        commodity.setQty(1);
        commodity.setWeight(0.1);
        commodity.setTradePrice(100.00);
        commodity.setTradeTotal(100.00);
        commodity.setDeclPrice(100.00);
        commodity.setDeclTotalPrice(100.00);
        commodity.setCodeTs("09000000"); // 货主商品在海关备案的行邮税号
        
        order.setOrderDtls(new ArrayList<EcmCommodity>());
        order.getOrderDtls().add(commodity);
        
        orders.setOrders(new ArrayList<EcmOrder>());
        orders.getOrders().add(order);
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/logistics/ecm/pushSaleOrder",
                JsonUtil.writeValueAsString(orders));
        System.out.println(result);
    }
    
    @Test
    public void testSendOrderStatus() {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        String datetime = DateUtil.getCurrentDate("yyyyMMddHHmmss");
        
        paramMap.put("ip", EcmService.IP); // bis的外网ip
        paramMap.put("v", EcmService.V); // 接口版本号
        // paramMap.put("appKey", APP_KEY); // ECM给的key
        paramMap.put("sessionKey", EcmService.SESSION_KEY);
        paramMap.put("datetime", datetime);
        
        paramMap.put("sign",
            Coder.encodeMd5(EcmService.V + EcmService.IP + EcmService.SESSION_KEY + datetime + EcmService.APP_KEY)
                .toUpperCase()); // 校验码
        // paramMap.put("JSON_OBJ", Coder.encryptBASE64(JsonUtil.writeValueAsString(jsonObj))); // BASE64编码后的jsonObj
        
        String result = HttpUtils.httpPost("http://localhost:8080/bis/logistics/ecm/sendOrderStatus", paramMap);
        System.out.println(result);
    }
    
    @Test
    public void testSendShipOrder() {
        Map<String, String> paramMap = new HashMap<String, String>();
        String datetime = DateUtil.getCurrentDate("yyyyMMddHHmmss");
        
        paramMap.put("ip", EcmService.IP); // bis的外网ip
        paramMap.put("v", EcmService.V); // 接口版本号
        // paramMap.put("appKey", APP_KEY); // ECM给的key
        paramMap.put("sessionKey", EcmService.SESSION_KEY);
        paramMap.put("datetime", datetime);
        
        paramMap.put("sign",
            Coder.encodeMd5(EcmService.V + EcmService.IP + EcmService.SESSION_KEY + datetime + EcmService.APP_KEY)
                .toUpperCase()); // 校验码
        // paramMap.put("JSON_OBJ", Coder.encryptBASE64(JsonUtil.writeValueAsString(jsonObj))); // BASE64编码后的jsonObj
        
        String result = HttpUtils.httpPost("http://localhost:8080/bis/logistics/ecm/sendShipOrder", paramMap);
        System.out.println(result);
    }
}
