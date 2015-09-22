package com.zebone.dnode.engine.clean.exception;

/**
 * 错误代码定义类
 * @author cz
 *
 */
public interface ErrorUtil {
	
	public static final String DATA_CONVERT_ERROR = "DATA_CONVERT";
	public static final String DATA_CONVERT_ERROR_MESSAGE = "数据转化错误";
	
	/** 系统错误代码  **/
	public static final String SYSTEM_ERROR = "0000";
	
	/** 数据转化错误  **/
	public static final String DATA_FORMAT_ERROR = "1000";
	
	/**  业务校验错误  **/
	public static final String BUSINESS_CHECKING_ERROR = "2000";

}
