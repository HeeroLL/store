package com.zebone.dnode.engine.analyze;

/**
 * 解析异常类
 * 
 * @author songjunjie
 * @version 2013-8-21 上午09:59:24
 */
@SuppressWarnings("serial")
public class AnalyzeException extends RuntimeException {
	/**
	 * 异常业务描述信息。
	 */
	private String errorDesc;
	
	public AnalyzeException(Exception e){
		super(e);
	}
	
	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

}
