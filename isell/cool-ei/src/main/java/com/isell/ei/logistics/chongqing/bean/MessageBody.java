package com.isell.ei.logistics.chongqing.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageBody {
	
	@XmlElement(name="DTCFlow")
	private DtcFlow dtcFlow;

	public DtcFlow getDtcFlow() {
		return dtcFlow;
	}

	public void setDtcFlow(DtcFlow dtcFlow) {
		this.dtcFlow = dtcFlow;
	}
	
	
}
