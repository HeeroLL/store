package com.isell.ei.logistics.huitong.bean;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Order")
public class Order {
	/*电子订单编号，不可重复*/
	@XmlElement(name="ENo")
	private String ENo;
	/*收件人姓名*/
	@XmlElement(name="ReName")
	private String reName;
	/*收件人身份证号*/
	@XmlElement(name="ReDocId")
	private String reDocId;
	/*收件人省份*/
	@XmlElement(name="ReProv")
	private String reProv;
	/*收件人城市*/
	@XmlElement(name="ReCity")
	private String reCity;
	
	@XmlElement(name="ReCode")
	private String reCode;
	/*收件人县/区*/
	@XmlElement(name="ReZone")
	private String reZone;
	/*收件人地址*/
	@XmlElement(name="ReAdress")
	private String reAdress;
	/*联系电话*/
	@XmlElement(name="RePhone")
	private String rePhone;
	/*物流公司*/
	@XmlElement(name="LogisticsEnt")
	private String logisticsEnt;
	/*运单号，如果为空的话，这边会从圆通取单号并返回，不可重复*/
	@XmlElement(name="WayBillNo")
	private String wayBillNo;
	/*运输方式*/
	@XmlElement(name="TransportType")
	private String transportType;
	/*发件人国别，默认香港，直邮必须，保税忽略*/
	@XmlElement(name="SeCountry")
	private String seCountry;
	
	/*发件人姓名，直邮必须，保税忽略*/
	@XmlElement(name="SeName")
	private String seName;
	/*币制*/
	@XmlElement(name="Currency")
	private String currency;
	/*运费，默认0*/
	@XmlElement(name="InsuranceValue")
	private BigDecimal insuranceValue;
	
	/*报价费，默认0*/
	@XmlElement(name="Freight")
	private BigDecimal freight;
	/*税款，默认0*/
	@XmlElement(name="Tax")
	private BigDecimal tax;
	/*备注*/
	@XmlElement(name="Note")
	private String note;
	
	@XmlElement(name="Details")
	private Detail detail;
	
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	public String getENo() {
		return ENo;
	}
	public void setENo(String eNo) {
		ENo = eNo;
	}
	public String getReCode() {
		return reCode;
	}
	public void setReCode(String reCode) {
		this.reCode = reCode;
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
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public String getSeCountry() {
		return seCountry;
	}
	public void setSeCountry(String seCountry) {
		this.seCountry = seCountry;
	}
	public String getSeName() {
		return seName;
	}
	public void setSeName(String seName) {
		this.seName = seName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getInsuranceValue() {
		return insuranceValue;
	}
	public void setInsuranceValue(BigDecimal insuranceValue) {
		this.insuranceValue = insuranceValue;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
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
	
	
	
}
