package com.isell.ps.logistics.dtw.bean;


public class ReceiptItems {
	/*项次（行号）即订单上面的项次*/
	/*@JsonProperty("Msgitem")*/
	private String msgitem;
	/*产品编码*/
	/*@JsonProperty("Partno")*/
	private String partno;
	/*货物名称*/
	/*@JsonProperty("Partname")*/
	private String partname;
	/*数量*/
/*	@JsonProperty("Qty")*/
	private String qty;

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
	
	
}
