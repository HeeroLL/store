package com.isell.bis.order.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;

public class OrderControllerTest {
	
	@Test
	public void testTest(){
		CoolOrder order = new CoolOrder();
		CoolOrderItem i = new CoolOrderItem();
		i.setGg("aa");
		List<CoolOrderItem>  list = new ArrayList<CoolOrderItem>();
		list.add(i);
		order.setItemList(list);
		order.setId(159);
		String aaa = JsonUtil.writeValueAsString(order);
		System.out.println(aaa);
		String result = HttpUtils.httpPost("http://localhost:18080/bis/order/testaa",  JsonUtil.writeValueAsString(order));
        System.out.println("result=" + result);       
	}	
	
	//客户系统调用该接口获取运单相关信息
	@Test
	public void testGetWaybill() {
		   Map<String, String> paramMap = new HashMap<String, String>();
		   System.out.println(UUID.randomUUID().toString().replace("-", ""));
	       paramMap.put("service", "order_getWaybill");
	    //  paramMap.put("jsonObj", "{\"orderOldno\":\"CO201512151641101678\"}");
	        paramMap.put("jsonObj", "{\"beginTime\":\"20150730\",\"endTime\":\"20150730\",\"timeType\":\"1\"}");
	        paramMap.put("accessCode", "haiziwang");
	        paramMap.put("authCode", Coder.encodeMd5(paramMap.get("jsonObj")+"4yRa9Do4XLPkZN6h").toString());
	        //String result = HttpUtils.httpPost("http://localhost:8080/bis/gateway", paramMap);
	       String result = HttpUtils.httpPost("http://service.i-cooltest.com/gateway", paramMap);
	       // String result = HttpUtils.httpPost("http://127.0.0.1:8080/bis/gateway", paramMap);
	        System.out.println(result);
	}
	
	
	//客户系统调用该接口推送订单数据
	@Test
	public void testPushOrder() {
		   Map<String, String> paramMap = new HashMap<String, String>();
	        paramMap.put("service", "order_pushOrder");
	        CoolOrder co = new CoolOrder();
	        co.setAddress("21");
	        co.setLocationA("新北区");
	        co.setLocationC("常州");
	        co.setLocationP("江苏省");
	        co.setIdcard("513701199109084430");
	        co.setOrderOldno("1122234");
	        co.setLinkman("mao");
	        co.setZipcode("617000");
	        co.setMobile("18861264083");
	        co.setReturnUrl("http://192.168.1.10:8080/gateway");
	        co.setTotal(new BigDecimal(20));
	        co.setTradeNo("20");
	        co.setZffs(1);
	        CoolOrderItem item = new CoolOrderItem();
	       /* item.setGid(1287);
	        item.setCount(2);*/
	        item.setCode("0501000028");
	        item.setCount(5);
	        List<CoolOrderItem> items = new ArrayList<CoolOrderItem>();
	        items.add(item);
	        co.setItems(items);
	        String jsonObj = JsonUtil.writeValueAsString(co);
	        System.out.println(jsonObj);
	        paramMap.put("jsonObj", jsonObj);
	       // paramMap.put("jsonObj", JsonUtil.writeValueAsString(co));
	        paramMap.put("accessCode", "haiziwang");
	        paramMap.put("authCode", Coder.encodeMd5(paramMap.get("jsonObj") + "4yRa9Do4XLPkZN6h").toString());
	       String result = HttpUtils.httpPost("http://service.i-cooltest.com/gateway", paramMap);
	        //String result = HttpUtils.httpPost("http://localhost:8080/bis/gateway", paramMap);
	        //String result = HttpUtils.httpPost("http://127.0.0.1:8080/bis/order/pushOrder", paramMap);
	        System.out.println(result);
	}
	
	//客户系统调用该接口获取订单信息数据
	@Test
	public  void testGetOrderInfo() {
		  Map<String, String> paramMap = new HashMap<String, String>();
		   System.out.println(UUID.randomUUID().toString().replace("-", ""));
	       paramMap.put("service", "order_getOrderInfo");
	        paramMap.put("jsonObj", "{\"orderNo\":\"1122334455\"}");
	        paramMap.put("accessCode", "haiziwang");
	        paramMap.put("authCode", Coder.encodeMd5(paramMap.get("jsonObj")+"4yRa9Do4XLPkZN6h").toString());
	       // String result = HttpUtils.httpPost("http://127.0.0.1:8080/bis/order/getOrderInfo", paramMap);
	       String result = HttpUtils.httpPost("http://service.i-cooltest.com/gateway", paramMap);
	        System.out.println(result);
	}
	
	//订单变更
	@Test
	public void modifyOrder() {
		 	Map<String, String> paramMap = new HashMap<String, String>();
		   System.out.println(UUID.randomUUID().toString().replace("-", ""));
	       paramMap.put("service", "order_getWaybill");
	        CoolOrder order = new CoolOrder();
	        order.setOrderNo("1122334455");
	        order.setLocationP("江苏省");
	        order.setLocationC("常州市");
	        order.setLocationA("新北区");
	        order.setAddress("常高新");
	        order.setLinkman("毛伟杰");
	        order.setMobile("18861264083");
	        order.setRemark("要红色的");
	        order.setIdcard("513701199109084438");
	        order.setZipcode("617000");
	        String jsonObj =  JsonUtil.writeValueAsString(order);
	        paramMap.put("jsonObj", jsonObj);
	        paramMap.put("accessCode", "haiziwang");
	        paramMap.put("authCode", Coder.encodeMd5(paramMap.get("jsonObj")+"4yRa9Do4XLPkZN6h").toString());
	        String result = HttpUtils.httpPost("http://127.0.0.1:8080/bis/order/modifyOrder", paramMap);
	        System.out.println(result);
	}
	
	//确认订单
	@Test
	public  void testConfirmOrder() {
		  Map<String, String> paramMap = new HashMap<String, String>();
		   System.out.println(UUID.randomUUID().toString().replace("-", ""));
	       paramMap.put("service", "order_confirmOrder");
	        paramMap.put("jsonObj", "{\"orderOldno\":\"CO2015111109203879988\"}");
	        paramMap.put("accessCode", "haiziwang");
	        paramMap.put("authCode", Coder.encodeMd5(paramMap.get("jsonObj")+"4yRa9Do4XLPkZN6h").toString());
	        String result = HttpUtils.httpPost("http://127.0.0.1:8080/bis/order/confirmOrder", paramMap);
	        System.out.println(result);
	}
	
	/**
	@Test
	public void testTest(){
		CoolOrder order = new CoolOrder();
		CoolOrderItem i = new CoolOrderItem();
		i.setGg("aa");
		List<CoolOrderItem>  list = new ArrayList<CoolOrderItem>();
		list.add(i);
		order.setItemList(list);
		order.setId(159);
		String aaa = JsonUtil.writeValueAsString(order);
		System.out.println(aaa);
		String result = HttpUtils.httpPost("http://localhost:18080/bis/order/testaa",  JsonUtil.writeValueAsString(order));
        System.out.println("result=" + result);       
	}	
	
	@Test
	public void testGetSum(){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("selectType", "3");
		
		String result = HttpUtils.httpPost("http://localhost:8080/bis/order/getSumCoolOrderSales", JsonUtil.writeValueAsString(param));
        System.out.println("result=" + result);
	}

	@Test
    public void testUpdateCoolOrderDelivery(){
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("id", 400);
        param.put("fhfs", "20");
        
        String result = HttpUtils.httpPost("http://127.0.0.1:8080/bis/order/updateCoolOrderDelivery", JsonUtil.writeValueAsString(param));
        System.out.println("result=" + result);
    }
	
	@Test
	public void testImportCoolOrder() {
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("filePath", "d:/pdd1106.xls");
		 param.put("importType", "1");
		 param.put("arrears", 1);
		 
		 String result = HttpUtils.httpPost("http://localhost:18080/bis/order/importCoolOrder", JsonUtil.writeValueAsString(param));
		 System.out.println("result=" + result);
	}
	*/
}
