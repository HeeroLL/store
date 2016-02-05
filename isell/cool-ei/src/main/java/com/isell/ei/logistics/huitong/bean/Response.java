package com.isell.ei.logistics.huitong.bean;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Response {
	/*电子订单编号，不可重复*/
	@JsonProperty("ENo")
	private String eNo;
	/*币制*/
	@JsonProperty("Currency")
	private String currency;
	/*收件人姓名*/
	@JsonProperty("ReName")
	private String reName;
	/*收件人身份证号*/
	@JsonProperty("ReDocId")
	private String reDocId;
	/*收件人省份*/
	@JsonProperty("ReProv")
	private String reProv;
	/*收件人城市*/
	@JsonProperty("ReCity")
	private String reCity;
	@JsonProperty("ReCode")
	private String reCode;
	/*收件人县/区*/
	@JsonProperty("ReZone")
	private String reZone;
	/*收件人地址*/
	@JsonProperty("ReAddress")
	private String reAdress;
	/*联系电话*/
	@JsonProperty("RePhone")
	private String rePhone;
	/*物流公司*/
	@JsonProperty("LogisticsEnt")
	private String logisticsEnt;
	/*运单号，如果为空的话，这边会从圆通取单号并返回，不可重复*/
	@JsonProperty("WayBillNo")
	private String wayBillNo;
	/*报价费，默认0*/
	@JsonProperty("Freight")
	private BigDecimal freight;
	/*运费，默认0*/
	@JsonProperty("InsuranceValue")
	private BigDecimal insuranceValue;
	/*税款，默认0*/
	@JsonProperty("Tax")
	private BigDecimal tax;
	@JsonProperty("Note")
	private String note;
	@JsonProperty("Details")
	private List<DetailsResponse> details;
	@JsonProperty("State")
	private String state;
	
	public String geteNo() {
		return eNo;
	}
	public void seteNo(String eNo) {
		this.eNo = eNo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getReName() {
		return reName;
	}
	public void setReName(String reName) {
		this.reName = reName;
	}
	public String getReDocId() {
		return reDocId;
	}
	public void setReDocId(String reDocId) {
		this.reDocId = reDocId;
	}
	public String getReProv() {
		return reProv;
	}
	public void setReProv(String reProv) {
		this.reProv = reProv;
	}
	public String getReCity() {
		return reCity;
	}
	public void setReCity(String reCity) {
		this.reCity = reCity;
	}
	public String getReCode() {
		return reCode;
	}
	public void setReCode(String reCode) {
		this.reCode = reCode;
	}
	public String getReZone() {
		return reZone;
	}
	public void setReZone(String reZone) {
		this.reZone = reZone;
	}
	public String getReAdress() {
		return reAdress;
	}
	public void setReAdress(String reAdress) {
		this.reAdress = reAdress;
	}
	public String getRePhone() {
		return rePhone;
	}
	public void setRePhone(String rePhone) {
		this.rePhone = rePhone;
	}
	public String getLogisticsEnt() {
		return logisticsEnt;
	}
	public void setLogisticsEnt(String logisticsEnt) {
		this.logisticsEnt = logisticsEnt;
	}
	public String getWayBillNo() {
		return wayBillNo;
	}
	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public BigDecimal getInsuranceValue() {
		return insuranceValue;
	}
	public void setInsuranceValue(BigDecimal insuranceValue) {
		this.insuranceValue = insuranceValue;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<DetailsResponse> getDetails() {
		return details;
	}
	public void setDetails(List<DetailsResponse> details) {
		this.details = details;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}	
