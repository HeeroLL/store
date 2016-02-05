package com.isell.ps.logistics.dtw.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class OrderFollow {
	/*电商企业发货订单号\销售订单号*/
	@JsonProperty("Msgid")
	private String msgid;
	/*运单号*/
	@JsonProperty("wayBill")
	private String wayBill;
	
	
	@JsonProperty("Items")
	private List<OrderFollowItems> item;


	public String getMsgid() {
		return msgid;
	}


	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}


	public String getWayBill() {
		return wayBill;
	}


	public void setWayBill(String wayBill) {
		this.wayBill = wayBill;
	}


	public List<OrderFollowItems> getItem() {
		return item;
	}


	public void setItem(List<OrderFollowItems> item) {
		this.item = item;
	}
	
	
	
}
