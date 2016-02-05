package com.isell.demo.gztg.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Head {
	/* 报文编号 */
	@XmlElement(name="MessageID")
	private String messageID;
	/* 报文类型 */
	@XmlElement(name="MessageType")
	private String messageType;
	/* 接入企业备案号 */
	@XmlElement(name="SenderID")
	private String senderID;

	/* 报文日期 */
	@XmlElement(name="SendTime")
	private Date sendTime;
	/* 报文版本号 */
	@XmlElement(name="Version")
	private String version;

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

	public String getSenderID() {
		return senderID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
}
