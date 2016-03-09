package com.isell.service.order.vo;

public class OrderReturn {
	
	private String returnurl;//回调地址
	private String orderno;//订单编号
	private String state;//订单修改之后的状态
	private String reason;//修改订单理由
	private String waybillNo;//物流编号
	private String sendStyle;//寄送方式 圆通 申通...
	private int pid;
	private int gid;
	private String price;
	private int checkType;
	private int recid;
	
	
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public int getCheckType() {
		return checkType;
	}
	public void setCheckType(int checkType) {
		this.checkType = checkType;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getPrice() {
		if(price==null)
		{
			price="";
		}
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getReturnurl() {
		if(returnurl==null)
		{
			returnurl="";
		}
		return returnurl;
	}
	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getWaybillNo() {
		if(waybillNo==null)
		{
			waybillNo="";
		}
		return waybillNo;
	}
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}
	public String getSendStyle() {
		if(sendStyle==null)
		{
			sendStyle="";
		}
		return sendStyle;
	}
	public void setSendStyle(String sendStyle) {
		this.sendStyle = sendStyle;
	}
	
	
}
