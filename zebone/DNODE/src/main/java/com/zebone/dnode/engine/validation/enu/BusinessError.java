package com.zebone.dnode.engine.validation.enu;



/**
 * 业务类型
 * @author 陈阵 
 * @date 2013-8-7 上午9:31:33
 */
public enum BusinessError {
	
    EMIAL("30001","邮件格式错误"),
    
    PNONE("30002","电话号码错误"),
    
    IDCARD("30003","身份证错误"),
    
    CELLPHONE("30004","手机号码错误");
    
 
	private String errorCode;
	
	private String errorMsg;
	
	private BusinessError(String errorCode,String errorMsg){
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
