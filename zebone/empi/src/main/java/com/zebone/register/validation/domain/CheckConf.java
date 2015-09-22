package com.zebone.register.validation.domain;


/**
 * 类描述：校验开关信息
 * @author: caixl
 * @date： 日期：Sep 3, 2013
 * @version 1.0
 */

public class CheckConf {
	/**主键id*/
	private String id;
	/**文档类型ID*/
	private String docId;
	/**文档名称*/
	private String docName;
	/**可选性开关*/
	private String isSelect;
	/**重复性开关*/
	private String isRepeat;
	/**数据格式开关*/
	private String isDataFormat;
	/**业务格式开关*/
	private String isBusiFormat;
	/**唯一性开关*/
	private String isOnly;
	/**值域检查开关*/
	private String isValue;
	/**冗余字段一*/
	private String blank0;
	/**冗余字段二*/
	private String blank1;
	/**权限检查开关*/
	private String isJurisd;
	/**
	 * 文档类型编码
	 */
	private String docTypeCode;
	
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getIsSelect() {
		return isSelect;
	}
	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
	public String getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}
	public String getIsDataFormat() {
		return isDataFormat;
	}
	public void setIsDataFormat(String isDataFormat) {
		this.isDataFormat = isDataFormat;
	}
	public String getIsBusiFormat() {
		return isBusiFormat;
	}
	public void setIsBusiFormat(String isBusiFormat) {
		this.isBusiFormat = isBusiFormat;
	}
	public String getIsOnly() {
		return isOnly;
	}
	public void setIsOnly(String isOnly) {
		this.isOnly = isOnly;
	}
	public String getIsValue() {
		return isValue;
	}
	public void setIsValue(String isValue) {
		this.isValue = isValue;
	}
	public String getBlank0() {
		return blank0;
	}
	public void setBlank0(String blank0) {
		this.blank0 = blank0;
	}
	public String getBlank1() {
		return blank1;
	}
	public void setBlank1(String blank1) {
		this.blank1 = blank1;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getIsJurisd() {
		return isJurisd;
	}
	public void setIsJurisd(String isJurisd) {
		this.isJurisd = isJurisd;
	}
	
}
