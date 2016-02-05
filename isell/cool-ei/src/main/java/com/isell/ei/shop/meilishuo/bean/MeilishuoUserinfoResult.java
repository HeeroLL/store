package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说获取商家个人信息返回值
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoUserinfoResult {
	
	/** 账户id */
	private long account_id;
	
	/** 店铺名称 */
	private String group_name;
	
	/** 店铺logo的url */
	private String group_header;
	
	/** 店铺邮箱 */
	private String account_email;
	
	/** 店铺所在国家 */
	private String country_code;

	/** 账户id */
	public long getAccount_id() {
		return account_id;
	}

	/** 账户id */
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}

	/** 店铺名称 */
	public String getGroup_name() {
		return group_name;
	}

	/** 店铺名称 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	/** 店铺logo的url */
	public String getGroup_header() {
		return group_header;
	}

	/** 店铺logo的url */
	public void setGroup_header(String group_header) {
		this.group_header = group_header;
	}

	/** 店铺邮箱 */
	public String getAccount_email() {
		return account_email;
	}

	/** 店铺邮箱 */
	public void setAccount_email(String account_email) {
		this.account_email = account_email;
	}

	/** 店铺所在国家 */
	public String getCountry_code() {
		return country_code;
	}

	/** 店铺所在国家 */
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	} 

}
