package com.isell.demo.gztg.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Declaration{
	
	@XmlElement(name="EOrder")
	private EOrder eOrder;

	@XmlElement(name="EOrderGoods")
	private EOrderGoods eOrderGoods;

	public EOrder geteOrder() {
		return eOrder;
	}

	public void seteOrder(EOrder eOrder) {
		this.eOrder = eOrder;
	}

	public EOrderGoods geteOrderGoods() {
		return eOrderGoods;
	}

	public void seteOrderGoods(EOrderGoods eOrderGoods) {
		this.eOrderGoods = eOrderGoods;
	}

	
	
}
