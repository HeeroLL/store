package com.isell.ei.idcard.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;

public class IdcardControllerTest {

	@Test
	public void testGetIdcardInfo() {
		Map<String, String> param = new HashMap<String, String>();
		//param.put("accessCode", "123");
		param.put("idCard", "42098419870420789");
		String result =  HttpUtils.httpPost("http://localhost:18080/bis/idcard/getIdcardInfo",param);
		System.out.println(result);
	}

}
