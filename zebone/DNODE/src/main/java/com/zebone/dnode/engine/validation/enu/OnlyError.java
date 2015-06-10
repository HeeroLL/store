package com.zebone.dnode.engine.validation.enu;



/**
 * 唯一性错误
 * @author 陈阵 
 * @date 2013-8-7 上午9:31:33
 */
public enum OnlyError {
	
    DATA_HAS_EXIT("50001","数据已经存在");
    

    
	private String errorCode;
	
	private String errorMsg;
	
	private OnlyError(String errorCode,String errorMsg){
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
