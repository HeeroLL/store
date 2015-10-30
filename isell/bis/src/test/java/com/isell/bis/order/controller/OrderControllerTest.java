package com.isell.bis.order.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.service.order.vo.CoolOrder;

public class OrderControllerTest {

	@Test
	public void testTest(){
		CoolOrder order = new CoolOrder();
		order.setId(159);
		
		String result = HttpUtils.httpPost("http://localhost:8080/bis/order/testaa", JsonUtil.writeValueAsString(order));
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
}
