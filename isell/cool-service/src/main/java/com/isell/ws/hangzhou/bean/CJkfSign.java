package com.isell.ws.hangzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 签名信息
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class CJkfSign {
	
	/** 发送方海关十位数编码 */
	@XmlElement
	private String companyCode;
	/** 业务编号(订单编号) */
	@XmlElement
	private String businessNo;
	/** 业务类型（IMPORTORDER） */
	@XmlElement
	private String businessType;
	/** 申报类型（固定填写1）1.新增 2.修改  */
	@XmlElement
	private String declareType;
	/** 备注 */
	@XmlElement
	private String note;
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getDeclareType() {
		return declareType;
	}
	public void setDeclareType(String declareType) {
		this.declareType = declareType;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
