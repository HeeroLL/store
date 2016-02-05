package com.isell.core.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * 订单申报信息对象
 * @author 一代魔笛
 *
 */
public class OrderFiling {

	/** 业务编号 */
	private String businessNo;
	/** 进出口标志 I:进口E:出口 */
	private String ieFlag;
	/** 支付类型 01:银行卡支付 02:余额支付 03:其他 */
	private String payType;
	/** 支付公司编码 */
	private String payCompanyCode;
	/** 支付单号 */
	private String payNumber;
	/** 订单总金额 */
	private BigDecimal orderTotalAmount;
	/** 订单货款 */
	private BigDecimal orderGoodsAmount;
	/** 订单编号 */
	private String orderNo;
	/** 订单税款 */
	private BigDecimal orderTaxAmount;
	/** 运费 非必填*/
	private BigDecimal feeAmount;
	/** 电商企业编码 */
	private String eCommerceCode;
	/** 电商企业名称 */
	private String eCommerceName;
	/** 成交时间 */
	private String tradeTime;
	/** 成交币制 非必填*/
	private String currCode;
	/** 成交总价 非必填*/
	private BigDecimal totalAmount;
	/** 收件人Email 非必填*/
	private String consigneeEmail;
	/** 收件人联系方式 */
	private String consigneeTel;
	/** 收件人姓名 */
	private String consignee;
	/** 收件人地址 */
	private String consigneeAddress;
	/** 总件数 */
	private int totalCount;
	/** 发货方式（物流方式） 非必填*/
	private String postMode;
	/** 发件人国别 */
	private String senderCountry;
	/** 发件人姓名 */
	private String senderName;
	/** 物流企业名称 */
	private String logisCompanyName;
	/** 物流企业编号 */
	private String logisCompanyCode;
	/** 邮编 非必填*/
	private String zipCode;
	/** 备注 */
	private String note;
	/** 运单号列表 */
	private String wayBills;
	/** 汇率 */
	private String rate;
	/** 购买人ID */	
	private String purchaserId;
	/** 姓名 */
	private String purchaserName;
	/** 注册邮箱 */
	private String purchaserEmail;
	/** 联系电话 */
	private String purchaserTelNumber;
	/** 证件类型代码 */
	private String purchaserPaperType;
	/** 证件号码 */
	private String purchaserPaperNumber;
	/** 地址 */
	private String purchaserAddress;
	/** 订单表体信息 */
	private List<OrderFilingDetail> details = new ArrayList<OrderFilingDetail>();
	/** 个人委托申报协议 */
	@XmlElement
	private String userProcotol;
	
	/** 业务编号 */
	public String getBusinessNo() {
		return businessNo;
	}
	/** 业务编号 */
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	/** 进出口标志 I:进口E:出口 */
	public String getIeFlag() {
		return ieFlag;
	}
	/** 进出口标志 I:进口E:出口 */
	public void setIeFlag(String ieFlag) {
		this.ieFlag = ieFlag;
	}
	/** 支付类型 01:银行卡支付 02:余额支付 03:其他 */
	public String getPayType() {
		return payType;
	}
	/** 支付类型 01:银行卡支付 02:余额支付 03:其他 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/** 支付公司编码 */
	public String getPayCompanyCode() {
		return payCompanyCode;
	}
	/** 支付公司编码 */
	public void setPayCompanyCode(String payCompanyCode) {
		this.payCompanyCode = payCompanyCode;
	}
	/** 支付单号 */
	public String getPayNumber() {
		return payNumber;
	}
	/** 支付单号 */
	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}
	/** 订单总金额 */
	public BigDecimal getOrderTotalAmount() {
		return orderTotalAmount;
	}
	/** 订单总金额 */
	public void setOrderTotalAmount(BigDecimal orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	/** 订单货款 */
	public BigDecimal getOrderGoodsAmount() {
		return orderGoodsAmount;
	}
	/** 订单货款 */
	public void setOrderGoodsAmount(BigDecimal orderGoodsAmount) {
		this.orderGoodsAmount = orderGoodsAmount;
	}
	/** 订单编号 */
	public String getOrderNo() {
		return orderNo;
	}
	/** 订单编号 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/** 订单税款 */
	public BigDecimal getOrderTaxAmount() {
		return orderTaxAmount;
	}
	/** 订单税款 */
	public void setOrderTaxAmount(BigDecimal orderTaxAmount) {
		this.orderTaxAmount = orderTaxAmount;
	}
	/** 运费 非必填*/
	public BigDecimal getFeeAmount() {
		return feeAmount;
	}
	/** 运费 非必填*/
	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}
	/** 电商企业编码 */
	public String geteCommerceCode() {
		return eCommerceCode;
	}
	/** 电商企业编码 */
	public void seteCommerceCode(String eCommerceCode) {
		this.eCommerceCode = eCommerceCode;
	}
	/** 电商企业名称 */
	public String geteCommerceName() {
		return eCommerceName;
	}
	/** 电商企业名称 */
	public void seteCommerceName(String eCommerceName) {
		this.eCommerceName = eCommerceName;
	}
	/** 成交时间 */
	public String getTradeTime() {
		return tradeTime;
	}
	/** 成交时间 */
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	/** 成交币制 非必填*/
	public String getCurrCode() {
		return currCode;
	}
	/** 成交币制 非必填*/
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	/** 成交总价 非必填*/
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	/** 成交总价 非必填*/
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	/** 收件人Email 非必填*/
	public String getConsigneeEmail() {
		return consigneeEmail;
	}
	/** 收件人Email 非必填*/
	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
	}
	/** 收件人联系方式 */
	public String getConsigneeTel() {
		return consigneeTel;
	}
	/** 收件人联系方式 */
	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}
	/** 收件人姓名 */
	public String getConsignee() {
		return consignee;
	}
	/** 收件人姓名 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	/** 收件人地址 */
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	/** 收件人地址 */
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	/** 总件数 */
	public int getTotalCount() {
		return totalCount;
	}
	/** 总件数 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/** 发货方式（物流方式） 非必填*/
	public String getPostMode() {
		return postMode;
	}
	/** 发货方式（物流方式） 非必填*/
	public void setPostMode(String postMode) {
		this.postMode = postMode;
	}
	/** 发件人国别 */
	public String getSenderCountry() {
		return senderCountry;
	}
	/** 发件人国别 */
	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}
	/** 发件人姓名 */
	public String getSenderName() {
		return senderName;
	}
	/** 发件人姓名 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	/** 物流企业名称 */
	public String getLogisCompanyName() {
		return logisCompanyName;
	}
	/** 物流企业名称 */
	public void setLogisCompanyName(String logisCompanyName) {
		this.logisCompanyName = logisCompanyName;
	}
	/** 物流企业编号 */
	public String getLogisCompanyCode() {
		return logisCompanyCode;
	}
	/** 物流企业编号 */
	public void setLogisCompanyCode(String logisCompanyCode) {
		this.logisCompanyCode = logisCompanyCode;
	}
	/** 邮编 非必填*/
	public String getZipCode() {
		return zipCode;
	}
	/** 邮编 非必填*/
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/** 备注 非必填*/
	public String getNote() {
		return note;
	}
	/** 备注 非必填*/
	public void setNote(String note) {
		this.note = note;
	}
	/** 运单号列表 非必填*/
	public String getWayBills() {
		return wayBills;
	}
	/** 运单号列表 非必填*/
	public void setWayBills(String wayBills) {
		this.wayBills = wayBills;
	}
	/** 汇率 非必填*/
	public String getRate() {
		return rate;
	}
	/** 汇率 非必填*/
	public void setRate(String rate) {
		this.rate = rate;
	}
	/** 购买人ID */	
	public String getPurchaserId() {
		return purchaserId;
	}
	/** 购买人ID */	
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}
	/** 购买人姓名 */
	public String getPurchaserName() {
		return purchaserName;
	}
	/** 购买人姓名 */
	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}
	/** 购买人注册邮箱 非必填*/
	public String getPurchaserEmail() {
		return purchaserEmail;
	}
	/** 购买人注册邮箱 非必填*/
	public void setPurchaserEmail(String purchaserEmail) {
		this.purchaserEmail = purchaserEmail;
	}
	/** 购买人联系电话 */
	public String getPurchaserTelNumber() {
		return purchaserTelNumber;
	}
	/** 购买人联系电话 */
	public void setPurchaserTelNumber(String purchaserTelNumber) {
		this.purchaserTelNumber = purchaserTelNumber;
	}
	/** 购买人证件类型代码 非必填*/
	public String getPurchaserPaperType() {
		return purchaserPaperType;
	}
	/** 购买人证件类型代码 非必填*/
	public void setPurchaserPaperType(String purchaserPaperType) {
		this.purchaserPaperType = purchaserPaperType;
	}
	/** 购买人证件号码 */
	public String getPurchaserPaperNumber() {
		return purchaserPaperNumber;
	}
	/** 购买人证件号码 */
	public void setPurchaserPaperNumber(String purchaserPaperNumber) {
		this.purchaserPaperNumber = purchaserPaperNumber;
	}
	/** 购买人地址 */
	public String getPurchaserAddress() {
		return purchaserAddress;
	}
	/** 购买人地址 */
	public void setPurchaserAddress(String purchaserAddress) {
		this.purchaserAddress = purchaserAddress;
	}
	/** 订单表体信息 */
	public List<OrderFilingDetail> getDetails() {
		return details;
	}
	/** 订单表体信息 */
	public void setDetails(List<OrderFilingDetail> details) {
		this.details = details;
	}
	/** 个人委托申报协议 */	
	public String getUserProcotol() {
		return userProcotol;
	}
	/** 个人委托申报协议 */
	public void setUserProcotol(String userProcotol) {
		this.userProcotol = userProcotol;
	}
}
