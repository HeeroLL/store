package com.zebone.dnode.engine.validation.domain;

import org.apache.ibatis.type.Alias;


/**
 * 校验结果
 * @author 陈阵 
 * @date 2013-8-6 下午1:22:45
 */
@Alias("checkResult")
public class CheckResult {
	/** 主键  **/
	private String id;
	
	/** 文档存储内容  **/
	private String docXml;
	
	/** 文档校验状态（未校验 0，已校验 1）  **/
	private String checkFlag;
	
	/** 文档存储状态（未存储 0，已存储 1）  **/
	private String StorageFlag;
	
	/** 存储错误返回结果  **/
	private String errorDesc;
	
	/** 文档类型编号  **/
	private String docTypeCode;
	
	/** 文档编号   **/
	private String docCode;
	
	/** 文档来源机构  **/
	private String docOrgCode;
	
	/** 文档版本   **/
	private String docVersion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocXml() {
		return docXml;
	}

	public void setDocXml(String docXml) {
		this.docXml = docXml;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getStorageFlag() {
		return StorageFlag;
	}

	public void setStorageFlag(String storageFlag) {
		StorageFlag = storageFlag;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getDocTypeCode() {
		return docTypeCode;
	}

	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}

	public String getDocOrgCode() {
		return docOrgCode;
	}

	public void setDocOrgCode(String docOrgCode) {
		this.docOrgCode = docOrgCode;
	}

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getDocVersion() {
		return docVersion;
	}

	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}
	
}
