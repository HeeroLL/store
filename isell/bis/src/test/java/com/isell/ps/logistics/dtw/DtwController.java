package com.isell.ps.logistics.dtw;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
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
		
	}
}
