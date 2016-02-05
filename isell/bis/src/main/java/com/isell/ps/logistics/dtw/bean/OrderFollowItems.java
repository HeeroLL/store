package com.isell.ps.logistics.dtw.bean;

import org.codehaus.jackson.annotate.JsonProperty;

public class OrderFollowItems {
	/*扫描时间*/
	@JsonProperty("acceptTime")
	private String acceptTime;
	/*扫描地址*/
	@JsonProperty("acceptAddress")
	private String acceptAddress;
	/*说明*/
	@JsonProperty("remark")
	private String remark;
	/*联系人*/
	@JsonProperty("scanPrincipal")
	private String scanPrincipal;
	/*联系单号*/
	@JsonProperty("scanPhone")
	private String scanPhone;

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getAcceptAddress() {
		return acceptAddress;
	}

	public void setAcceptAddress(String acceptAddress) {
		this.acceptAddress = acceptAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getScanPrincipal() {
		return scanPrincipal;
	}

	public void setScanPrincipal(String scanPrincipal) {
		this.scanPrincipal = scanPrincipal;
	}

	public String getScanPhone() {
		return scanPhone;
	}

	public void setScanPhone(String scanPhone) {
		this.scanPhone = scanPhone;
	}
	
	
	
}
