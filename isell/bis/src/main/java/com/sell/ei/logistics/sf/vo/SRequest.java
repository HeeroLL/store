package com.sell.ei.logistics.sf.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 【顺丰接入】请求报文Request
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Request") 
@XmlType(propOrder = {}) 
public class SRequest {

	/** 服务名 */
	@XmlAttribute
	private String service;
	/** 定义响应报文的语言 */
	@XmlAttribute
	private String lang = "zh-CN";
	/**接入编码*/
	@XmlElement(name = "Head")
	private String head;
	/** 请求数据 */
	@XmlElement(name = "Body")
	private SBody body;
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public SBody getBody() {
		return body;
	}
	public void setBody(SBody body) {
		this.body = body;
	}
	
	
}
