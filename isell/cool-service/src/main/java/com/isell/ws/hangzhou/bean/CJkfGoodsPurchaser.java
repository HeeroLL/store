package com.isell.ws.hangzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 购买人信息
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class CJkfGoodsPurchaser {

	/** 购买人ID */
	@XmlElement
	private String id;
	/** 姓名 */
	@XmlElement
	private String name;
	/** 注册邮箱 */
	@XmlElement
	private String email;
	/** 联系电话 */
	@XmlElement
	private String telNumber;
	/** 地址 */
	@XmlElement
	private String address;
	/** 证件类型代码 01:身份证（试点期间只能是身份证）02:护照 03:其他 */
	@XmlElement
	private String paperType;
	/** 证件号码 */
	@XmlElement
	private String paperNumber;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPaperType() {
		return paperType;
	}
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	public String getPaperNumber() {
		return paperNumber;
	}
	public void setPaperNumber(String paperNumber) {
		this.paperNumber = paperNumber;
	}
	
	
}
