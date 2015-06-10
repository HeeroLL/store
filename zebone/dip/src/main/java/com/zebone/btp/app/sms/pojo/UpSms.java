package com.zebone.btp.app.sms.pojo;

import java.util.Date;

public class UpSms {
	private String upSmsId;

	private String mobileNo;

	private String content;

	private Date receiveTime;

	public String getUpSmsId() {
		return upSmsId;
	}

	public void setUpSmsId(String upSmsId) {
		this.upSmsId = upSmsId == null ? null : upSmsId.trim();
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo == null ? null : mobileNo.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
}