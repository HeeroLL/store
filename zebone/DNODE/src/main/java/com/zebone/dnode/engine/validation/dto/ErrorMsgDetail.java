package com.zebone.dnode.engine.validation.dto;


/**
 * 错误信息明细
 * @author 陈阵 
 * @date 2013-7-31 下午2:00:25
 */
public class ErrorMsgDetail {
	
	/** 文档标签xpath **/
	private String docXpath;
	
	/** 错误类型  **/
	private String errorType;
	
	/** 错误子类型 **/
	private String errorSubType;
	
	/** 错误描述 **/
	private String errorDesc;
	
	/** 错误异常 **/
	private String errorException;
    
	/** 开始时间 **/
	private String startTime;
	
	/**结束时间 **/
	private String endTime;
	
	public String getDocXpath() {
		return docXpath;
	}

	public void setDocXpath(String docXpath) {
		this.docXpath = docXpath;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

    
	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getErrorException() {
		return errorException;
	}

	public void setErrorException(String errorException) {
		this.errorException = errorException;
	}

	public String getErrorSubType() {
		return errorSubType;
	}

	public void setErrorSubType(String errorSubType) {
		this.errorSubType = errorSubType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
     
}
