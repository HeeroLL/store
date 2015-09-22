package com.zebone.empi.vo;

public class UnbindException extends RuntimeException {

    private String code;

	private static final long serialVersionUID = 1L;

	public String getCode() {
		return code;
	}

	public UnbindException(String code,Throwable paramThrowable) {
		super(paramThrowable);
		this.code = code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
