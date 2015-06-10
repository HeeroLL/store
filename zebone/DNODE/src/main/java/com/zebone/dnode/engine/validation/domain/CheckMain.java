package com.zebone.dnode.engine.validation.domain;

import org.apache.ibatis.type.Alias;


/**
 * 校验主表
 * @author 陈阵 
 * @date 2013-8-1 上午9:14:48
 */
@Alias("checkMain")
public class CheckMain {
	
	/** 主键 **/
	private String id;
	
	/** 文档类型ID **/
	private String docTypeCode;
	
	/** 文档编号  **/
	private String docCode;
	
	/** 文档来源机构 **/
	private String docOrgCode;
	
	/** 文档校验标志 **/
	private String checkFlag;
	
	/** 校验开始时间 **/
	private String startTime;
	
    /** 校验结束时间 **/
	private String endTime;
	
	/** 错误文档存储（正确文档不用存储）**/
	private String docXml;
	
	/** 文档版本   **/
	private String docVersion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

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

	public String getDocVersion() {
		return docVersion;
	}

	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}
	
	
}
