package com.isell.ei.logistics.youxinda.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 查询订单信息第三层
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HeaderRequest")
public class GetOrderByCode {
	
	@XmlElement(name = "HeaderRequest")
	private HeaderRequest headerRequest;
	
	/**
	 * 订单号/客户交易订单号/运单号之一
	 */
	private String orderCode;

	public HeaderRequest getHeaderRequest() {
		return headerRequest;
	}

	public void setHeaderRequest(HeaderRequest headerRequest) {
		this.headerRequest = headerRequest;
	}

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

}
