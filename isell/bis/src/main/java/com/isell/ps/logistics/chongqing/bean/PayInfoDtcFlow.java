package com.isell.ps.logistics.chongqing.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class PayInfoDtcFlow {
	@XmlElement(name="PayMementInfo")
	private List<PayInfoMesAskInfo> mesAskInfo;

	public List<PayInfoMesAskInfo> getMesAskInfo() {
		return mesAskInfo;
	}

	public void setMesAskInfo(List<PayInfoMesAskInfo> mesAskInfo) {
		this.mesAskInfo = mesAskInfo;
	}




}
