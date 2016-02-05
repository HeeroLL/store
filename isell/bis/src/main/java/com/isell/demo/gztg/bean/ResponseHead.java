package com.isell.demo.gztg.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseHead {
	
	/*接入企业备案号*/
	@XmlElement(name="SendID")
	private String senderID;
	/*接入企业备案号*/
	@XmlElement(name="MessageID")
	private String messageID;
	/*接入企业备案号*/
	@XmlElement(name="MessageType")
	private String messageType;
	/*接入企业备案号*/
	@XmlElement(name="ReturnDate")
	private Date returnDate;
	/*接入企业备案号*/
	@XmlElement(name="OldMessageId")
	private String oldMessageId;
	/*接入企业备案号*/
	@XmlElement(name="AttachedFlag")
	private String attachedFlag;
	public String getSenderID() {
		return senderID;
	}
	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}
	public String getMessageID() {
		return messageID;
	}
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getOldMessageId() {
		return oldMessageId;
	}
	public void setOldMessageId(String oldMessageId) {
		this.oldMessageId = oldMessageId;
	}
	public String getAttachedFlag() {
		return attachedFlag;
	}
	public void setAttachedFlag(String attachedFlag) {
		this.attachedFlag = attachedFlag;
	}
	
}
