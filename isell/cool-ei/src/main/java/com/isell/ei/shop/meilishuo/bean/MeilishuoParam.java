package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说回调参数
 * 
 * @author wangpeng
 * @version [版本号, 2016-01-06]
 */
public class MeilishuoParam {
	
	/**
	 * 原样返回的字段
	 */
	private String state;
	
	/**
	 * 登录成功时返回，用来获取token
	 */
	private String code;
	
	/**
	 * 错误信息
	 */
	private String error;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
