package com.zebone.dnode.engine.analyze.vo;

import java.util.Date;

/**
 * 日志对象
 * @author songjunjie
 * @version 2013-8-16 上午08:18:13
 */
public class DocAnalyzeLog {
	private String id;
	/**
	 * 事件类型
	 */
	private String eventType;
	/**
	 * 异常值
	 */
	private String errorException;
	/**
	 * 错误描述
	 */
	private String errorDesc;
	/**
	 *文档代码
	 */
	private String docNo;
	/**
	 * 文档类型代码
	 */
	private String docTypeCode;
	/**
	 * 文档机构
	 */
	private String docOrgan;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getErrorException() {
		return errorException;
	}

	public void setErrorException(String errorException) {
		this.errorException = errorException;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDocTypeCode() {
		return docTypeCode;
	}

	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}

	public String getDocOrgan() {
		return docOrgan;
	}

	public void setDocOrgan(String docOrgan) {
		this.docOrgan = docOrgan;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
