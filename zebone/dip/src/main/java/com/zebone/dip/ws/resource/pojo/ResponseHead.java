package com.zebone.dip.ws.resource.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("head")
public class ResponseHead {

	private String requestId;
	
	private String tradeNo;
	
	private String sucess;
	
	private String errorCode;
	
	private String errorDesc;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getSucess() {
		return sucess;
	}

	public void setSucess(String sucess) {
		this.sucess = sucess;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
}
