package com.isell.ws.hangzhou.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * 订单信息
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class COrderInfo {

	/** 签名信息 */
	@XmlElement(name = "jkfSign")
	private CJkfSign jkfSign;
	/** 订单信息 */
	@XmlElement(name = "jkfOrderImportHead")
	private CJkfOrderImportHead jkfOrderImportHead;
	/** 购买人信息 */
	@XmlElement(name = "jkfGoodsPurchaser")
	private CJkfGoodsPurchaser jkfGoodsPurchaser;
	/** 订单表体 */
	@XmlElementWrapper(name = "jkfOrderDetailList")  
    @XmlElement(name = "jkfOrderDetail") 
	private List<CJkfOrderDetail> jkfOrderDetails = new ArrayList<CJkfOrderDetail>();
	public CJkfSign getJkfSign() {
		return jkfSign;
	}
	public void setJkfSign(CJkfSign jkfSign) {
		this.jkfSign = jkfSign;
	}
	public CJkfOrderImportHead getJkfOrderImportHead() {
		return jkfOrderImportHead;
	}
	public void setJkfOrderImportHead(CJkfOrderImportHead jkfOrderImportHead) {
		this.jkfOrderImportHead = jkfOrderImportHead;
	}
	public CJkfGoodsPurchaser getJkfGoodsPurchaser() {
		return jkfGoodsPurchaser;
	}
	public void setJkfGoodsPurchaser(CJkfGoodsPurchaser jkfGoodsPurchaser) {
		this.jkfGoodsPurchaser = jkfGoodsPurchaser;
	}
	public List<CJkfOrderDetail> getJkfOrderDetails() {
		return jkfOrderDetails;
	}
	public void setJkfOrderDetails(List<CJkfOrderDetail> jkfOrderDetails) {
		this.jkfOrderDetails = jkfOrderDetails;
	}
}
