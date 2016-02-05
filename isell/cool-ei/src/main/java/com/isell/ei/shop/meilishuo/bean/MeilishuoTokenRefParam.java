package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说刷新token参数
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoTokenRefParam {
	
	/**
	 * 固定值为refresh_token
	 */
	private String grant_type;
	
	/**
	 * 获取token时返回的刷新令牌 ${refresh_token}
	 */
	private String refresh_token;

	/**
	 * 固定值为refresh_token
	 */
	public String getGrant_type() {
		return grant_type;
	}

	/**
	 * 固定值为refresh_token
	 */
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	/**
	 * 获取token时返回的刷新令牌 ${refresh_token}
	 */
	public String getRefresh_token() {
		return refresh_token;
	}

	/**
	 * 获取token时返回的刷新令牌 ${refresh_token}
	 */
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

}
