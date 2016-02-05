package com.isell.ei.logistics.huitong.bean;


import org.codehaus.jackson.annotate.JsonProperty;
public class StockResponse {
	@JsonProperty("Success")
	private Boolean success;
	@JsonProperty("NoticeMessage")
	private String noticeMessage;
	@JsonProperty("response")
	private Integer response; 
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getNoticeMessage() {
		return noticeMessage;
	}
	public void setNoticeMessage(String noticeMessage) {
		this.noticeMessage = noticeMessage;
	}
	public Integer getResponse() {
		return response;
	}
	public void setResponse(Integer response) {
		this.response = response;
	}
	
	
}
