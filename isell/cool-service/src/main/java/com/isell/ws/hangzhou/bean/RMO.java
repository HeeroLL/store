package com.isell.ws.hangzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 数据公共对象（接收）
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "mo") 
@XmlType(propOrder = {}) 
public class RMO {

	@XmlElement(name = "head")
	private RHead head;
	@XmlElement(name = "body")
	private RBody body;
	public RHead getHead() {
		return head;
	}
	public void setHead(RHead head) {
		this.head = head;
	}
	public RBody getBody() {
		return body;
	}
	public void setBody(RBody body) {
		this.body = body;
	}
	
}
