package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说登录授权
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoLogin {
	
	/**
	 * appkey
	 */
	private String client_id;
	
	/**
	 * OAuth 校验码回调时会原样返回
	 */
	private String state;
	
	/**
	 * 回调地址
	 */
	private String redirect_url;

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

}
