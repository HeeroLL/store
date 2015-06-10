package com.zebone.dip.tool.vo;

/**
 * 文档信息
 * @author YinCM
 * @since
 */
public class DocInfo {
	//文档主键
	private String id;
	//文档名称
	private String docName;
	//文档版本
	private String docVersion;
	//文档XML数据
	private String docXML;
	//文档编号
	private String docTypeCode;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocVersion() {
		if(docVersion==null){
			return "";
		}
		return docVersion;
	}
	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}
	public String getDocXML() {
		return docXML;
	}
	public void setDocXML(String docXML) {
		this.docXML = docXML;
	}
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	
}
