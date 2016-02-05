package com.isell.ws.hangzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 订单信息
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class CJkfOrderImportHead {

	/** 电商企业编码 */
	@XmlElement
	private String eCommerceCode;
	/** 电商企业名称 */
	@XmlElement
	private String eCommerceName;
	/** 进出口标志 I:进口 E:出口 */
	@XmlElement
	private String ieFlag;
	/** 支付类型 01:银行卡支付 02:余额支付 03:其他 */
	@XmlElement
	private String payType;
	/** 支付公司编码 */
	@XmlElement
	private String payCompanyCode;
	/** 支付单号 */
	@XmlElement
	private String payNumber;
	/** 订单总金额 (货款+订单税款+运费) */
	@XmlElement
	private String orderTotalAmount;
	/** 订单编号 */
	@XmlElement
	private String orderNo;
	/** 订单税款 免税模式填写0 */
	@XmlElement
	private String orderTaxAmount;
	/** 订单货款 */
	@XmlElement
	private String orderGoodsAmount;
	/** 运费 免邮模式填写0 */
	@XmlElement
	private String feeAmount;
	/** 企业备案名称 */
	@XmlElement
	private String companyName;
	/** 企业备案编号 */
	@XmlElement
	private String companyCode;
	/** 成交时间 格式：2014-02-18 15:58:11 */
	@XmlElement
	private String tradeTime;
	/** 成交币制 */
	@XmlElement
	private String currCode;
	/** 成交总价 “订单总金额”扣除“折扣”之后的金额 */
	@XmlElement
	private String totalAmount;
	/** 收件人Email */
	@XmlElement
	private String consigneeEmail;
	/** 收件人联系方式 */
	@XmlElement
	private String consigneeTel;
	/** 收件人姓名 */
	@XmlElement
	private String consignee;
	/** 收件人地址 */
	@XmlElement
	private String consigneeAddress;
	/** 总件数 包裹中独立包装的物品总数，不考虑物品计量单位 */
	@XmlElement
	private String totalCount;
	/** 发货方式（物流方式） 1.邮政小包 2.快件 3.EMS */
	@XmlElement
	private String postMode;
	/** 发件人国别 */
	@XmlElement
	private String senderCountry;
	/** 发件人姓名 */
	@XmlElement
	private String senderName;
	/** 购买人ID */
	@XmlElement
	private String purchaserId;
	/** 物流企业名称 */
	@XmlElement
	private String logisCompanyName;
	/** 物流企业编号 */
	@XmlElement
	private String logisCompanyCode;
	/** 邮编 */
	@XmlElement
	private String zipCode;
	/** 备注 */
	@XmlElement
	private String note;
	/** 运单号列表 */
	@XmlElement
	private String wayBills;
	/** 汇率 如果是人民币，统一填写1 */	
	@XmlElement
	private String rate;
	/** 个人委托申报协议 */
	@XmlElement
	private String userProcotol;
	
	public String geteCommerceCode() {
		return eCommerceCode;
	}
	public void seteCommerceCode(String eCommerceCode) {
		this.eCommerceCode = eCommerceCode;
	}
	public String geteCommerceName() {
		return eCommerceName;
	}
	public void seteCommerceName(String eCommerceName) {
		this.eCommerceName = eCommerceName;
	}
	public String getIeFlag() {
		return ieFlag;
	}
	public void setIeFlag(String ieFlag) {
		this.ieFlag = ieFlag;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayCompanyCode() {
		return payCompanyCode;
	}
	public void setPayCompanyCode(String payCompanyCode) {
		this.payCompanyCode = payCompanyCode;
	}
	public String getPayNumber() {
		return payNumber;
	}
	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}
	public String getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount(String orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderTaxAmount() {
		return orderTaxAmount;
	}
	public void setOrderTaxAmount(String orderTaxAmount) {
		this.orderTaxAmount = orderTaxAmount;
	}
	public String getOrderGoodsAmount() {
		return orderGoodsAmount;
	}
	public void setOrderGoodsAmount(String orderGoodsAmount) {
		this.orderGoodsAmount = orderGoodsAmount;
	}
	public String getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getConsigneeEmail() {
		return consigneeEmail;
	}
	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
	}
	public String getConsigneeTel() {
		return consigneeTel;
	}
	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getPostMode() {
		return postMode;
	}
	public void setPostMode(String postMode) {
		this.postMode = postMode;
	}
	public String getSenderCountry() {
		return senderCountry;
	}
	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getPurchaserId() {
		return purchaserId;
	}
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}
	public String getLogisCompanyName() {
		return logisCompanyName;
	}
	public void setLogisCompanyName(String logisCompanyName) {
		this.logisCompanyName = logisCompanyName;
	}
	public String getLogisCompanyCode() {
		return logisCompanyCode;
	}
	public void setLogisCompanyCode(String logisCompanyCode) {
		this.logisCompanyCode = logisCompanyCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getWayBills() {
		return wayBills;
	}
	public void setWayBills(String wayBills) {
		this.wayBills = wayBills;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getUserProcotol() {
		return userProcotol;
	}
	public void setUserProcotol(String userProcotol) {
		this.userProcotol = userProcotol;
	}
	
}
