package com.zebone.dnode.engine.clean.exception;

public class CleanException extends Exception {

	private static final long serialVersionUID = 4074503101519003556L;
	
	/** 错误码   **/
	private String errorCode;
	
	/** 错误描述  **/
	private String errorDesc;

	public CleanException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CleanException(String errorCode,String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.errorDesc = cause.getMessage();
		// TODO Auto-generated constructor stub
	}

	public CleanException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.errorDesc = message;
		// TODO Auto-generated constructor stub
	}

	public CleanException(String errorCode,Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorDesc = cause.getMessage();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return errorCode + errorDesc;
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

   
}
