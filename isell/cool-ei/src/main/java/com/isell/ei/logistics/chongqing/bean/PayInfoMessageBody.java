package com.isell.ei.logistics.chongqing.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class PayInfoMessageBody {
	@XmlElement(name="DTCFlow")
	private PayInfoDtcFlow dtcFlow;

	public PayInfoDtcFlow getDtcFlow() {
		return dtcFlow;
	}

	public void setDtcFlow(PayInfoDtcFlow dtcFlow) {
		this.dtcFlow = dtcFlow;
	}
	
}
