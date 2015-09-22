package com.zebone.log;

public enum RangeError {
	
	TRADE_NO("0001","文档头资源编码错误"),
	OPT_NO("0002","文档头操作编码错误"),
	NODE_VALUE_LENGTH("0003","值长度错误"),
	NODE_VALUE_FORMAT("0004","值格式错误"),
	VALUE_NO("0005","值域不存在");
	
	private String errorCode;

	private String errorDesc;

	RangeError(String _errorCode, String _errorDesc) {
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
