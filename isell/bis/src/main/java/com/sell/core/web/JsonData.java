package com.sell.core.web;

import java.util.ArrayList;
import java.util.List;

import com.sell.core.util.Exceptions;


/**
 * 通用的Json数据对象。
 * @author songjunjie
 * @version 2013-12-20 上午11:10:20
 */
@SuppressWarnings("rawtypes")
public class JsonData {
	private Integer total;// 一共多少条记录
	private List rows = new ArrayList();// 数据
	private boolean success = true;//操作是否成功
	private String msg = "";//提示信息
	private String errorTrace;//错误的详细信息
	private Object extData ;//扩展数据
	
	/**
	 * 数据总条数
	 * @return
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * 数据总条数
	 * @return
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * 每页显示条数
	 * @return
	 */
	public List getRows() {
		return rows;
	}

	/**
	 * 每页显示条数
	 * @return
	 */
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	/**
	 * 操作是否成功
	 * @param success
	 */
	public void setSuccess(boolean success){
		this.success = success;
	}
	
	/**
	 * 操作是否成功
	 * @param success
	 */
	public boolean getSuccess(){
		return success;
	}
	
	
	/**
	 * 提示信息
	 * @param msg
	 */
	public void setMsg(String msg){
		this.msg = msg;
	}

	/**
	 * 提示信息
	 * @param msg
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * 错误详细信息
	 * @return
	 */
	public String getErrorTrace() {
		return errorTrace;
	}

	/**
	 * 错误详细信息
	 * @return
	 */
	public void setErrorTrace(String errorTrace) {
		this.errorTrace = errorTrace;
	}

	/**
	 * 扩展信息
	 * @return
	 */
	public Object getExtData() {
		return extData;
	}

	/**
	 * 扩展信息
	 * @return
	 */
	public void setExtData(Object extData) {
		this.extData = extData;
	}
	
	/**
	 * 设置一个异常对象，将异常信息转换成字符串提供给errorTrace
	 * @param e
	 */
	public void setException(Exception e){
		this.errorTrace =   Exceptions.getStackTraceAsString(e);
	}
}
