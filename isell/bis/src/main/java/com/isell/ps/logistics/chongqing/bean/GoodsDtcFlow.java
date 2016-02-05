package com.isell.ps.logistics.chongqing.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GoodsDtcFlow {
	@XmlElement(name="MesAskInfo")
	private GoodsMesAskInfo goodsMesAskInfo;

	public GoodsMesAskInfo getGoodsMesAskInfo() {
		return goodsMesAskInfo;
	}

	public void setGoodsMesAskInfo(GoodsMesAskInfo goodsMesAskInfo) {
		this.goodsMesAskInfo = goodsMesAskInfo;
	}
	
}
