package com.isell.ps.logistics.chongqing.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class GoodsMessageHead {
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
	 
}
