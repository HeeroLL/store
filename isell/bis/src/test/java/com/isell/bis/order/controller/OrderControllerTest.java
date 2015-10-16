package com.isell.bis.order.controller;

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

}
