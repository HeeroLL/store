package com.zebone.dnode.engine.reversebuild.dto;

import org.apache.ibatis.type.Alias;

@Alias("mappingInfo")
public class MappingInfo {

	 private String docTypeCode;
	 
	 private String xpath;
	 
	 private String columnName;

	public String getDocTypeCode() {
		return docTypeCode;
	}

	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	 
	 
	 
}
