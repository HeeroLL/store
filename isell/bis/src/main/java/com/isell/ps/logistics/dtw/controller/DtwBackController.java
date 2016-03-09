package com.isell.ps.logistics.dtw.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonUtil;
import com.isell.ps.logistics.dtw.bean.OrderFollow;
import com.isell.ps.logistics.dtw.bean.Receipt;
import com.isell.ps.logistics.dtw.bean.Send;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;

@Controller
@RequestMapping("dtwBackController")
public class DtwBackController {
	
	@Resource 
	private OrderService orderService;

	//入库回执
	@ResponseBody
	@RequestMapping("/getReceipt")
	public String  getReceipt(@RequestBody Receipt rec){
		String jsonObj = JsonUtil.writeValueAsString(rec);
		System.out.println(jsonObj);
		return jsonObj;
	}
	
	//出库回执
	@ResponseBody
	@RequestMapping("sendOrDelivery")
	public String sendOrDelivery(@RequestBody Send sod){
		String jsonObj = JsonUtil.writeValueAsString(sod);
		System.out.println(jsonObj);
		return jsonObj;
	}
	
	//订单跟踪
	@ResponseBody
	@RequestMapping("orderFollow")
	public String orderFollow(@RequestBody OrderFollow of){
		if(of!=null){
			CoolOrder coolOrder = new CoolOrder();
	        coolOrder.setOrderNo(of.getMsgid()); // 订单ID
	        coolOrder.setPsfs("大田物流");
	        coolOrder.setState(CoolOrder.ORDER_STATE_2); // 已发货
	        coolOrder.setFhfs(CoolOrder.FHFS_30); // 
	        coolOrder.setPsCode(of.getWayBill()); 
	        coolOrder.setUpdatetime(new Date());
	        orderService.updateOrder(coolOrder); // 更新订单信息		
	        return "1";
	        }
		else return "0";
	}
}
