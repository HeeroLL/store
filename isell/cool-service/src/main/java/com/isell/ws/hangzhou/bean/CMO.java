package com.isell.ws.hangzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 数据公共对象
 * @author 一代魔笛
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "mo") 
@XmlType(propOrder = {}) 
public class CMO {

	@XmlElement(name = "head")
	private CHead head;
	@XmlElement(name = "body")
	private CBody body;
	
	public CHead getHead() {
		return head;
	}
	public void setHead(CHead head) {
		this.head = head;
	}
	public CBody getBody() {
		return body;
	}
	public void setBody(CBody body) {
		this.body = body;
	}
}
