package com.zebone.btp.app.sms.pojo;

import java.util.Date;

public class SmsReceiver {
	private String smsReceiverId;

	private String smsContentId;

	private String mobileNo;

	private String receiverId;

	private String receiverName;

	private Date sendTime;

	private Date reveiveTime;

	private Short state;

	private String stateDesc;

	public String getSmsReceiverId() {
		return smsReceiverId;
	}

	public void setSmsReceiverId(String smsReceiverId) {
		this.smsReceiverId = smsReceiverId == null ? null : smsReceiverId.trim();
	}

	public String getSmsContentId() {
		return smsContentId;
	}

	public void setSmsContentId(String smsContentId) {
		this.smsContentId = smsContentId == null ? null : smsContentId.trim();
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo == null ? null : mobileNo.trim();
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId == null ? null : receiverId.trim();
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName == null ? null : receiverName.trim();
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getReveiveTime() {
		return reveiveTime;
	}

	public void setReveiveTime(Date reveiveTime) {
		this.reveiveTime = reveiveTime;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc == null ? null : stateDesc.trim();
	}
}