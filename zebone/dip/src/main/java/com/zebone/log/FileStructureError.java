package com.zebone.log;

public enum FileStructureError {
	
	
	TRADENO_NODE_NOT_EXISTS("0001","文档头结点tradeNo不存在"),
	NODE_NOT_EXISTS("0002","结点不存在");
	
	private String errorCode;

	private String errorDesc;

	FileStructureError(String _errorCode, String _errorDesc) {
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
