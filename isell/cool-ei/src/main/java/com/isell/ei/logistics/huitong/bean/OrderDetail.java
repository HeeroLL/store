package com.isell.ei.logistics.huitong.bean;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetail {
	/*汇通商品编码*/
	@XmlElement(name="ItemNo")
	private String itemNo;
	/*数量*/
	@XmlElement(name="Qty")
	private BigDecimal 	qty;
	/*价格*/
	@XmlElement(name="Price")
	private BigDecimal price;
	/*备注*/
	@XmlElement(name="Note")
	private String note;
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
