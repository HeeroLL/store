package com.isell.ei.logistics.huitong.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ei.logistics.huitong.bean.Detail;
import com.isell.ei.logistics.huitong.bean.Order;
import com.isell.ei.logistics.huitong.bean.OrderDetail;

public class HuitongControllerTest {
	@Test
	public void testCreateMail(){
		OrderDetail od = new  OrderDetail();
		od.setItemNo("00020001");
		od.setNote("note1");
		od.setPrice(new BigDecimal(1));
		od.setQty(new BigDecimal(2));
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		list.add(od);
		Detail d = new Detail();
		d.setOrderDetail(list);
		Order o = new Order();
		o.setCurrency("人民币");
		o.setENo("6674849a332");
		o.setFreight(new BigDecimal(2));
		o.setInsuranceValue(new BigDecimal(2));
		o.setLogisticsEnt("YTO");
		o.setNote("2");
		o.setReAdress("朝阳");
		o.setReCity("北京市");
		o.setReDocId("610112198902193528");
		o.setRePhone("15881290134");
		o.setReName("张三");
		o.setReProv("北京市");
		o.setReZone("朝阳");
		o.setSeCountry("香港");
		o.setSeName("吕布");
		o.setTax(new BigDecimal(0));
		o.setTransportType("监管仓库");
		o.setDetail(d);
		String jsonObj = JsonUtil.writeValueAsString(o);
		System.out.println(jsonObj);
        String result = HttpUtils.httpPost("http://localhost:8080/bis/logistics/huitong/createBondOrMail", jsonObj);
		System.out.println(result);
	}
	
	@Test
	public void queryBondOrMail(){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("eNo", "873627292022280A");
		paramMap.put("method", "bbc.query");
		String result = HttpUtils.httpPost("http://localhost:8080/bis/logistics/huitong/queryBondOrMail", paramMap);
		System.out.println(result);
	}
	
	@Test
	public void queryStock(){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("eNo", "6674849a33");
		String result = HttpUtils.httpPost("http://localhost:8080/bis/logistics/huitong/queryStock", paramMap);
		System.out.println(result);
	}
}
