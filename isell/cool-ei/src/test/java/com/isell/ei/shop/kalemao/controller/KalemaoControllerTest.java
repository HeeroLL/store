package com.isell.ei.shop.kalemao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;

public class KalemaoControllerTest {

	@Test
	public void testPushOrder() {
		Map<String, String> paramMap = new HashMap<String, String>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        paramMap.put("order_sn", "123"); // 外部订单号
        paramMap.put("waybill_no", "234"); //货运单号
        paramMap.put("shipping_code","yto"); // 货运单号
        paramMap.put("shipping_name","圆通快递");//发货方式
        list.add(paramMap);
        String result = HttpUtils.httpPost("http://localhost:8080/bis/kalemao/upWayBillNo",
        		JsonUtil.writeValueAsString(list));
        System.out.println(result);
	}
}
