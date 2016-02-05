package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说错误信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoErrorInfo {
	
	/**
	 * 错误信息
	 */
	private String error;
	
	/**
	 * 错误描述
	 */
	private String error_description;

	/**
	 * 错误信息
	 */
	public String getError() {
		return error;
	}

	/**
	 * 错误信息
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * 错误描述
	 */
	public String getError_description() {
		return error_description;
	}

	/**
	 * 错误描述
	 */
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

}
