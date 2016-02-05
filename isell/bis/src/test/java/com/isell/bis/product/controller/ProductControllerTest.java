package com.isell.bis.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;

public class ProductControllerTest {
	
	//客户系统调用该接口获取商品信息数据
	@Test
	public void testGetInfo() {
		    Map<String, String> paramMap = new HashMap<String, String>();
	        paramMap.put("service", "product_getInfo");
	       // paramMap.put("jsonObj", "{\"orderOldno\":\"CO201511021453314278\"}");
	         paramMap.put("jsonObj", "{\"id\":\"209\"}");
	        //paramMap.put("jsonObj", "{\"name\":\"面膜\"}");
	        paramMap.put("accessCode", "gykj");
	        paramMap.put("authCode", Coder.encodeMd5(paramMap.get("jsonObj") + "f415c035ad73444283c2576ac6d6b43e").toString());
	        
	        String result = HttpUtils.httpPost("http://127.0.0.1:8080/bis/gateway", paramMap);
	        System.out.println(result);
	}
	
	//获取商品库存信息数据
	@Test
	public void testGetStock() {
		  Map<String, String> paramMap = new HashMap<String, String>();
	        paramMap.put("service", "product_getStock");
	        paramMap.put("jsonObj", "{\"orderOldno\":\"CO201511021453314278\"}");
	        paramMap.put("jsonObj", "{\"code\":\"23,22\"}");
	        paramMap.put("accessCode", "gykj");
	        paramMap.put("authCode", Coder.encodeMd5(paramMap.get("jsonObj") + "f415c035ad73444283c2576ac6d6b43e").toString());
	        String result = HttpUtils.httpPost("http://127.0.0.1:8080/bis/gateway", paramMap);
	      //  String result = HttpUtils.httpPost("http://service.i-cooltest.com/gateway", paramMap);
	        System.out.println(result);	
	}
}
