package com.isell.ps.logistics.chongqing.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="DTC_Message")
public class PayInfo {
	@XmlElement(name="MessageHead")
	private PayInfoMessageHead messageHead;
	@XmlElement(name="MessageBody")
	private PayInfoMessageBody messageBody;
	public PayInfoMessageHead getMessageHead() {
		return messageHead;
	}
	public void setMessageHead(PayInfoMessageHead messageHead) {
		this.messageHead = messageHead;
	}
	public PayInfoMessageBody getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(PayInfoMessageBody messageBody) {
		this.messageBody = messageBody;
	}
	
}
