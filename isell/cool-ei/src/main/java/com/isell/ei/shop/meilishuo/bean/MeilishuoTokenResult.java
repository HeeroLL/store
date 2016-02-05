package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说token返回值
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoTokenResult {
	
	/**
	 * 访问令牌
	 */
	private String access_token;
	
	/**
	 * 过期时间
	 */
	private long expires_in;
	
	/**
	 * 信息类型，默认Bearer
	 */
	private String token_type;
	
	/**
	 * 
	 */
	private String scope;
	
	/**
	 * 刷新令牌
	 */
	private String refresh_token;

	/**
	 * 访问令牌
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * 访问令牌
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * 过期时间
	 */
	public long getExpires_in() {
		return expires_in;
	}

	/**
	 * 过期时间
	 */
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

	/**
	 * 信息类型，默认Bearer
	 */
	public String getToken_type() {
		return token_type;
	}

	/**
	 * 信息类型，默认Bearer
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	/**
	 * 
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * 
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * 刷新令牌
	 */
	public String getRefresh_token() {
		return refresh_token;
	}

	/**
	 * 刷新令牌
	 */
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

}
