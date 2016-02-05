package com.isell.ei.logistics.chongqing.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.ei.logistics.chongqing.bean.Goods;
import com.isell.ei.logistics.chongqing.bean.Order;
import com.isell.ei.logistics.chongqing.bean.PayInfo;
import com.isell.ei.logistics.chongqing.service.ChongqingService;

@RequestMapping("logistics/chongqing")
@Controller
public class ChongqingController {
	
	@Resource
	private ChongqingService cqService;
	
	//订单
	@RequestMapping("sendOrder")
	@ResponseBody
	public String sendOrder(@RequestBody Order order){
		return cqService.sendOrder(order);
	}
	
	//商品备案
	@RequestMapping("goodsRecord")
	@ResponseBody
	public String goodsRecord(@RequestBody Goods goods){
		return cqService.goodsRecord(goods);
	}
	
	@RequestMapping("payInfo")
	@ResponseBody
	public String payInfo(@RequestBody PayInfo payInfo){
		return cqService.goodsRecord(payInfo);
	}
}
