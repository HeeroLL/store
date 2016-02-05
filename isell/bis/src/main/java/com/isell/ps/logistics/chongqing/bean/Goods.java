package com.isell.ps.logistics.chongqing.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.isell.ps.logistics.chongqing.bean.GoodsMessageBody;
import com.isell.ps.logistics.chongqing.bean.GoodsMessageHead;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="DTC_Message")
public class Goods {
	@XmlElement(name="MessageHead")
	private GoodsMessageHead messageHead;
	@XmlElement(name="MessageBody")
	private GoodsMessageBody messageBody;
	public GoodsMessageHead getMessageHead() {
		return messageHead;
	}
	public void setMessageHead(GoodsMessageHead messageHead) {
		this.messageHead = messageHead;
	}
	public GoodsMessageBody getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(GoodsMessageBody messageBody) {
		this.messageBody = messageBody;
	}

}
