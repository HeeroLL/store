package com.zebone.dnode.engine.validation.enu;


/**
 * xsd错误信息
 * @author 陈阵 
 * @date 2013-8-1 下午2:27:42
 */
public enum XsdError {
    DOCUMENT("10001","数据文档错误"),
	ELEMENT_REPEAT("10002","节点的重复性错误"),
	ELEMENT_SELECT("10003","节点的可选性错误");
	private String errorCode;
	
	private String errorMsg;
	
	private XsdError(String errorCode,String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	
}
