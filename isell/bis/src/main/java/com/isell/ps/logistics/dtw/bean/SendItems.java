package com.isell.ps.logistics.dtw.bean;

import org.codehaus.jackson.annotate.JsonProperty;


public class SendItems {
	/*项次（行号）*/
	@JsonProperty("Msgitem")
	private String msgitem;
	/*产品编码*/
	@JsonProperty("Partno")
	private String partno;
	/*货品名称）*/
	@JsonProperty("Partname")
	private String partname;
	/*数量）*/
	@JsonProperty("Qty")
	private String qty;
	/*金额*/
	@JsonProperty("Amount")
	private String amount;

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

	public String getPartname() {
		return partname;
	}

	public void setPartname(String partname) {
		this.partname = partname;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
