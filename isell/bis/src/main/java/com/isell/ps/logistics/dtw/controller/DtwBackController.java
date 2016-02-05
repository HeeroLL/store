package com.isell.ps.logistics.dtw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isell.core.util.JsonUtil;
import com.isell.ps.logistics.dtw.bean.OrderFollow;
import com.isell.ps.logistics.dtw.bean.Receipt;
import com.isell.ps.logistics.dtw.bean.Send;

@Controller
@RequestMapping("dtwBackController")
public class DtwBackController {
	//入库回执
	@RequestMapping("/getReceipt")
	public String  getReceipt(@RequestBody Receipt rec){
		String jsonObj = JsonUtil.writeValueAsString(rec);
		System.out.println(jsonObj);
		return jsonObj;
	}
	
	//出库回执
	@RequestMapping("sendOrDelivery")
	public String sendOrDelivery(@RequestBody Send sod){
		String jsonObj = JsonUtil.writeValueAsString(sod);
		System.out.println(jsonObj);
		return jsonObj;
	}
	
	//订单跟踪
	@RequestMapping("orderFollow")
	public String orderFollow(@RequestBody OrderFollow of){
		String jsonObj = JsonUtil.writeValueAsString(of);
		System.out.println(jsonObj);
		return jsonObj;
	}
}
