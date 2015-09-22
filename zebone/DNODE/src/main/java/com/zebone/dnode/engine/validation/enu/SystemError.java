package com.zebone.dnode.engine.validation.enu;



/**
 * 元数据模块错误
 * @author 陈阵 
 * @date 2013-8-27 上午11:00:39
 */
public enum SystemError {
	
	PARAMETER_ERROR("70001","参数错误");
	
	private String errorCode;
	
	private String errorMsg;
	
	private SystemError(String errorCode,String errorMsg){
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
