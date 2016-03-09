package com.isell.ei.logistics.dtw.bean;

import org.codehaus.jackson.annotate.JsonProperty;

public class MultipleResponse {
	
	@JsonProperty("ErrCode")
	private String errCode;
	@JsonProperty("ErrMsg")
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
