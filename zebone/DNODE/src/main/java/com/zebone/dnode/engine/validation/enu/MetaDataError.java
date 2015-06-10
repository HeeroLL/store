package com.zebone.dnode.engine.validation.enu;



/**
 * 元数据模块错误
 * @author 陈阵 
 * @date 2013-8-27 上午11:00:39
 */
public enum MetaDataError {
	
	FIELD_CONF_NOT_FIND("60001","元数据不存在"),
	
	DOC_MAPPING_NOT_FIND("60002","文档数据映射不存在"),
	
	FIELD_TABLE_COLUMN_NOT_FIND("60003","元数据映射表、列不存在"),
	
	DOC_DOCUMENT_NOT_FIND("60004","文档不存在"),
	
	FIELD_CONFIG_FIELDVALUE_NOT_FIND("60005","元数据值域不存在"),
	
	DOC_DOCUMENT_XSD_NOT_FIND("60006","文档校验xsd不存在");
	
	private String errorCode;
	
	private String errorMsg;
	
	private MetaDataError(String errorCode,String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	
}
