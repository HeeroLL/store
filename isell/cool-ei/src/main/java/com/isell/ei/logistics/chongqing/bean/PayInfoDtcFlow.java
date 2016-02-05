package com.isell.ei.logistics.chongqing.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class PayInfoDtcFlow {
	@XmlElement(name="PAYMENT_INFO")
	private List<PayMementInfo> paymementInfo;

	public List<PayMementInfo> getPaymementInfo() {
		return paymementInfo;
	}

	public void setPaymementInfo(List<PayMementInfo> paymementInfo) {
		this.paymementInfo = paymementInfo;
	} 


}
