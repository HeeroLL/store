package com.zebone.empi.check;

public enum ErrorInfo {

    TRADE_NO("0001","文档头资源编码错误"),
    OPT_NO("0002","文档头操作编码错误"),
    TRADENO_NODE_NOT_EXISTS("0003","文档头结点tradeNo不存在"),
    VALUE_NO("0004","值域不存在"),
    UNIQUE_NO("0005","唯一性校验错误");

    private String errorCode;

    private String errorDesc;

    ErrorInfo(String _errorCode, String _errorDesc) {
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
