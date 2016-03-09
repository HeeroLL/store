package com.isell.service.order.po;

import java.util.List;

public class SendPresonalInfo {
	private String passKey;
	/**电商企业发货单号**/
	private String msgid;
	/**账册编号**/
	private String accountBookNo;
	/**进口类型（0一般进口，1保税进口）**/
	private String importType;
	/**进出口日期**/
	private String inOutDateStr;
	/**运输工具名称**/
	private String trafName;
	/**运输工具航次(班)号**/
	private String voyageNo;
	/**电商企业编码(电商企业在跨境平台备案编码)**/
	private String eCommerceCode;
	/**电商企业名称**/
	private String eCommerceName;
	/**订单编号**/
	private String orderNo;
	/**分运单号**/
	private String wayBill;
	/**件数**/
	private String packNo;
	/**毛重（公斤）**/
	private String grossWeight;
	/**净重**/
	private String netWeight;
	/**备注**/
	private String remark;
	/**报关员代码**/
	private String declarantNo;
	/**发件人**/
	private String senderName;
	/**收件人姓名**/
	private String consignee;
	/**发件人城市**/
	private String senderCity;
	/**支付人证件类型**/
	private String paperType;
	/**支付人证件号**/
	private String paperNumber;
	/**价值**/
	private String worth;
	/**成交币制（三字代码）**/
	private String currCode;
	/**主要货物名称**/
	private String mainGName;
	/**区内企业编码**/
	private String internalAreaCompanyNo;
	/**区内企业名称**/
	private String internalAreaCompanyName;
	/**申请单编号**/
	private String applicationFormNo;
	/**发件人国别（三字代码）**/
	private String tradeCountry;
	/**贸易国别（起运地三字代码）**/
	private String senderCountry;
	private List<SendPresonalItems> items;
	
	public String getPassKey() {
		return passKey;
	}
	public void setPassKey(String passKey) {
		this.passKey = passKey;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getAccountBookNo() {
		return accountBookNo;
	}
	public void setAccountBookNo(String accountBookNo) {
		this.accountBookNo = accountBookNo;
	}
	public String getImportType() {
		return importType;
	}
	public void setImportType(String importType) {
		this.importType = importType;
	}
	public String getInOutDateStr() {
		return inOutDateStr;
	}
	public void setInOutDateStr(String inOutDateStr) {
		this.inOutDateStr = inOutDateStr;
	}
	public String getTrafName() {
		return trafName;
	}
	public void setTrafName(String trafName) {
		this.trafName = trafName;
	}
	public String getVoyageNo() {
		return voyageNo;
	}
	public void setVoyageNo(String voyageNo) {
		this.voyageNo = voyageNo;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getWayBill() {
		return wayBill;
	}
	public void setWayBill(String wayBill) {
		this.wayBill = wayBill;
	}
	public String getPackNo() {
		return packNo;
	}
	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}
	public String getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}
	public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeclarantNo() {
		return declarantNo;
	}
	public List<SendPresonalItems> getItems() {
		return items;
	}
	public void setItems(List<SendPresonalItems> items) {
		this.items = items;
	}
	public void setDeclarantNo(String declarantNo) {
		this.declarantNo = declarantNo;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getSenderCity() {
		return senderCity;
	}
	public void setSenderCity(String senderCity) {
		this.senderCity = senderCity;
	}
	public String getPaperType() {
		return paperType;
	}
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	public String getPaperNumber() {
		return paperNumber;
	}
	public void setPaperNumber(String paperNumber) {
		this.paperNumber = paperNumber;
	}
	public String getWorth() {
		return worth;
	}
	public void setWorth(String worth) {
		this.worth = worth;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public String getMainGName() {
		return mainGName;
	}
	public void setMainGName(String mainGName) {
		this.mainGName = mainGName;
	}
	public String getInternalAreaCompanyNo() {
		return internalAreaCompanyNo;
	}
	public void setInternalAreaCompanyNo(String internalAreaCompanyNo) {
		this.internalAreaCompanyNo = internalAreaCompanyNo;
	}
	public String getInternalAreaCompanyName() {
		return internalAreaCompanyName;
	}
	public void setInternalAreaCompanyName(String internalAreaCompanyName) {
		this.internalAreaCompanyName = internalAreaCompanyName;
	}
	public String getApplicationFormNo() {
		return applicationFormNo;
	}
	public void setApplicationFormNo(String applicationFormNo) {
		this.applicationFormNo = applicationFormNo;
	}
	public String getTradeCountry() {
		return tradeCountry;
	}
	public void setTradeCountry(String tradeCountry) {
		this.tradeCountry = tradeCountry;
	}
	public String getSenderCountry() {
		return senderCountry;
	}
	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}
	

}
