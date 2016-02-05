package com.isell.ei.logistics.dtw.bean;

import org.codehaus.jackson.annotate.JsonProperty;
public class MultipleItems {

	/*供应商编码（电商平台下的商家备案编号）*/
	@JsonProperty("Supplier")
	private String supplier;
	/*项次（行号）*/
	@JsonProperty("Msgitem")
	private String msgitem;
	/*产品编码*/
	@JsonProperty("Partno")
	private String partno;
	/*货物名称*/
	@JsonProperty("PartName")
	private String partName;
	/*货物规格*/
	@JsonProperty("Spec")
	private String spec;
	/*批次*/
	@JsonProperty("Batch")
	private String batch;
	/*数量*/
	@JsonProperty("Qty")
	private String qty;
	/*单位*/
	@JsonProperty("Unit")
	private String unit;
	/*备用字段*/
	@JsonProperty("Dref1")
	private String dref1;
	/*备用字段*/
	@JsonProperty("Dref2")
	private String dref2;
	/*备用字段*/
	@JsonProperty("Dref3")
	private String dref3;
	/*备用字段*/
	@JsonProperty("Dref4")
	private String dref4;
	/*币种编码*/
	@JsonProperty("Currency")
	private String currency;
	/*金额*/
	@JsonProperty("Amount")
	private String amount;
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getMsgitem() {
		return msgitem;
	}
	public void setMsgitem(String msgitem) {
		this.msgitem = msgitem;
	}
	public String getPartno() {
		return partno;
	}
	public void setPartno(String partno) {
		this.partno = partno;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDref1() {
		return dref1;
	}
	public void setDref1(String dref1) {
		this.dref1 = dref1;
	}
	public String getDref2() {
		return dref2;
	}
	public void setDref2(String dref2) {
		this.dref2 = dref2;
	}
	public String getDref3() {
		return dref3;
	}
	public void setDref3(String dref3) {
		this.dref3 = dref3;
	}
	public String getDref4() {
		return dref4;
	}
	public void setDref4(String dref4) {
		this.dref4 = dref4;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
}
