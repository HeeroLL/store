package com.isell.ei.shop.kalemao.bean;

import org.codehaus.jackson.annotate.JsonProperty;

public class KalemaoResult {
	@JsonProperty("success")
	private String success;
	@JsonProperty("msg")
	private String msg;
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
