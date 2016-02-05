package com.isell.ei.logistics.huitong.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.logistics.huitong.bean.CreateResponse;
import com.isell.ei.logistics.huitong.bean.Order;
import com.isell.ei.logistics.huitong.bean.QueryResponse;
import com.isell.ei.logistics.huitong.bean.StockResponse;
import com.isell.ei.logistics.huitong.service.HuitongService;

@RequestMapping("logistics/huitong")
@Controller
public class HuitongController {
	
	@Resource
	private HuitongService huitongService;
	
	//生成保税、直邮
	@RequestMapping("createBondOrMail")
	@ResponseBody
	public JsonData createBondOrMail(@RequestBody Order order,String method){
		CreateResponse response = huitongService.createMail(order,method);
		JsonData jsonData = new JsonData();
        jsonData.setData(response);
        return jsonData;
	}
	
	//查询保税、直邮
	@RequestMapping("queryBondOrMail")
	@ResponseBody
	public JsonData queryBondOrMail(String eNo,String method){
		QueryResponse response = huitongService.queryBondOrMail(eNo,method);
		JsonData jsonData = new JsonData();
		jsonData.setData(response);
        return jsonData;
	}
	
	@RequestMapping("queryStock")
	@ResponseBody
	public JsonData queryStock(String eNo){
		StockResponse response = huitongService.queryBondOrMail(eNo);
		JsonData jsonData = new JsonData();
		jsonData.setData(response);
        return jsonData;	
	}
}
