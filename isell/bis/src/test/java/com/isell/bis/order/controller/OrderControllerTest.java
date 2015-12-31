package com.isell.bis.order.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
