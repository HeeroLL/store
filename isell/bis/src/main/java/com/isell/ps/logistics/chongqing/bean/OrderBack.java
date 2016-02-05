package com.isell.ps.logistics.chongqing.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="DTC_Message")
public class OrderBack {
	@XmlElement(name="MessageHead")
	private MessageHead messageHead;
	@XmlElement(name="MessageBody")
	private MessageBody messageBody;
	public MessageHead getMessageHead() {
		return messageHead;
	}
	public void setMessageHead(MessageHead messageHead) {
		this.messageHead = messageHead;
	}
	public MessageBody getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(MessageBody messageBody) {
		this.messageBody = messageBody;
	}
}
