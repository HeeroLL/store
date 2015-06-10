package com.zebone.empi.check;

public enum ErrorType {

    SYSTEM("0","系统错误未知错误"),
    XML_SCHEMA("1", "xml文档结构错误"),
    VALUE_CHECK("2","值校验错误"),
    LOGIC("3","逻辑错误");

    private String errorCode;

    private String errorDesc;

    ErrorType(String _errorCode, String _errorDesc) {
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
