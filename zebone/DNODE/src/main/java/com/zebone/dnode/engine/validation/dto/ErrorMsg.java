package com.zebone.dnode.engine.validation.dto;

/**
 * 错误信息
 * @author 陈阵 
 * @date 2013-7-31 下午2:06:16
 */
public class ErrorMsg {
	
	/** 文档类型编号 **/
	private String docTypeCode;
	
	/** 文档编号 **/
	private String docCode;
	
	/** 文档来源机构 **/
	private String docOrgCode;
	
	/** 开始时间 **/
	private String startTime;
	
	/** 结束时间 **/
	private String endTime;
	
	/** 校验存储文档 **/
	private String docXml;
	
	/** 文档版本  **/
	private String docVersion;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDocXml() {
		return docXml;
	}

	public void setDocXml(String docXml) {
		this.docXml = docXml;
	}
    
	
	public String getDocTypeCode() {
		return docTypeCode;
	}

	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getDocOrgCode() {
		return docOrgCode;
	}

	public void setDocOrgCode(String docOrgCode) {
		this.docOrgCode = docOrgCode;
	}

	public String getDocVersion() {
		return docVersion;
	}

	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}
	
}
