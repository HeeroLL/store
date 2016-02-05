package com.isell.ei.logistics.chongqing.bean;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class PayMementInfo {
	/*关区代码*/
     @XmlElement(name="CUSTOMS_CODE")
     private String customsCode;
     /*  直购，网购代码*/
     @XmlElement(name="BIZ_TYPE_CODE")
     private String bizTypeCode;
     /* 海关10位编码*/
     @XmlElement(name="ESHOP_ENT_CODE")
     private String eshopEntCode;
     /* 企业名称*/
     @XmlElement(name="ESHOP_ENT_NAME")
     private String eshopEntName;
     /*海关10位编码*/
     @XmlElement(name="PAYMENT_ENT_CODE")
     private String paymentEntCode;
    /* 企业名称*/
     @XmlElement(name="PAYMENT_ENT_NAME")
     private String paymentEntName;
    /* 支付单编号*/
     @XmlElement(name="PAYMENT_NO")
     private String paymentNo;
     /*原始订单编号*/
     @XmlElement(name="ORIGINAL_ORDER_NO")
     private String originalOrderNo;
    /* 总额*/
     @XmlElement(name="PAY_AMOUNT")
     private BigDecimal payAmount;
     /*商品货款金额	*/
     @XmlElement(name="GOODS_FEE")
     private BigDecimal goodsFee;
     /*税款金额	*/
     @XmlElement(name="TAX_FEE")
     private BigDecimal taxFee;
     /*支付币制	*/
     @XmlElement(name="CURRENCY_CODE")
     private String currencyCode;
     @XmlElement(name="MEMO")
     private String memo;
	public String getCustomsCode() {
		return customsCode;
	}
	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}
	public String getBizTypeCode() {
		return bizTypeCode;
	}
	public void setBizTypeCode(String bizTypeCode) {
		this.bizTypeCode = bizTypeCode;
	}
	public String getEshopEntCode() {
		return eshopEntCode;
	}
	public void setEshopEntCode(String eshopEntCode) {
		this.eshopEntCode = eshopEntCode;
	}
	public String getEshopEntName() {
		return eshopEntName;
	}
	public void setEshopEntName(String eshopEntName) {
		this.eshopEntName = eshopEntName;
	}
	public String getPaymentEntCode() {
		return paymentEntCode;
	}
	public void setPaymentEntCode(String paymentEntCode) {
		this.paymentEntCode = paymentEntCode;
	}
	public String getPaymentEntName() {
		return paymentEntName;
	}
	public void setPaymentEntName(String paymentEntName) {
		this.paymentEntName = paymentEntName;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public String getOriginalOrderNo() {
		return originalOrderNo;
	}
	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public BigDecimal getGoodsFee() {
		return goodsFee;
	}
	public void setGoodsFee(BigDecimal goodsFee) {
		this.goodsFee = goodsFee;
	}
	public BigDecimal getTaxFee() {
		return taxFee;
	}
	public void setTaxFee(BigDecimal taxFee) {
		this.taxFee = taxFee;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
     
     
}
