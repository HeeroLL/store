package com.isell.ps.logistics.chongqing.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class PayInfoMessageHead {
	@XmlElement(name="MessageType")
	private String messageType;
	/*唯一ID 自己发送*/
	@XmlElement(name="MessageId")
	private String messageId;
	@XmlElement(name="ActionType")
	private String actionType;
	/*时间*/
	@XmlElement(name="MessageTime")
	private Date messageTime;
	/*海关10位编码*/
	@XmlElement(name="SenderId")
	private String senderId;
	@XmlElement(name="ReceiverId")
	private String receiverId;
	@XmlElement(name="SenderAddress")
	private String senderAddress;
	@XmlElement(name="ReceiverAddress")
	private String receiverAddress;
	@XmlElement(name="PlatFormNo")
	private String platFormNo;
	@XmlElement(name="CustomCode")
	private String customCode;
	@XmlElement(name="Note")
	private String note;
	@XmlElement(name="seqNo")
	private String seqNo;
	/*海关10位编码*/
	@XmlElement(name="UserNo")
	private String userNo;
	/*加密后的字符串*/
	@XmlElement(name="Password")
	private String password;
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public Date getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getPlatFormNo() {
		return platFormNo;
	}
	public void setPlatFormNo(String platFormNo) {
		this.platFormNo = platFormNo;
	}
	public String getCustomCode() {
		return customCode;
	}
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	
	
}
