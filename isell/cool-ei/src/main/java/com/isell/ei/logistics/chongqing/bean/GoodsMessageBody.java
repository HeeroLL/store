package com.isell.ei.logistics.chongqing.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class GoodsMessageBody {
	@XmlElement(name="DTCFlow")
	private GoodsDtcFlow dtcFlow;

	public GoodsDtcFlow getDtcFlow() {
		return dtcFlow;
	}

	public void setDtcFlow(GoodsDtcFlow dtcFlow) {
		this.dtcFlow = dtcFlow;
	}

	
}
