package com.zebone.dnode.engine.validation.dto;


/**
 * 值域校验
 * @author 陈阵 
 * @date 2013-7-31 下午2:14:00
 */
public class FieldTypeValidationPara {
	
	/** 要验证的值 **/
	private String fieldTypeValue;
	
	/** xpath路径 **/
	private String xpath;
	
	/** 数据字典类型主键id **/
	private String dicTypeId;
	
	/** 主数据id **/
	private String masterDataId;

	public String getFieldTypeValue() {
		return fieldTypeValue;
	}

	public void setFieldTypeValue(String fieldTypeValue) {
		this.fieldTypeValue = fieldTypeValue;
	}

	public String getDicTypeId() {
		return dicTypeId;
	}

	public void setDicTypeId(String dicTypeId) {
		this.dicTypeId = dicTypeId;
	}

	public String getMasterDataId() {
		return masterDataId;
	}

	public void setMasterDataId(String masterDataId) {
		this.masterDataId = masterDataId;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
		
}
