package com.isell.ws.hangzhou.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.isell.core.pojo.OrderFiling;
import com.isell.core.pojo.OrderFilingDetail;

public class CrossBorderServiceImplTest {

	@Test
	public void testOrderRecord() {
		CrossBorderServiceImpl impl = new CrossBorderServiceImpl();
		OrderFiling orderFiling = new OrderFiling();		
		orderFiling.setBusinessNo("123");
		orderFiling.setIeFlag("I");
		orderFiling.setPayType("01");
		orderFiling.setPayNumber("111");
		orderFiling.setOrderTotalAmount(new BigDecimal(111.11));
		orderFiling.setOrderGoodsAmount(new BigDecimal(100.11));
		orderFiling.setOrderNo("CO201512281001");
		orderFiling.setOrderTaxAmount(new BigDecimal(5));
		orderFiling.setFeeAmount(new BigDecimal(5));
		orderFiling.setTradeTime("2015-12-28 12:12:12");
		orderFiling.setCurrCode("142");
		orderFiling.setTotalAmount(new BigDecimal(100.11));
		orderFiling.setConsigneeTel("13513513513");
		orderFiling.setConsignee("王月");
		orderFiling.setConsigneeAddress("软件园");
		orderFiling.setTotalCount(2);
		//orderFiling.setPostMode("2");
		orderFiling.setSenderCountry("133");
		orderFiling.setSenderName("高铭");
		orderFiling.setPurchaserId("120104197501187337");
		orderFiling.setLogisCompanyCode("2");
		orderFiling.setLogisCompanyName("快件");
		
		List<OrderFilingDetail> details = new ArrayList<OrderFilingDetail>();
		OrderFilingDetail detail = new OrderFilingDetail();	
		detail.setGoodsName("九多云");	
		detail.setCodeTs("09020100");
		detail.setGoodsModel("10ml");
		detail.setOriginCountry("123");
		detail.setUnitPrice(new BigDecimal(50));
		detail.setGoodsCount(new BigDecimal(2));
		detail.setGoodsUnit("011");
		detail.setGrossWeight(new BigDecimal(125));		
		details.add(detail);
		orderFiling.setDetails(details);
		
		orderFiling.setPurchaserName("王月");
		orderFiling.setPurchaserTelNumber("13313313311");
		orderFiling.setPurchaserPaperType("01");
		orderFiling.setPurchaserPaperNumber("321181199108144621");
		orderFiling.setPurchaserAddress("常州");
		
		orderFiling.setUserProcotol("ceshi");
		
		impl.orderRecord(orderFiling);
	}

}
