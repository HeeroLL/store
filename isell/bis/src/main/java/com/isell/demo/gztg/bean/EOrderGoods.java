package com.isell.demo.gztg.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EOrderGoods {
	
	@XmlElement(name="EOrderGood")
	private List<EOrderGood> eOrderGood;

	public List<EOrderGood> geteOrderGood() {
		return eOrderGood;
	}

	public void seteOrderGood(List<EOrderGood> eOrderGood) {
		this.eOrderGood = eOrderGood;
	}

	
	
}
