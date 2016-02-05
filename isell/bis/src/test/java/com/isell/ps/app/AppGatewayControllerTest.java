package com.isell.ps.app;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;

public class AppGatewayControllerTest {
	
//	public static void main(String [] args){
//		System.out.println(Coder.encodeMd5("{\"mobile\":\"15861895565\"}7cc8b1e606a9439596483ee9655992ed"));
//	}

	@Test
	public void testGateway() {		
		//fail("Not yet implemented");
		//token:1b94d6e17d4747aaa81a6e8d253f62d2
		Map<String, String> param = new HashMap<String, String>();
		//param.put("accessCode", "123");
		param.put("service", "order_getOrderList");		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplier", "8661960c5021445b9b293e40d2bead21");	
//		paramMap.put("pName", "九朵云");	
//		paramMap.put("page",  1);
		paramMap.put("rows",  1000);
		paramMap.put("orderNo",  "222");
//		paramMap.put("stock", "1");
//		paramMap.put("name", "無條件de猪的海外店safsfb");
//		paramMap.put("page", 1);
//		paramMap.put("rows", 10);
//		paramMap.put("address", "太湖东路");
//		paramMap.put("name", "海");
//		paramMap.put("mobile", "12345678912");
//		paramMap.put("def", true);
//		paramMap.put("idcard", "121323");
//		paramMap.put("oldPssword", "4297f44b13955235245b2497399d7a93");
		param.put("jsonObj", JsonUtil.writeValueAsString(paramMap));
		
		//param.put("authCode", Coder.encodeMd5(param.get("jsonObj")+"1fca277c400d41afbef990a8c1da1163"));
		
		//String result =  HttpUtils.httpPost("http://service.i-cooltest.com/app/gateway",param);
		String result =  HttpUtils.httpPost("http://localhost:18080/bis/app/gateway",param);
		System.out.println(result);
		
	}
	
//	@Test
//	public void testGetToken() {
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("accessCode", "123");
//		 String result =  HttpUtils.httpPost("http://localhost:18080/bis/app/getToken",paramMap);
//		 System.out.println(result);		
//	}

}
