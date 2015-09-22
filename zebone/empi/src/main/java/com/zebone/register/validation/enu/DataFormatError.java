package com.zebone.register.validation.enu;



/**
 * 数据格式
 * @author 陈阵 
 * @date 2013-8-7 上午9:31:33
 */
public enum DataFormatError {
	
    A("40001","字母字符错误"),
    N("40002","数字字符错误"),
    AN("40003","字母或(和)数字字符错误"),
    D8("40004","日期格式错误YYYYMMDD"),
    T6("40005","日期格式错误hhmmss"),
    DT15("40006","日期格式错误YYYYMMDDThhmmss"),
    TF("40007","T/F格式错误");
    
 
	private String errorCode;
	
	private String errorMsg;
	
	private DataFormatError(String errorCode,String errorMsg){
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
