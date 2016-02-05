package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说订单收货人信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoAddressInfo {
	
	/** 收件人 */
	private String receiver_name;
	
	/** 收件人手机号 */
	private String mobile;
	
	/** 收件人座机 */
	private String phone;
	
	/** 国家 */
	private String country;
	
	/** 省 */
	private String province;
	
	/** 市 */
	private String city;
	
	/** 区 */
	private String district;
	
	/** 街道 */
	private String street;
	
	/** 邮编 */
	private String zip_code;
	
	/** 身份证信息 */
	private MeilishuoIdentityCard identity_card;

	/** 收件人 */
	public String getReceiver_name() {
		return receiver_name;
	}

	/** 收件人 */
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	/** 收件人手机号 */
	public String getMobile() {
		return mobile;
	}

	/** 收件人手机号 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/** 收件人座机 */
	public String getPhone() {
		return phone;
	}

	/** 收件人座机 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 国家 */
	public String getCountry() {
		return country;
	}

	/** 国家 */
	public void setCountry(String country) {
		this.country = country;
	}

	/** 省 */
	public String getProvince() {
		return province;
	}

	/** 省 */
	public void setProvince(String province) {
		this.province = province;
	}

	/** 市 */
	public String getCity() {
		return city;
	}

	/** 市 */
	public void setCity(String city) {
		this.city = city;
	}

	/** 区 */
	public String getDistrict() {
		return district;
	}

	/** 区 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/** 街道 */
	public String getStreet() {
		return street;
	}
	
	/** 街道 */
	public void setStreet(String street) {
		this.street = street;
	}

	/** 邮编 */
	public String getZip_code() {
		return zip_code;
	}

	/** 邮编 */
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	/** 身份证信息 */
	public MeilishuoIdentityCard getIdentity_card() {
		return identity_card;
	}

	/** 身份证信息 */
	public void setIdentity_card(MeilishuoIdentityCard identity_card) {
		this.identity_card = identity_card;
	}

}
