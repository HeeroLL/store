package com.isell.ei.logistics.dtw.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.ei.logistics.dtw.bean.GainsRequest;
import com.isell.ei.logistics.dtw.bean.GainsResponse;
import com.isell.ei.logistics.dtw.bean.MultipleRequest;
import com.isell.ei.logistics.dtw.bean.MultipleResponse;
import com.isell.ei.logistics.dtw.bean.SendPersonalResponse;
import com.isell.ei.logistics.dtw.bean.SendPresonalInfo;
import com.isell.ei.logistics.dtw.bean.SendRequest;
import com.isell.ei.logistics.dtw.bean.SendResponse;
import com.isell.ei.logistics.dtw.service.DtwService;

@RequestMapping("logistics/dtw")
@Controller
public class DtwController {
	
	
	@Resource
	private DtwService dtwService;
	
	//入库
	@RequestMapping("getGainsOrder")
    @ResponseBody
	public GainsResponse getGainsOrder(@RequestBody GainsRequest cdr){
		return  dtwService.getGainsOrder(cdr);
	}
	
	//出库
	@RequestMapping("sendGoods")
	@ResponseBody
	public SendResponse sendGoods(@RequestBody SendRequest sr){
		return dtwService.sendGoods(sr);
	}
	
	@RequestMapping("multipleOrders")
	@ResponseBody
	public MultipleResponse multipleOrders(@RequestBody MultipleRequest multipleRequest)  {
		return dtwService.multipleOrders(multipleRequest);
	}
	
	@RequestMapping("sendPersonalInfo")
	@ResponseBody
	public SendPersonalResponse sendPersonalInfo(@RequestBody SendPresonalInfo sendPresonalInfo){
		return dtwService.sendPersonalInfo(sendPresonalInfo);
	}
}
