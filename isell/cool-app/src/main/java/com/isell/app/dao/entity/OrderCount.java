package com.isell.app.dao.entity;

public class OrderCount {
	private int preSendNum;
	private int readySendNum;
	private int alreadySendNum;
	private int refundingNum;
	
	
	public int getPreSendNum() {
		return preSendNum;
	}
	public void setPreSendNum(int preSendNum) {
		this.preSendNum = preSendNum;
	}
	public int getReadySendNum() {
		return readySendNum;
	}
	public void setReadySendNum(int readySendNum) {
		this.readySendNum = readySendNum;
	}
	public int getAlreadySendNum() {
		return alreadySendNum;
	}
	public void setAlreadySendNum(int alreadySendNum) {
		this.alreadySendNum = alreadySendNum;
	}
	public int getRefundingNum() {
		return refundingNum;
	}
	public void setRefundingNum(int refundingNum) {
		this.refundingNum = refundingNum;
	}
}
