package com.zebone.register.validation.enu;



/**
 * 操作类型
 * @author 林彬 
 * @date 2015-6-3 上午9:31:33
 */
public enum ResultType {
	
    XML_ERROR("ErrorStructure","XML结构错误"),
    
    VALUE_ERROR("ErrorValue","XML结构不合法值错误"),
    
    SYS_ERROR("ErrorSystem","注册过程中产生错误"),
    
    SUCCESS("Success","注册成功信息");
    

    
	private String errorCode;
	
	private String errorMsg;
	
	private ResultType(String errorCode,String errorMsg){
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
