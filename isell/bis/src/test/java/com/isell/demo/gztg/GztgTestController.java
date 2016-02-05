package com.isell.demo.gztg;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.core.util.JsonUtil;
import com.isell.demo.gztg.bean.Declaration;
import com.isell.demo.gztg.bean.EOrder;
import com.isell.demo.gztg.bean.EOrderGood;
import com.isell.demo.gztg.bean.EOrderGoods;
import com.isell.demo.gztg.bean.Head;
import com.isell.demo.gztg.bean.Manifest;
import com.isell.demo.gztg.bean.ResponseBody;
import com.isell.demo.gztg.bean.ResponseBodyList;
import com.isell.demo.gztg.bean.ResponseHead;
import com.isell.demo.gztg.bean.ResponseMessage;
import com.isell.demo.gztg.util.AESMessageSigner;

public class GztgTestController {

	@Test
	public void test() {
		EOrderGood eog = new EOrderGood();
		eog.setChildOrderNo("001");
		eog.setCustomsListNO("GDO51101408270000003");
		eog.setDeclTotal(2);
		eog.setDecPrice(1);
		eog.setgNo(1);
		eog.setgQty(2);
		eog.setNotes("0006");
		eog.setStoreRecordName("007");
		eog.setStoreRecordNo("0008");
		eog.setUnit("084");
		EOrder eo = new EOrder();
		eo.setEntRecordName("上海琨铭文化传播有限公司");
		eo.setEntRecordNo("IE150708758484");
		eo.setFreight(new BigDecimal(2));
		eo.setFreightCurr("110");
		eo.setiEFlag("I");
		eo.setNote("0006");
		eo.setOrderDate(new Date());
		eo.setOrderDocId("007");
		eo.setOrderDocType("01");
		eo.setOrderGoodTotal(new BigDecimal(1));
		eo.setOrderGoodTotalCurr("110");
		eo.setOrderId("33");
		eo.setOrderName("44");
		eo.setOrderName("55");
		eo.setOrderPhone("15881290134");
		eo.setOrderStatus("S");
		eo.setTax(2);
		eo.setTaxCurr("110");
		
		EOrderGoods egs = new EOrderGoods();
		List<EOrderGood> eOrderGood = new ArrayList<EOrderGood>();
		eOrderGood.add(eog);
		egs.seteOrderGood(eOrderGood);
		
		Declaration d = new Declaration();
		d.seteOrder(eo);
		d.seteOrderGoods(egs);
		
		Head h = new Head();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		h.setMessageID("880020"+date+"2346");
		h.setMessageType("880020");
		h.setSenderID("IE150708758484");
		h.setSendTime(new Date());
		h.setVersion("2");
		
		
		Manifest m = new Manifest();
		m.setHead(h);
		m.setDeclaration(d);
		 /* String xml = JaxbUtil.convertToXml(m);
		  System.out.println(xml);
		  AESMessageSigner as = new AESMessageSigner();
		  as.setKeyWord(" MYgGnQE2+DAS973vd1DFHg==");
		 String exml = as.encrypt(xml);
		  System.out.println(exml);*/
		String jsonObj = JsonUtil.writeValueAsString(m);
		System.out.println(jsonObj);
		
		
        String result = HttpUtils.httpPost("http://localhost:8080/bis/demo/gtzg/getGtzg", jsonObj);
        System.out.println(result);
	}
	
	@Test
	public void getResponse(){
		ResponseBody rb = new ResponseBody();
		rb.setDocumentNo("001");
		rb.setReturnInfo("002");
		rb.setReturnCode("003");
		ResponseBodyList rbl = new ResponseBodyList();
		List<ResponseBody> rb2 = new ArrayList<ResponseBody>();
		rb2.add(rb);
		rbl.setResponseBody(rb2);
		ResponseHead rh = new ResponseHead();
		rh.setAttachedFlag("1");
		rh.setMessageID("2");
		rh.setMessageType("2");
		rh.setMessageType("4");
		rh.setOldMessageId("4");
		rh.setReturnDate(new Date());
		rh.setAttachedFlag("2");
		ResponseMessage rm = new ResponseMessage();
		rm.setResponseBodyList(rbl);
		rm.setResponseHead(rh);
		
		String xml = JaxbUtil.convertToXml(rm);
		  System.out.println(xml);
		String jsonObj = JsonUtil.writeValueAsString(rm);
		System.out.println(jsonObj);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("messageId", "880020201512091743142346");
		String result = HttpUtils.httpPost("http://localhost:8080/bis/demo/gtzg/getResponse",paramMap);
		System.out.println(result);
	}
	
	@Test
	public void decrypt(){
		AESMessageSigner as = new AESMessageSigner();
		as.setKeyWord("MYgGnQE2+DAS973vd1DFHg==");
		String s = as.decrypt("HDvqi10LCBr5E/zf7Ls3mMc423JJ8Nua/i3gzAYTK7RSTPZ1xcJOf/I+wJcVUnhdK3KOu0cJpIaVsO3jpHwNK/mMcf1qdZKbY/CEswV/r9b0XRfYzHI8b64/BJ0TyydymJxfrW9x5cpv5Z5NbTTJsh3DtIZkVnXOd8uuWsCqAZ5MW6ewC+4Kuv7EW1q5ZSvbTnVXMg6aC8551HusZlT1tQo1xdB4BhmOQD05MSZfXMplItPy7aHTSTNzaWadcSJmGoR6x0+wTX5N1Sa9ih9UB/aETgcYsRZZrwNsPSEzliYP9PFLk6foNFs0xHMDqnUhB7swk2pKC/U1+Bz0ZRiGu0wh6WdDDP3Z9y80vWpOVpa8PD+LB+USxdK3/cA6fRZGLtko1OP5Tdew2nBHKQUwhr9W0eTnt6PMbCVM14nDhCc4duZsgIJS3WawF96nJbSn8noKpyVG3PWbSxbX661X754fhBML60+zA3/qbQsDtAcB3DV60Fp5cCTGAX68FxPf9mVrSDkq9ZNuQPEXtDbcAJP7xaVR7guB6UZB4exgYn4a8NVNmv5zddToBQHNfL02i0lEZh3B/9nIEtFqRbfw7Kryi4WhCOz6r7lqcz8nHFljZHNRMmJiFa2vYIanqO50Pdh3wFOPJFSZf+MUv5tY0ReubUMxO0hnrpik2xZC1KzUMIW9l5K3nh+nvJUTTzZiLA0qDE40idEepi3O+22+D1qL4ofL5+HILvb9557GTeO5Q17VqaM058KTaEZLVVDzHH9Lt2fhamCd41cb3crMyQ==");
		System.out.println(s);
	}
}
