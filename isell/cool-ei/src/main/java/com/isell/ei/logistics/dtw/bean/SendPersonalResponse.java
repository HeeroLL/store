package com.isell.ei.logistics.dtw.bean;

import org.codehaus.jackson.annotate.JsonProperty;

public class SendPersonalResponse {
	@JsonProperty("errCode")
	private String errCode;
	@JsonProperty("errMsg")
	private String errMsg;
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
