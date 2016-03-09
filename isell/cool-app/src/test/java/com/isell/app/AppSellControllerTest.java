package com.isell.app;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;

public class AppSellControllerTest {

	@Test
	public void test() {
		Map<String, String> param = new HashMap<String, String>();
		
		param.put("service", "appservice_" + "getBannerPage");
		param.put("accessCode", "123");
		
		//param.put("jsonObj", "{\"bannerList\":[{\"content\":\"哈哈哈哈\",\"gId\":\"329\",\"imgs\":\"/member/7560/1455851246632.jpg,/member/7560/1455851249865.jpg\",\"mId\":\"7571\",\"oId\":\"29157\",\"scoreB\":\"5\",\"scoreD\":\"4\",\"scoreL\":\"4\",\"scoreP\":\"4\"}]}");
		
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
//		paramMap.put("id", 94);	
//		paramMap.put("page", 1);	
//		paramMap.put("rows", 10);
//		paramMap.put("shopCode",  "10000010");
//		paramMap.put("type",  "4");
//				paramMap.put("stock", "1");
//				paramMap.put("pName", "面膜");
//				paramMap.put("page", 1);
//				paramMap.put("rows", 10);
//				paramMap.put("address", "太湖东路");
//				paramMap.put("name", "海");
//				paramMap.put("mobile", "12345678912");
//				paramMap.put("def", true);
//				paramMap.put("idcard", "121323");
//				paramMap.put("oldPssword", "4297f44b13955235245b2497399d7a93");
		
		param.put("jsonObj", JsonUtil.writeValueAsString(paramMap));
		
		//param.put("authCode", Coder.encodeMd5(param.get("jsonObj")+"1fca277c400d41afbef990a8c1da1163"));
		
		//String result =  HttpUtils.httpPost("http://service.i-cooltest.com/app/gateway",param);
		String result =  HttpUtils.httpPost("http://localhost:18080/bis/app/iservice",param);
		System.out.println(result);
	}

}
