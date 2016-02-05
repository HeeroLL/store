package com.isell.ei.logistics.chongqing.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.core.util.JsonUtil;
import com.isell.ei.logistics.chongqing.bean.DtcFlow;
import com.isell.ei.logistics.chongqing.bean.Goods;
import com.isell.ei.logistics.chongqing.bean.GoodsDtcFlow;
import com.isell.ei.logistics.chongqing.bean.GoodsMessageBody;
import com.isell.ei.logistics.chongqing.bean.GoodsMessageHead;
import com.isell.ei.logistics.chongqing.bean.GoodsSkuInfo;
import com.isell.ei.logistics.chongqing.bean.MessageBody;
import com.isell.ei.logistics.chongqing.bean.MessageHead;
import com.isell.ei.logistics.chongqing.bean.Order;
import com.isell.ei.logistics.chongqing.bean.OrderDetail;
import com.isell.ei.logistics.chongqing.bean.OrderHead;
import com.isell.ei.logistics.chongqing.bean.PayInfo;
import com.isell.ei.logistics.chongqing.bean.PayInfoDtcFlow;
import com.isell.ei.logistics.chongqing.bean.PayInfoMessageBody;
import com.isell.ei.logistics.chongqing.bean.PayInfoMessageHead;
import com.isell.ei.logistics.chongqing.bean.PayMementInfo;

public class ChongqingTestController {

	
	@Test
	public void sendOrder(){
		OrderDetail od = new OrderDetail();
		od.setSku("2015122411");
		od.setGoodsSpec("带外壳的保温杯");
		od.setCurrencyCode("142");//人民币
		od.setPrice(119.00);
		od.setQty(1);
		od.setGoodsFee(119.00);
		od.setTaxFee(0);
		List<OrderDetail> ods = new ArrayList<OrderDetail>();
		ods.add(od);
		OrderHead oh = new OrderHead();
		oh.setCustomsCode("8012");
		oh.setBizTypeCode("I10");
		oh.setOriginalOrderNo("201512241635");//订单号每次不一样
		oh.setEshopEntCode("0001111222");
		oh.setEshopEntName("上海艾售电子商务有限公司");
		oh.setDespArriCountryCode("499");//起运国
		oh.setShipToolCode("5");//运输方式
		oh.setReceiverIdNo("500226198710271148");
		oh.setReceiverName("庄挺");
		//oh.setReceiverAddress("重庆重庆寸滩保税港区水港综合楼1604室");
		oh.setReceiverTel("13983892879");
		oh.setGoodsFee(119.00);
		oh.setTaxFee(0);
		oh.setGrossWeight(0.6);
		oh.setOrderDetail(ods);
		oh.setProxyEntCode("");
		oh.setProxyEntName("");
		oh.setReceiverAddress("重庆102室");
		oh.setSortlineId("SORTLINE02");
		DtcFlow df = new DtcFlow();
		df.setOrderHead(oh);
		MessageBody mb = new MessageBody();
		mb.setDtcFlow(df);
		
		MessageHead mh = new MessageHead();
		mh.setMessageType("ORDER_INFO");
		 String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		 System.out.println(uuid);
		mh.setMessageId(uuid);//201512241635208UWYFKPXPGA每次不一样
		mh.setActionType("1");
		mh.setMessageTime("2015-12-24 14:28:25");
		mh.setSenderId("0001111222");
		mh.setReceiverId("CQITC");
		mh.setUserNo("0001111222");
		mh.setPassword(Coder.encodeMd5("0001111222"));
		mh.setSenderAddress("");
		mh.setReceiverAddress("");
		mh.setPlatFormNo("");
		mh.setCustomCode("");
		mh.setSeqNo("");
		mh.setNote("");
		Order dtc = new Order();
		dtc.setMessageBody(mb);
		dtc.setMessageHead(mh);
		String xml = JaxbUtil.convertToXml(dtc);
		System.out.println(xml);
		String jsonObj = JsonUtil.writeValueAsString(dtc);
		String result = HttpUtils.httpPost(
				"http://service.i-coolshop.com/logistics/chongqing/sendOrder",jsonObj);
		System.out.println(result);
	}	
	
	@Test
	public void testPayInfo(){
		PayMementInfo pmi = new PayMementInfo();
		pmi.setBizTypeCode("I10");
		pmi.setCustomsCode("8012");
		pmi.setEshopEntCode("0001111222");
		pmi.setEshopEntName("上海艾售电子商务有限公司");
		pmi.setPaymentEntCode("0001111222");
		pmi.setPaymentEntName("上海艾售电子商务有限公司");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		pmi.setPaymentNo(str);
		pmi.setOriginalOrderNo("201512241635");
		pmi.setPayAmount(new BigDecimal(100));
		pmi.setGoodsFee(new BigDecimal(10));
		pmi.setTaxFee(new BigDecimal(0));
		pmi.setCurrencyCode("142");
		pmi.setMemo("");
		List<PayMementInfo> pmis = new ArrayList<PayMementInfo>();
		pmis.add(pmi);
		PayInfoDtcFlow payDtc = new PayInfoDtcFlow();
		payDtc.setPaymementInfo(pmis);
		PayInfoMessageBody pmb = new PayInfoMessageBody();
		pmb.setDtcFlow(payDtc);
		PayInfoMessageBody mb = new PayInfoMessageBody();
		mb.setDtcFlow(payDtc);
		
		PayInfoMessageHead mh = new PayInfoMessageHead();
		mh.setMessageType("PAYMENT_INFO");
		 String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		mh.setMessageId(uuid);
		Date date2 = new Date();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str2 = sdf2.format(date2);
		mh.setMessageTime(str2);
		mh.setSenderId("0001111222");
		mh.setReceiverId("CQITC");
		mh.setUserNo("0001111222");
		mh.setPassword("0001111222");
		mh.setSenderAddress("");
		mh.setReceiverAddress("");
		mh.setPlatFormNo("");
		mh.setCustomCode("");
		mh.setSeqNo("");
		mh.setNote("");
		PayInfo dtc = new PayInfo();
		dtc.setMessageBody(mb);
		dtc.setMessageHead(mh);
		String xml = JaxbUtil.convertToXml(dtc);
		System.out.println(xml);
		String jsonObj = JsonUtil.writeValueAsString(dtc);
		String result = HttpUtils.httpPost(
				"http://service.i-coolshop.com/logistics/chongqing/payInfo",jsonObj);
		System.out.println(result);
	}
	
	@Test
	public void testGoods(){
		GoodsSkuInfo gsi = new GoodsSkuInfo();
		gsi.setEshopEntCode("0001111222");
		gsi.setEshopEntName("上海艾售电子商务有限公司");
		gsi.setSku("201512251225");
		gsi.setGoodsName("带外壳的保温杯");
		gsi.setGoodsSpec("带外壳的保温杯");
		gsi.setDeclareUnit("001");
		gsi.setPostTaxNo("11030190");
		gsi.setLegalUnit("001");//台
		gsi.setConvLegalUnitNum("1");
		gsi.setHsCode("4202210090");
		gsi.setInAreaUnit("001");
		gsi.setConvInAreaUnitNum("1");
		List<GoodsSkuInfo> gsk = new ArrayList<GoodsSkuInfo>();
		gsk.add(gsi);
		GoodsDtcFlow dtc = new GoodsDtcFlow();
		dtc.setSkuInfo(gsk);
		GoodsMessageBody gmb = new GoodsMessageBody();
		gmb.setDtcFlow(dtc);
		
		GoodsMessageHead mh = new GoodsMessageHead();
		mh.setMessageType("SKU_INFO");
		 String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");    
		mh.setMessageId(uuid);
		mh.setActionType("1");
		mh.setMessageTime(new Date());
		mh.setSenderId("0001111222");
		mh.setReceiverId("CQITC");
		mh.setUserNo("0001111222");
		mh.setPassword("0001111222");
		Goods goods = new Goods();
		goods.setMessageBody(gmb);
		goods.setMessageHead(mh);
		String xml = JaxbUtil.convertToXml(goods);
		System.out.println(xml);
		String jsonObj = JsonUtil.writeValueAsString(goods);
		String result = HttpUtils.httpPost(
				"http://service.i-coolshop.com/logistics/chongqing/goodsRecord",jsonObj);
		System.out.println(result);
	}
}
