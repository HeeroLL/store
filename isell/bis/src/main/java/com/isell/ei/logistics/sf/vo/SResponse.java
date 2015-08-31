package com.isell.ei.logistics.sf.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 响应信息报文
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response") 
@XmlType(propOrder = {}) 
public class SResponse {

	/** 服务名 */
	@XmlAttribute
	private String service;
	/**交易状态 */
	@XmlElement(name = "Head")
	private String head;
	/** 错误信息报文 */
	@XmlElement(name = "ERROR")
	private SError error;
	/** 请求数据 */
	@XmlElement(name = "Body")
	private SBody body;
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public SError getError() {
		return error;
	}
	public void setError(SError error) {
		this.error = error;
	}
	public SBody getBody() {
		return body;
	}
	public void setBody(SBody body) {
		this.body = body;
	}
}
