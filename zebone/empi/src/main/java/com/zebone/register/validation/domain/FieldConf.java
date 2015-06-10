package com.zebone.register.validation.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 数据元
 * @author 陈阵 
 * @date 2013-7-31 上午10:11:48
 */
@Alias("fieldConf1")
public class FieldConf implements Serializable {

	private static final long serialVersionUID = 2900957838092092251L;
	
	/** 元数据主键   **/
	private String id;
	
	/** 数据源名称  **/
	private String fieldName;
	
	/** 值域类型（日期1，字符2，数字3，数据字典4，主数据5） **/
	private String fieldType; 
	
	/** 值域  **/
	private String fieldValue;
	
	/** 数据格式 **/
	private String fieldFormat;
	
	/** 业务格式（手机格式，邮件格式) **/
	private String fieldRuleFormat;
	
	/** 维一性校验   **/
	private String onlyCode;
	
	

	public FieldConf() {
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFieldFormat() {
		return fieldFormat;
	}

	public void setFieldFormat(String fieldFormat) {
		this.fieldFormat = fieldFormat;
	}

	public String getFieldRuleFormat() {
		return fieldRuleFormat;
	}

	public void setFieldRuleFormat(String fieldRuleFormat) {
		this.fieldRuleFormat = fieldRuleFormat;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnlyCode() {
		return onlyCode;
	}

	public void setOnlyCode(String onlyCode) {
		this.onlyCode = onlyCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FieldConf [id=");
		builder.append(id);
		builder.append(", fieldName=");
		builder.append(fieldName);
		builder.append(", fieldType=");
		builder.append(fieldType);
		builder.append(", fieldValue=");
		builder.append(fieldValue);
		builder.append(", fieldFormat=");
		builder.append(fieldFormat);
		builder.append(", fieldRuleFormat=");
		builder.append(fieldRuleFormat);
		builder.append("]");
		return builder.toString();
	}

    
}
