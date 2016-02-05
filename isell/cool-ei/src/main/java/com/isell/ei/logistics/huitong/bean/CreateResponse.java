package com.isell.ei.logistics.huitong.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Response")
public class CreateResponse {
	
	@XmlElement(name="Success")
	private Boolean success;
	@XmlElement(name="NoticeMessage")
	private String noticeMessage;
	@XmlElement(name="response")
	private CreateResponses response;
	
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
	public CreateResponses getResponse() {
		return response;
	}
	public void setResponse(CreateResponses response) {
		this.response = response;
	}
	
	
}
