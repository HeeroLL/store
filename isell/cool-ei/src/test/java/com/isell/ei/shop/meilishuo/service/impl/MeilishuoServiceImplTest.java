package com.isell.ei.shop.meilishuo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;

public class MeilishuoServiceImplTest {
	
	 private static final String SEND_URL = "http://localhost:18080/bis/meilishuo";

	 /**
	@Test
	public void testGetAccessToken() {
		Map<String, String> paramMap = new HashMap<String, String>();
		String result = HttpUtils.httpPost(SEND_URL + "/getAccessToken", JsonUtil.writeValueAsString(paramMap));
		
		System.out.println(result); 
	}
	*/
	
	 
	@Test
	public void testGetMeilishuoOrder() {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("ctimeStart", "2015-10-01 00:00:00");
		paramMap.put("ctimeEnd", "2016-01-20 23:59:59");
		String result = HttpUtils.httpPost(SEND_URL + "/getMeilishuoOrder", JsonUtil.writeValueAsString(paramMap));
		
		System.out.println(result);
	}
	
	
	 /**
	@Test
	public void testGetMeilishuoLogisticsCompany() {
		Map<String, String> paramMap = new HashMap<String, String>();

		String result = HttpUtils.httpPost(SEND_URL + "/getMeilishuoLogisticsCompany", JsonUtil.writeValueAsString(paramMap));
		
		System.out.println(result);
	}
	*/

}
