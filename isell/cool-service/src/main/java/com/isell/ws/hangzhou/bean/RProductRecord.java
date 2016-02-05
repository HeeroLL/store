package com.isell.ws.hangzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 产品登记审批结果信息
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class RProductRecord {

	/** 产品国检备案编号 */
	@XmlElement
	private String productRecordNo;
	/** 审批结果 P2:通过 P3:未通过 */
	@XmlElement
	private String approveResult;
	/** 审批意见 */
	@XmlElement
	private String approveComment;
	/** 处理时间 格式：2014-02-18 15:58:11 */
	@XmlElement
	private String processTime;
	
	public String getProductRecordNo() {
		return productRecordNo;
	}
	public void setProductRecordNo(String productRecordNo) {
		this.productRecordNo = productRecordNo;
	}
	public String getApproveResult() {
		return approveResult;
	}
	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}
	public String getApproveComment() {
		return approveComment;
	}
	public void setApproveComment(String approveComment) {
		this.approveComment = approveComment;
	}
	public String getProcessTime() {
		return processTime;
	}
	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}
}
