package com.isell.ps.logistics.dtw.bean;

import java.util.List;

public class Receipt {
	/*电商企业收货单号（Msgid）*/
	/* @JsonProperty("Msgackid")*/
	 private String msgackid;
	 /*发送时间*/
	 /*@JsonProperty("Inputdate")*/
	 private String inputdate;
	 
/*	 @JsonProperty("Items")
*/	 private List<ReceiptItems> items;

	public String getMsgackid() {
		return msgackid;
	}

	public void setMsgackid(String msgackid) {
		this.msgackid = msgackid;
	}

	public String getInputdate() {
		return inputdate;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

	public List<ReceiptItems> getItems() {
		return items;
	}

	public void setItems(List<ReceiptItems> items) {
		this.items = items;
	}
	 
	 
}
