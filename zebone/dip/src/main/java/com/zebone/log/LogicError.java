package com.zebone.log;

public enum LogicError {
	
	
	UNIQUE_NO("0001","唯一性校验错误"),
	DELETE_NO("0002","数据不存在，删除失败"),
	UPDATE_NO("0003","数据不存在，更新失败");
	
	private String errorCode;

	private String errorDesc;

	LogicError(String _errorCode, String _errorDesc) {
		this.errorCode = _errorCode;
		this.errorDesc = _errorDesc;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	
}
