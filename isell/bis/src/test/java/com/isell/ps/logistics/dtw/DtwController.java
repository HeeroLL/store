package com.isell.ps.logistics.dtw;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ps.logistics.dtw.bean.OrderFollow;
import com.isell.ps.logistics.dtw.bean.OrderFollowItems;
import com.isell.ps.logistics.dtw.bean.Receipt;
import com.isell.ps.logistics.dtw.bean.ReceiptItems;

public class DtwController {
	
	
	@Test
	public void testReceipt(){
		Receipt r = new Receipt();
		r.setInputdate("string");
		r.setMsgackid("string");
		ReceiptItems ri = new ReceiptItems();
		ri.setMsgitem("string");
		ri.setPartname("string");
		ri.setPartno("string");
		ri.setQty("string");
		List<ReceiptItems> ris = new ArrayList<ReceiptItems>();
		ris.add(ri);
		r.setItems(ris);
		String jsonObj = JsonUtil.writeValueAsString(r);
		String result = HttpUtils.httpPost(
				"http://localhost:8080/bis/dtwBackController/getReceipt",
				jsonObj);
		System.out.println(result);
	}
	
	@Test
	public  void testOrderFollow(){
		OrderFollow of = new OrderFollow();
		of.setMsgid("CO2016022504191600154950");
		of.setWayBill("0000");
		OrderFollowItems ofi = new OrderFollowItems();
		ofi.setAcceptAddress("上海");
		ofi.setAcceptTime("2016-03-02 10:50:23");
		ofi.setScanPhone("15881290134");
		ofi.setScanPrincipal("02121");
		List<OrderFollowItems> list = new ArrayList<OrderFollowItems>();
		list.add(ofi);
		of.setItem(list);
		String jsonObj = JsonUtil.writeValueAsString(of);
		String result = HttpUtils.httpPost(
				"http://localhost:8080/bis/dtwBackController/orderFollow",
				jsonObj);
		System.out.println(result);
}
}
