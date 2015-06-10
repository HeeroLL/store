package com.zebone.btp.app.sms.pojo;

import java.util.Date;

public class SmsContent {
	private String smsContentId;

	private String content;

	private String senderId;

	private Date sendTime;

	public String getSmsContentId() {
		return smsContentId;
	}

	public void setSmsContentId(String smsContentId) {
		this.smsContentId = smsContentId == null ? null : smsContentId.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId == null ? null : senderId.trim();
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
}