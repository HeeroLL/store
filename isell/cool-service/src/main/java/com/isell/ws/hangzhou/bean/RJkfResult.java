package com.isell.ws.hangzhou.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * 处理结果
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class RJkfResult {

	/** 企业编码 */
	@XmlElement
	private String companyCode;	
	/** 业务编号 */
	@XmlElement
	private String businessNo;
	/** 业务类型 */
	@XmlElement
	private String businessType;
	/** 申报类型 */
	@XmlElement
	private String declareType;
	/** 处理结果 */
	@XmlElement
	private String chkMark;
	/** 通知日期 */
	@XmlElement
	private String noticeDate;
	/** 通知时间 */
	@XmlElement
	private String noticeTime;
	/** 备注 */
	@XmlElement
	private String note;
	@XmlElementWrapper(name = "resultList")  
    @XmlElement(name = "jkfOrderDetail") 
	private List<RJkfResultDetail> resultDetails = new ArrayList<RJkfResultDetail>();
	
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
	public String getChkMark() {
		return chkMark;
	}
	public void setChkMark(String chkMark) {
		this.chkMark = chkMark;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	public String getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<RJkfResultDetail> getResultDetails() {
		return resultDetails;
	}
	public void setResultDetails(List<RJkfResultDetail> resultDetails) {
		this.resultDetails = resultDetails;
	}
	
}
