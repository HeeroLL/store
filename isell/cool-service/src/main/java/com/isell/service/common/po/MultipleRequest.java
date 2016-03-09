package com.isell.service.common.po;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
/**
 * 
 * @author maoweijie
 * @version 
 */
public class MultipleRequest {
	@JsonProperty("PassKey")
	private String PassKey = "6442305C-5A31-43BB-B36D-C73FB1EE14EC";
	/*电商企业编码（电eCommerceCode企业在跨境平台备案编码）*/
	@JsonProperty("eCommerceCode")
	private String eCommerceCode;
	/*电商企业名称*/
	@JsonProperty("eCommerceName")
	private String eCommerceName;
	/*电商企业发货订单号\销售订单号*/
	@JsonProperty("Msgid")
	private String msgid;
	/*支付类型"01:银行卡支付02:余额支付03:其他"*/
	@JsonProperty("payType")
	private String payType;
	/*支付公司编码支付平台在跨境平台备案编号*/
	@JsonProperty("payCompanyCode")
	private String payCompanyCode;
	/*支付单号支付成功后，支付平台反馈给电商平台的支付单号*/
	@JsonProperty("payNumber")
	private String payNumber;
	/*订单总金额货款+订单税款+运费*/
	@JsonProperty("orderTotalAmount")
	private String orderTotalAmount;
	/*订单货款*/
	@JsonProperty("orderGoodsAmount")
	private String orderGoodsAmount;
	/*订单编号 电商平台订单号*/
	@JsonProperty("orderNo")
	private String orderNo;
	/*订单税款*/
	@JsonProperty("orderTaxAmount")
	private String orderTaxAmount;
	/*总件数*/
	@JsonProperty("totalCount")
	private String totalCount;
	/*成交总价 “订单总金额”扣除“折扣”之后的金额。*/
	@JsonProperty("totalAmount")
	private String totalAmount;
	/*购买人ID购买人在电商平台的注册ID*/
	@JsonProperty("purchaserId")
	private String purchaserId;
	/*发货人名称*/
	@JsonProperty("Shipper")
	private String shipper;
	/*发货人省份*/
	@JsonProperty("ShipperPro")
	private String shipperPro;
	/*发货人市*/
	@JsonProperty("ShipperCity")
	private String shipperCity;
	/*	发货人区县*/
	@JsonProperty("ShipperDistrict")
	private String shipperDistrict;
	/*发货人地址*/
	@JsonProperty("ShipperAddress")
	private String shipperAddress;
	/*发货人手机*/
	@JsonProperty("ShipperMobile")
	private String shipperMobile;
	/*发货人电话*/
	@JsonProperty("ShipperTel")
	private String shipperTel;
	/*发货人所在国*/
	@JsonProperty("ShipperCountry")
	private String shipperCountry;
	/*发货人邮编*/
	@JsonProperty("ShipperZip")
	private String shipperZip;
	/*收货人名称*/
	@JsonProperty("Consignee")
	private String consignee;
	/*收货人省份*/
	@JsonProperty("ConsigneePro")
	private String consigneePro;
	/*收货人市*/
	@JsonProperty("ConsigneeCity")
	private String consigneeCity;
	/*收货人区县*/
	@JsonProperty("ConsigneeDistrict")
	private String consigneeDistrict;
	/*收件人地址*/
	@JsonProperty("ConsigneeAdd")
	private String consigneeAdd;
	/*收货人手机(手机与电话二选一)*/
	@JsonProperty("ConsigneeMobile")
	private String consigneeMobile;
	/*收货人电话(手机与电话二选一)*/
	@JsonProperty("ConsigneeTel")
	private String consigneeTel;
	/*收货人所在国(进口为中国)*/
	@JsonProperty("ConsigneeCountry")
	private String consigneeCountry;
	/*收货人邮编*/
	@JsonProperty("ConsigneeZip")
	private String consigneeZip;
	/*毛重*/
	@JsonProperty("Weigtht")
	private BigDecimal weigtht;
	/*批次号*/
	@JsonProperty("LotNo")
	private String lotNo;
	/*净重*/
	@JsonProperty("NetWeight")
	private BigDecimal netWeight;
	@JsonProperty("logisCompanyCode")
	private String logisCompanyCode;
	@JsonProperty("logisCompanyName")
	private String logisCompanyName;
	
	@JsonProperty("items")
	private List<MultipleItems> items;


	public String getPassKey() {
		return PassKey;
	}


	public void setPassKey(String passKey) {
		PassKey = passKey;
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


	public String getShipper() {
		return shipper;
	}


	public void setShipper(String shipper) {
		this.shipper = shipper;
	}


	public String getShipperPro() {
		return shipperPro;
	}


	public void setShipperPro(String shipperPro) {
		this.shipperPro = shipperPro;
	}


	public String getShipperCity() {
		return shipperCity;
	}


	public void setShipperCity(String shipperCity) {
		this.shipperCity = shipperCity;
	}


	public String getShipperDistrict() {
		return shipperDistrict;
	}


	public void setShipperDistrict(String shipperDistrict) {
		this.shipperDistrict = shipperDistrict;
	}


	public String getShipperAddress() {
		return shipperAddress;
	}


	public void setShipperAddress(String shipperAddress) {
		this.shipperAddress = shipperAddress;
	}


	public String getShipperMobile() {
		return shipperMobile;
	}


	public void setShipperMobile(String shipperMobile) {
		this.shipperMobile = shipperMobile;
	}


	public String getShipperTel() {
		return shipperTel;
	}


	public void setShipperTel(String shipperTel) {
		this.shipperTel = shipperTel;
	}


	public String getShipperCountry() {
		return shipperCountry;
	}


	public void setShipperCountry(String shipperCountry) {
		this.shipperCountry = shipperCountry;
	}


	public String getShipperZip() {
		return shipperZip;
	}


	public void setShipperZip(String shipperZip) {
		this.shipperZip = shipperZip;
	}


	public String getConsignee() {
		return consignee;
	}


	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}


	public String getConsigneePro() {
		return consigneePro;
	}


	public void setConsigneePro(String consigneePro) {
		this.consigneePro = consigneePro;
	}


	public String getConsigneeCity() {
		return consigneeCity;
	}


	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}


	public String getConsigneeDistrict() {
		return consigneeDistrict;
	}


	public void setConsigneeDistrict(String consigneeDistrict) {
		this.consigneeDistrict = consigneeDistrict;
	}


	public String getConsigneeAdd() {
		return consigneeAdd;
	}


	public void setConsigneeAdd(String consigneeAdd) {
		this.consigneeAdd = consigneeAdd;
	}


	public String getConsigneeMobile() {
		return consigneeMobile;
	}


	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}


	public String getConsigneeTel() {
		return consigneeTel;
	}


	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}


	public String getConsigneeCountry() {
		return consigneeCountry;
	}


	public void setConsigneeCountry(String consigneeCountry) {
		this.consigneeCountry = consigneeCountry;
	}


	public String getConsigneeZip() {
		return consigneeZip;
	}


	public void setConsigneeZip(String consigneeZip) {
		this.consigneeZip = consigneeZip;
	}


	public BigDecimal getWeigtht() {
		return weigtht;
	}


	public void setWeigtht(BigDecimal weigtht) {
		this.weigtht = weigtht;
	}


	public String getLotNo() {
		return lotNo;
	}


	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}


	public BigDecimal getNetWeight() {
		return netWeight;
	}


	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}


	public List<MultipleItems> getItems() {
		return items;
	}


	public void setItems(List<MultipleItems> items) {
		this.items = items;
	}


	public String getLogisCompanyCode() {
		return logisCompanyCode;
	}


	public void setLogisCompanyCode(String logisCompanyCode) {
		this.logisCompanyCode = logisCompanyCode;
	}


	public String getLogisCompanyName() {
		return logisCompanyName;
	}


	public void setLogisCompanyName(String logisCompanyName) {
		this.logisCompanyName = logisCompanyName;
	}







	






}
