package com.isell.ei.logistics.dtw.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ei.logistics.dtw.bean.GainsItem;
import com.isell.ei.logistics.dtw.bean.GainsRequest;
import com.isell.ei.logistics.dtw.bean.MultipleItems;
import com.isell.ei.logistics.dtw.bean.MultipleRequest;
import com.isell.ei.logistics.dtw.bean.SendItems;
import com.isell.ei.logistics.dtw.bean.SendPresonalInfo;
import com.isell.ei.logistics.dtw.bean.SendPresonalItems;
import com.isell.ei.logistics.dtw.bean.SendRequest;

public class DtwTestController {
	// private static final String host = "http://localhost:8080/bis";

	// private static final String URL = host + "/logistics/dtw/";

	@Test
	public void integratedOrder() throws UnsupportedEncodingException {
		MultipleRequest qo = new MultipleRequest();
		qo.setLogisCompanyCode("WL15041401");
		qo.setLogisCompanyName("百世物流科技（中国）有限公司");
		qo.setConsignee("杭买");
		qo.setConsigneeAdd("浙江杭州聚龙大厦");
		qo.setConsigneeCity("杭州");
		qo.setConsigneeCountry("中国");
		qo.setConsigneeDistrict("浙江");
		qo.setConsigneeMobile("15881290134");
		qo.setConsigneePro("浙江");
		qo.setConsigneeTel("08175544521");
		qo.setConsigneeZip("2345");
		qo.seteCommerceCode("093791963");
		qo.seteCommerceName("上海琨铭文化传播有限公司");
		qo.setLotNo("0");
		qo.setOrderNo("201601191650");
		qo.setMsgid("20150728000000001");
		qo.setNetWeight(new BigDecimal(2));
		qo.setPayCompanyCode("PTE51001409230000001");
		qo.setPayNumber("zhifu001");
		qo.setPayType("01");
		qo.setPurchaserId("202");
		qo.setShipper("1");
		qo.setShipperAddress("122");
		qo.setShipperCountry("中国");
		qo.setShipperAddress("江苏");
		qo.setShipperCity("常州");
		qo.setShipperDistrict("2");
		qo.setShipperMobile("15881200223");
		qo.setShipperTel("08175546366");
		qo.setShipperZip("000");
		qo.setOrderGoodsAmount("10");
		qo.setOrderTotalAmount("100");
		qo.setTotalAmount("10");
		qo.setTotalCount("10");
		MultipleItems qi = new MultipleItems();
		qi.setAmount("10");
		qi.setBatch("20152222");
		qi.setCurrency("80");
		qi.setQty("2");
		qi.setUnit("瓶");
		qi.setSupplier("US001");
		qi.setSpec("瓶");
		qi.setPartName("sss");
		qi.setPartno("2");
	
		List<MultipleItems> qs = new ArrayList<MultipleItems>();
		qs.add(qi);
		qo.setItems(qs);
		String json = JsonUtil.writeValueAsString(qo); 
		String result = HttpUtils.httpPost(
				"http://localhost:8080/bis/logistics/dtw/multipleOrders",json);
		System.out.println(result);
	}

	/**
	 * 
	 */
	@Test
	public void gainsRequest() {
		GainsRequest gr = new GainsRequest();
		gr.seteCommerceCode("093791963");
		gr.seteCommerceName("上海琨铭文化传播有限公司");
		gr.setMsgid("20150728000000001");
		gr.setHawb("2");
		gr.setMawb("1");
		gr.setSupplier("US001");
		GainsItem qi = new GainsItem();
		qi.setAmount("2");
		qi.setBatch("20152222");
		qi.setCurrency("80");
		qi.setQty("2");
		qi.setUnit("瓶");
		qi.setSpec("瓶");
		qi.setPartno("2");
		qi.setPartName("sss");
		qi.setHsCode("1901109000");
		qi.setOriginCountry("100");
		List<GainsItem> qs = new ArrayList<GainsItem>();
		qs.add(qi);
		gr.setItems(qs);
		String jsonObj = JsonUtil.writeValueAsString(gr);
		String result = HttpUtils.httpPost(
				"http://localhost:8080/bis/logistics/dtw/getGainsOrder",
				jsonObj);
		System.out.println(result);
	}

	@Test
	public void sendGoods(){
		SendRequest sr = new SendRequest();
		sr.setConsignee("杭买");
		sr.setConsigneeAdd("浙江杭州聚龙大厦");
		sr.setConsigneeTel("08175544521");
		sr.seteCommerceCode("328312788");
		sr.seteCommerceName("上海琨铭文化传播有限公司");
		sr.setMsgid("20150728000000001");
		sr.setOrderGoodsAmount("10");
		sr.setOrderNo("3");
		sr.setOrderTaxAmount("3");
		sr.setOrderTotalAmount("10");
		sr.setPayCompanyCode("PTE51001409230000001");
		sr.setPayNumber("01");
		sr.setPayType("03");
		sr.setPurchaserId("11");
		sr.setTotalAmount("10");
		sr.setTotalCount("1");
		SendItems si = new SendItems();
		si.setAmount("10");
		si.setBatch("20152222");
		si.setCurrency("80");
		si.setDref1("23");
		si.setMawb("3");
		si.setMsgitem("3");
		si.setPartName("3");
		si.setPartno("3");
		si.setQty("3");
		si.setSpec("瓶");
		si.setSupplier("US001");
		si.setUnit("瓶");
		List<SendItems> sis = new ArrayList<SendItems>();
		sis.add(si);
		sr.setItems(sis);
		String jsonObj = JsonUtil.writeValueAsString(sr);
		String result = HttpUtils.httpPost(
				"http://localhost:8080/bis/logistics/dtw/getReceipt",
				jsonObj);
		System.out.println(result);
	}
	
	@Test
	public void testPersonal(){
		SendPresonalInfo s = new SendPresonalInfo();
		s.setPassKey("6442305C-5A31-43BB-B36D-C73FB1EE14EC");
		MultipleItems qi = new MultipleItems();
		qi.setAmount("10");
		qi.setBatch("20152222");
		qi.setCurrency("80");
		qi.setQty("2");
		qi.setUnit("瓶");
		qi.setSupplier("US001");
		qi.setSpec("瓶");
		qi.setPartName("sss");
		qi.setPartno("2");
		s.setAccountBookNo("s");
		s.setApplicationFormNo("s");
		s.setConsignee("s");
		s.setCurrCode("s");
		s.setDeclarantNo("s");
		s.seteCommerceCode("093791963");
		s.seteCommerceName("上海琨铭文化传播有限公司");
		s.setGrossWeight("5");
		s.setImportType("s");
		s.setInOutDateStr("20151222");
		s.setInternalAreaCompanyName("2");
		s.setMainGName("s");
		s.setMsgid("20150728000000001");
		s.setNetWeight("2");
		s.setOrderNo("201601191650");
		s.setPackNo("2");
		s.setPaperNumber("3");
		s.setPaperType("22");
		s.setRemark("34");
		s.setSenderCity("3");
		s.setSenderCountry("中国");
		s.setTradeCountry("2");
		s.setTrafName("3");
		s.setVoyageNo("2");
		s.setWayBill("2");
		s.setWorth("10");
		s.setSenderName("maoweijie");
		SendPresonalItems si = new SendPresonalItems();
		si.setCodeTs("2");
		si.setDeclareCount("23");
		si.setFirstCount("s");
		si.setDeclPrice("34");
		si.setDeclTotalPrice("2");
		si.setFirstUnit("3");
		si.setGoodsGrossWeight("23");
		si.setGoodsItemNo("2");
		si.setGoodsModel("2");
		si.setGoodsName("3");
		si.setGoodsOrder("32");
		si.setGoodsUnit("3");
		si.setOriginCountry("中国");
		si.setSecondCount("2");
		si.setSecondUnit("瓶");
		si.setTradeCurr("110");
		si.setTradeTotal("100");
		si.setUseTo("10");
		List<SendPresonalItems> sis = new ArrayList<SendPresonalItems>();
		sis.add(si);
		s.setItems(sis);
		String jsonObj = JsonUtil.writeValueAsString(s);
		String result = HttpUtils.httpPost(
				"http://localhost:8080/bis/logistics/dtw/sendPersonalInfo",
				jsonObj);
		System.out.println(result);
	}
	
}
