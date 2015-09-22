package com.zebone.btp.log.pojo;

import java.util.Date;

/**
 * 错误日志信息
 * @author 宋俊杰
 *
 */
public class ErrorLog {
	private String errorLogId;

	/**
	 * 类名
	 */
	private String className;

	/**
	 * 方法名字 
	 */
	private String methodName;

	/**
	 * 产生时间
	 */
	private Date createTime;

	/**
	 * 日志详细信息
	 */
	private String errorInfo;

	public String getErrorLogId() {
		return errorLogId;
	}

	public void setErrorLogId(String errorLogId) {
		this.errorLogId = errorLogId == null ? null : errorLogId.trim();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName == null ? null : methodName.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo == null ? null : errorInfo.trim();
	}
}