package com.zebone.log;

public enum SchemaError {
	
	NODE_NOT_EXISTS("cvc-complex-type.2.4.b","缺少节点"),
	NODE_VALUE_ERROE("cvc-type.3.1.3","节点不对"),
	NODE_VALUE_LENGTH("cvc-length-valid","节点长度不对"),
	NODE_VALUE_FORMAT("cvc-pattern-valid","节点格式不对"),
	NODE_VALUE_MIN_LENGTH("cvc-minLength-valid","节点最小长度不对"),
	NODE_VALUE_MAX_LENGTH("cvc-maxLength-valid","节点最大长度不对"),
	NODE_VALUE_MIN_INT("cvc-minInclusive-valid","节点最小整数"),
	NODE_VALUE_MAX_INT("cvc-maxInclusive-valid","节点最大整数");
	
	private String errorCode;

	private String errorDesc;

	SchemaError(String _errorCode, String _errorDesc) {
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
