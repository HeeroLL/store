package com.isell.demo.gztg.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EOrderGood {
	/*商品序号*/
	@XmlElement(name="GNo")
	private Number gNo;
	/*子订单编号*/
	@XmlElement(name="ChildOrderNo")
	private String childOrderNo;
	/*电商商户企业备案号*/
	@XmlElement(name="StoreRecordNo")
	private String storeRecordNo;
	/*电商商户企业名称*/
	@XmlElement(name="StoreRecordName")
	private String storeRecordName;
	/*报文类型*/
	@XmlElement(name="CopGNo")
	private String copGNo;
	/*商品海关备案号*/
	@XmlElement(name="CustomsListNO")
	private String customsListNO ;
	/*商品单价*/
	@XmlElement(name="DecPrice")
	private Number decPrice;
	/*计量单位*/
	@XmlElement(name="Unit")
	private String unit;
	/*商品数量*/
	@XmlElement(name="GQty")
	private Number gQty;
	/*商品总价*/
	@XmlElement(name="DeclTotal")
	private Number declTotal;
	/*备注*/
	@XmlElement(name="Notes")
	private String notes;
	public Number getgNo() {
		return gNo;
	}
	public void setgNo(Number gNo) {
		this.gNo = gNo;
	}
	public String getChildOrderNo() {
		return childOrderNo;
	}
	public void setChildOrderNo(String childOrderNo) {
		this.childOrderNo = childOrderNo;
	}
	public String getStoreRecordNo() {
		return storeRecordNo;
	}
	public void setStoreRecordNo(String storeRecordNo) {
		this.storeRecordNo = storeRecordNo;
	}
	public String getStoreRecordName() {
		return storeRecordName;
	}
	public void setStoreRecordName(String storeRecordName) {
		this.storeRecordName = storeRecordName;
	}
	public String getCustomsListNO() {
		return customsListNO;
	}
	public void setCustomsListNO(String customsListNO) {
		this.customsListNO = customsListNO;
	}
	public Number getDecPrice() {
		return decPrice;
	}
	public void setDecPrice(Number decPrice) {
		this.decPrice = decPrice;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Number getgQty() {
		return gQty;
	}
	public void setgQty(Number gQty) {
		this.gQty = gQty;
	}
	public Number getDeclTotal() {
		return declTotal;
	}
	public void setDeclTotal(Number declTotal) {
		this.declTotal = declTotal;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
