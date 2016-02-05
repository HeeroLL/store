package com.isell.ps.logistics.dtw.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Send {
	
	@JsonProperty("Msgackid")
	private String msgackid;
	
	@JsonProperty("Inputdate")
	private String inputdate;
	
	@JsonProperty("Items")
	private List<SendItems> items;

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

	public List<SendItems> getItems() {
		return items;
	}

	public void setItems(List<SendItems> items) {
		this.items = items;
	}

	
	
}
