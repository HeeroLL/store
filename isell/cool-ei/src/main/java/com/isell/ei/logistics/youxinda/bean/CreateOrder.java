package com.isell.ei.logistics.youxinda.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 创建订单请求
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-28]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ns1:createOrder")
public class CreateOrder {
	
	@XmlElement(name = "HeaderRequest")
	private HeaderRequest headerRequest;
	
	@XmlElement(name = "OrderInfo")
	private OrderInfo orderInfo;

	public HeaderRequest getHeaderRequest() {
		return headerRequest;
	}

	public void setHeaderRequest(HeaderRequest headerRequest) {
		this.headerRequest = headerRequest;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

}
