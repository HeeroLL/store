package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说订单收货人身份证信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoIdentityCard {
	
	/** 身份证号 */
	private String identity_card_number;
	
	/** 身份证正面图地址 */
	private String identity_card_positive_url;
	
	/** 身份证反面图地址 */
	private String identity_card_negative_url;

	/** 身份证号 */
	public String getIdentity_card_number() {
		return identity_card_number;
	}

	/** 身份证号 */
	public void setIdentity_card_number(String identity_card_number) {
		this.identity_card_number = identity_card_number;
	}

	/** 身份证正面图地址 */
	public String getIdentity_card_positive_url() {
		return identity_card_positive_url;
	}

	/** 身份证正面图地址 */
	public void setIdentity_card_positive_url(String identity_card_positive_url) {
		this.identity_card_positive_url = identity_card_positive_url;
	}
	
	/** 身份证反面图地址 */
	public String getIdentity_card_negative_url() {
		return identity_card_negative_url;
	}

	/** 身份证反面图地址 */
	public void setIdentity_card_negative_url(String identity_card_negative_url) {
		this.identity_card_negative_url = identity_card_negative_url;
	}

}
