package com.zebone.dip.tool.vo;

public class DocFieldInfo {
	//元数据名称
	private String fieldName;
	//元数据标识
	private String fieldCode;
	//类型
	private String fieldFormat;
	private String fieldType;
	//必选
	private String isSelect;
	//重复
	private String repeat;
	//唯一
	private String isOnly;
	//值域
	private String fieldValue;
	//值域名称
	private String fieldValueName;
	//映射
	private String xpath;
	//类型名称
	private String typeCode;
	
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getFieldFormat() {
		return fieldFormat;
	}
	public void setFieldFormat(String fieldFormat) {
		this.fieldFormat = fieldFormat;
	}
	public String getFieldValueName() {
		return fieldValueName;
	}
	public void setFieldValueName(String fieldValueName) {
		this.fieldValueName = fieldValueName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getIsSelect() {
		return isSelect;
	}
	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
	public String getRepeat() {
		return repeat;
	}
	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
	public String getIsOnly() {
		return isOnly;
	}
	public void setIsOnly(String isOnly) {
		this.isOnly = isOnly;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getXpath() {
		return xpath;
	}
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
	
	
}
