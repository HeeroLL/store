package com.isell.ei.logistics.youxinda.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用户发送请求的验证信息类型
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HeaderRequest")
public class HeaderRequest {
	
	/**
	 * 用户Code
	 */
	private String customerCode = "C0157";
	
	/**
	 * 用户的数据标识
	 */
	private String appToken = "0FBE07973FFA3ABE";
	
	/**
	 * 用户的密钥
	 */
	private String appKey = "0fa09cd96bd8b6a85202bbb12cf341e5";

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

}
