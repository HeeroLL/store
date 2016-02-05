package com.isell.demo.gztg.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EOrder {
	/* 订单编号 */
	@XmlElement(name="OrderId")
	private String orderId;
	/* 进出口标识 */
	@XmlElement(name="IEFlag")
	private String iEFlag;
	/* 订单状态 */
	@XmlElement(name="OrderStatus")
	private String orderStatus;
	/* 电商平台企业备案号（代码 */
	@XmlElement(name="EntRecordNo")
	private String entRecordNo;
	/* 电商平台企业名称 */
	@XmlElement(name="EntRecordName")
	private String entRecordName;
	/* 订单人姓名 */
	@XmlElement(name="OrderName")
	private String orderName;
	/* 订单人证件类型 */
	@XmlElement(name="OrderDocType")
	private String orderDocType;
	/* 订单人证件号 */
	@XmlElement(name="OrderDocId")
	private String orderDocId;
	/* 订单人电话 */
	@XmlElement(name="OrderPhone")
	private String orderPhone;
	/* 订单商品总额 */
	@XmlElement(name="OrderGoodTotal")
	private BigDecimal orderGoodTotal;
	/* 订单商品总额币制 */
	@XmlElement(name="OrderGoodTotalCurr")
	private String orderGoodTotalCurr;
	/* 运费 */
	@XmlElement(name="Freight")
	private BigDecimal freight;
	/* 运费币制 */
	@XmlElement(name="FreightCurr")
	private String freightCurr;
	/* 税款 */
	@XmlElement(name="Tax")
	private Number tax;
	/* 税款币制 */
	@XmlElement(name="TaxCurr")
	private String taxCurr;
	/* 备注 */
	@XmlElement(name="Note")
	private String note;
	/* 订单日期 */
	@XmlElement(name="OrderDate")
	private Date orderDate;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getiEFlag() {
		return iEFlag;
	}

	public void setiEFlag(String iEFlag) {
		this.iEFlag = iEFlag;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getEntRecordNo() {
		return entRecordNo;
	}

	public void setEntRecordNo(String entRecordNo) {
		this.entRecordNo = entRecordNo;
	}

	public String getEntRecordName() {
		return entRecordName;
	}

	public void setEntRecordName(String entRecordName) {
		this.entRecordName = entRecordName;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderDocType() {
		return orderDocType;
	}

	public void setOrderDocType(String orderDocType) {
		this.orderDocType = orderDocType;
	}

	public String getOrderDocId() {
		return orderDocId;
	}

	public void setOrderDocId(String orderDocId) {
		this.orderDocId = orderDocId;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public BigDecimal getOrderGoodTotal() {
		return orderGoodTotal;
	}

	public void setOrderGoodTotal(BigDecimal orderGoodTotal) {
		this.orderGoodTotal = orderGoodTotal;
	}

	public String getOrderGoodTotalCurr() {
		return orderGoodTotalCurr;
	}

	public void setOrderGoodTotalCurr(String orderGoodTotalCurr) {
		this.orderGoodTotalCurr = orderGoodTotalCurr;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public String getFreightCurr() {
		return freightCurr;
	}

	public void setFreightCurr(String freightCurr) {
		this.freightCurr = freightCurr;
	}

	public Number getTax() {
		return tax;
	}

	public void setTax(Number tax) {
		this.tax = tax;
	}

	public String getTaxCurr() {
		return taxCurr;
	}

	public void setTaxCurr(String taxCurr) {
		this.taxCurr = taxCurr;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

}
