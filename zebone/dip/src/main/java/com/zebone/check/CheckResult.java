package com.zebone.check;

public class CheckResult {
	
	private boolean isSuccess;
	
	private String errorCode;
	
	private String errorMessage;

	public CheckResult(boolean isSuccess) {
		super();
		this.isSuccess = isSuccess;
	}

	public CheckResult(boolean isSuccess, String errorMessage) {
		super();
		this.isSuccess = isSuccess;
		this.errorMessage = errorMessage;
	}

	public CheckResult(boolean isSuccess, String errorCode, String errorMessage) {
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