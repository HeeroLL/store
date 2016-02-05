package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说token参数
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoTokenParam {
	
	/**
	 * 固定值为authorization_code
	 */
	private String grant_type;
	
	/**
	 * login 返回的 code
	 */
	private String code;

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
