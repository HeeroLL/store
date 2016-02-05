package com.isell.ps.logistics.chongqing.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderInfoFb {
	@XmlElement(name="orderNo")
	private String orderNo;
	/*原始订单编号*/
	@XmlElement(name="ORIGINAL_ORDER_NO")
	private String originalOrderNo;
	@XmlElement(name="STATUS_CODE")
	private String statusCode;
	@XmlElement(name="OP_DATE")
	private Date opDate;
	@XmlElement(name="MEMO")
	private String memo;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOriginalOrderNo() {
		return originalOrderNo;
	}
	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Date getOpDate() {
		return opDate;
	}
	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
