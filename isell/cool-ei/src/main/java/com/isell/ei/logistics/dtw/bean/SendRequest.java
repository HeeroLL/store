package com.isell.ei.logistics.dtw.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sendRequest ")
public class SendRequest {

	private String key = "6442305C-5A31-43BB-B36D-C73FB1EE14EC";
	/*电商企业编码（电商企业在跨境平台备案编码）*/
	private String eCommerceCode;
	/*电商企业名称*/
	private String eCommerceName;
	/*电商企业发货订单号\销售订单号*/
	private String msgid;
	/*支付类型*/
	private String payType;
	/*支付公司编码*/
	private String payCompanyCode;
	/*支付单号*/
	private String payNumber;
	/*订单总金额*/
	private String orderTotalAmount;
	/*订单货款*/
	private String orderGoodsAmount;
	/*订单编号*/
	private String orderNo;
	/*订单税款*/
	private String orderTaxAmount;
	/*收货人名称*/
	private String consignee;
	/*收件人地址*/
	private String consigneeAdd;
	/*收货人电话*/
	private String consigneeTel;
	/*总件数*/
	private String totalCount;
	/*成交总价*/
	private String totalAmount;
	/*购买人ID*/
	private String purchaserId;
	
	@XmlAttribute(name="items")
	private List<SendItems> items;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

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

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
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

	public String getOrderGoodsAmount() {
		return orderGoodsAmount;
	}

	public void setOrderGoodsAmount(String orderGoodsAmount) {
		this.orderGoodsAmount = orderGoodsAmount;
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

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeAdd() {
		return consigneeAdd;
	}

	public void setConsigneeAdd(String consigneeAdd) {
		this.consigneeAdd = consigneeAdd;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}

	public List<SendItems> getItems() {
		return items;
	}

	public void setItems(List<SendItems> items) {
		this.items = items;
	}



	






}
