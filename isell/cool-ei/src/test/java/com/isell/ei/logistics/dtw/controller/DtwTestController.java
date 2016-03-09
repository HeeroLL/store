package com.isell.ei.logistics.dtw.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		qi.setAmount("10");
		qi.setBatch(sdf.format(new Date()));
		qi.setCurrency("502");
		qi.setQty("2");
		qi.setUnit("122");
		qi.setSupplier("C3823699");
		qi.setSpec("122罐");
		qi.setPartName("婴儿营养米粉0013");
		qi.setPartno("CSXJ0013");
		
		List<MultipleItems> qs = new ArrayList<MultipleItems>();
		qs.add(qi);
		qo.setItems(qs);
		String json = JsonUtil.writeValueAsString(qo); 
		System.out.println(json);
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
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		gr.setMsgid("20160304105350522");//订单号
		gr.setHawb("160-20980282");//提单号
		gr.setMawb("00350510");//主运单号
		gr.setSupplier("C3823699");/*供应商编码（电商平台下的商家备案编号）*/
		GainsItem qi = new GainsItem();
		qi.setMsgItem("1");//项次
		qi.setCurrency("601");//币种
		qi.setQty("1515");//数量
		qi.setUnit("017");//单位
		qi.setSpec("GOAT 原味山羊奶皂 100g");//规格
		qi.setPartno("926");//产品编码
		qi.setPartName("山羊奶皂");//物品 名称
		qi.setHsCode("3401110000");//海关商品编码
		qi.setOriginCountry("601");//产国
		
		GainsItem qi2 = new GainsItem();
		qi2.setMsgItem("2");
		qi2.setCurrency("601");
		qi2.setQty("999");
		qi2.setUnit("017");
		qi2.setSpec("GOAT 摩洛哥油味山羊奶皂 100g");
		qi2.setPartno("928");
		qi2.setPartName("山羊奶皂");
		qi2.setHsCode("3401110000");//海关商品编码
		qi2.setOriginCountry("601");
		
		/*GainsItem qi3 = new GainsItem();
		qi3.setMsgItem("3");
		qi3.setCurrency("601");
		qi3.setQty("1344");
		qi3.setUnit("017");
		qi3.setSpec("GOAT 柠檬味山羊奶皂 100g");
		qi3.setPartno("930");
		qi3.setPartName("山羊奶皂");
		qi3.setHsCode("3401110000");//海关商品编码
		qi3.setOriginCountry("601");*/
		
		GainsItem qi4 = new GainsItem();
		qi4.setMsgItem("3");
		qi4.setCurrency("601");
		qi4.setQty("768");
		qi4.setUnit("017");
		qi4.setSpec("GOAT燕麦味山羊奶皂 100g");
		qi4.setPartno("932");
		qi4.setPartName("山羊奶皂");
		qi4.setHsCode("3401110000");//海关商品编码
		qi4.setOriginCountry("601");
		
		
		GainsItem qi5 = new GainsItem();
		qi5.setMsgItem("4");
		qi5.setCurrency("601");
		qi5.setQty("10086");
		qi5.setUnit("017");
		qi5.setSpec("GOAT 麦卢卡蜂蜜味山羊奶皂 100g");
		qi5.setPartno("933");
		qi5.setPartName("山羊奶皂");
		qi5.setHsCode("3401110000");//海关商品编码
		qi5.setOriginCountry("601");
		
		GainsItem qi6 = new GainsItem();
		qi6.setMsgItem("5");
		qi6.setCurrency("601");
		qi6.setQty("1344");
		qi6.setUnit("017");
		qi6.setSpec("GOAT 椰子油味山羊奶皂 100g");
		qi6.setPartno("931");
		qi6.setPartName("山羊奶皂");
		qi6.setHsCode("3401110000");//海关商品编码
		qi6.setOriginCountry("601");
		
		GainsItem qi7 = new GainsItem();
		qi7.setMsgItem("6");
		qi7.setCurrency("601");
		qi7.setQty("960");
		qi7.setUnit("017");
		qi7.setSpec("GOAT 儿童款 山羊奶皂 100g");
		qi7.setPartno("934");
		qi7.setPartName("山羊奶皂");
		qi7.setHsCode("3401110000");//海关商品编码
		qi7.setOriginCountry("601");
		
		GainsItem qi8 = new GainsItem();
		qi8.setMsgItem("7");
		qi8.setCurrency("601");
		qi8.setQty("10086");
		qi8.setUnit("017");
		qi8.setSpec("GOAT 草莓味 山羊奶皂 100g");
		qi8.setPartno("934");
		qi8.setPartName("山羊奶皂");
		qi8.setHsCode("3401110000");//海关商品编码
		qi8.setOriginCountry("601");
		
		List<GainsItem> qs = new ArrayList<GainsItem>();
		qs.add(qi);
		qs.add(qi2);
		/*qs.add(qi3);*/
		qs.add(qi4);
		qs.add(qi5);
		qs.add(qi6);
		qs.add(qi7);
		qs.add(qi8);
		gr.setItems(qs);
		String jsonObj = JsonUtil.writeValueAsString(gr);
		System.out.println(jsonObj);
		/*String result = HttpUtils.httpPost(
				"http://localhost:8080/bis/logistics/dtw/getGainsOrder",jsonObj);
		System.out.println(result);*/
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
		//s.setAccountBookNo("s001");
		//s.setApplicationFormNo("s002");
		s.setConsignee("毛伟杰");
		s.setCurrCode("142");/**成交币值 1**/
		//s.setDeclarantNo("s");
		s.seteCommerceCode("093791963");
		s.seteCommerceName("上海琨铭文化传播有限公司");
		s.setGrossWeight("1.1");
		s.setImportType("1");	/**进口类型（0一般进口，1保税进口）**/
		//s.setInOutDateStr("2060302");
		//s.setInternalAreaCompanyName("2");
		s.setMainGName("婴儿营养米粉0013");
		s.setMsgid("20150728000000001");
		s.setNetWeight("1");
		s.setOrderNo("201601191650");
		s.setPackNo("1");
		s.setPaperNumber("513701199109084431");/**zhengjian**/
		s.setPaperType("1");/**默认是1，身份证**/
		//s.setRemark("我要最好的");
		//s.setSenderCity("杭州");
		s.setSenderCountry("502");/**发件人国别（三字代码）**/
		s.setTradeCountry("601");/*贸易国别（起运地三字代码）*/
		//s.setTrafName("3");
		//s.setVoyageNo("2");
		//s.setWayBill("2");
		s.setWorth("10");/*价值*/
		s.setSenderName("震荣优品");
		SendPresonalItems si = new SendPresonalItems();
		si.setCodeTs("09029900");
		si.setDeclareCount("2");
		si.setFirstCount("2");/**申报数量和第一数量是对应关系**/
		si.setDeclPrice("5");
		si.setDeclTotalPrice("10");
		si.setFirstUnit("122");
		//si.setGoodsGrossWeight("1");
		si.setGoodsItemNo("CSXJ0013");
		si.setGoodsModel("W29916000063");//规格型号
		si.setGoodsName("婴儿营养米粉0013");
		si.setGoodsOrder("1");
		si.setGoodsUnit("122");/*122罐*/
		si.setOriginCountry("142");/**产销国代码(三字代码)**/
		//si.setSecondCount("2");
		//si.setSecondUnit("瓶");
		si.setTradeCurr("142");
		si.setTradeTotal("10");/*成交总价（包裹实际成交的金额）*/
		si.setUseTo("11");
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
