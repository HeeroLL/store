package com.isell.ei.logistics.huitong.bean;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

public class DetailsResponse {
	@JsonProperty("ItemNo")
	private String itemNo;
	@JsonProperty("Qty")
	private BigDecimal qty;
	@JsonProperty("Price")
	private BigDecimal price;
	@JsonProperty("Note")
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
