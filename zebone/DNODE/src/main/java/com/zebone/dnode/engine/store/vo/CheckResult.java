package com.zebone.dnode.engine.store.vo;

/**
 * 文档检验结果基类
 * 蔡祥龙
 * 2013-08-13
 */
public class CheckResult {
	/**主键id*/
	private String id;
	/**文档存储*/
	private String docXml;
	/**文档校验状态*/
	private String checkFlag;
	/**文档存储状态*/
	private String storageFlag;
	/**存储错误返回结果*/
	private String errorDesc;
	/**文档编号*/
	private String docNo;
	/**文档来源机构*/
	private String docOrgCode;
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
		return storageFlag;
	}
	public void setStorageFlag(String storageFlag) {
		this.storageFlag = storageFlag;
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
	public String getDocOrgCode() {
		return docOrgCode;
	}
	public void setDocOrgCode(String docOrgCode) {
		this.docOrgCode = docOrgCode;
	}
}
