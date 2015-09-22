package com.zebone.empi.check;

public class ValidateResult {
    private boolean isSuccess;

    private String errorCode;

    private String errorMessage;

    public ValidateResult(boolean isSuccess) {
        super();
        this.isSuccess = isSuccess;
    }

    public ValidateResult(boolean isSuccess, String errorMessage) {
        super();
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
    }

    public ValidateResult(boolean isSuccess, String errorCode, String errorMessage) {
        super();
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
