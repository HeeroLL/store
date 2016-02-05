package com.isell.ps.logistics.chongqing.bean;

import javax.xml.bind.annotation.XmlAccessType;



import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class DtcFlow {
	@XmlElement(name="ORDER_INFO_FB")
	private OrderInfoFb orderInfoFb;

	public OrderInfoFb getOrderInfoFb() {
		return orderInfoFb;
	}

	public void setOrderInfoFb(OrderInfoFb orderInfoFb) {
		this.orderInfoFb = orderInfoFb;
	}

	
}
