package com.isell.ei.idcard.bean;

/**
 * 身份证验证返回信息
 * 
 * @author wangpeng
 * @version [版本号, 2016年3月3日]
 */
public class IdcardRetData {
	
	/**
	 * M-男，F-女，N-未知
	 */
	private String sex;
	
	/**
	 * 出生日期
	 */
	private String birthday;
	
	/**
	 * 身份证归属地 市/县
	 */
	private String address;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
