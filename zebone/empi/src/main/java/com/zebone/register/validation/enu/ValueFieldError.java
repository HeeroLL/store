package com.zebone.register.validation.enu;



/**
 * 值域错误
 * @author 陈阵 
 * @date 2013-8-7 上午9:31:33
 */
public enum ValueFieldError {
	
    FIELD_NOT_FIND("20001","元数据不存在"),
    
    DICTYPE_NOT_FIND("20002","数据字典不存在"),
    
    DIC_NOT_FIND("20003","数据字典类型不存在"),
    
    MASTERDATA_NOT_FIND("20004","主数据不存在"),

    MASTERDATA_NONEXISTENCE("20005","主数据中不存在该值"),
    
    DATE_FORMAT("20006","日期格式错误"),
    
    NUBMER_FORMAT("20007","数字格式错误"),
    
    MASTER_DIC_FIELD_VALUE_NOT_FIND("20008","元数据中字典项和主数据对应的值不存在");
    
	private String errorCode;
	
	private String errorMsg;
	
	private ValueFieldError(String errorCode,String errorMsg){
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
