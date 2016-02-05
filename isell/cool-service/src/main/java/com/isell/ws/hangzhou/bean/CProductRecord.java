package com.isell.ws.hangzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 产品备案信息
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class CProductRecord {

	/** 签名信息 */
	@XmlElement(name = "jkfSign")
	private CJkfSign jkfSign;
	/** 产品备案信息对象 */
	@XmlElement(name = "productRecordDto")
	private CProductRecordDto productRecordDto;
	
	public CJkfSign getJkfSign() {
		return jkfSign;
	}
	public void setJkfSign(CJkfSign jkfSign) {
		this.jkfSign = jkfSign;
	}
	public CProductRecordDto getProductRecordDto() {
		return productRecordDto;
	}
	public void setProductRecordDto(CProductRecordDto productRecordDto) {
		this.productRecordDto = productRecordDto;
	}
}
