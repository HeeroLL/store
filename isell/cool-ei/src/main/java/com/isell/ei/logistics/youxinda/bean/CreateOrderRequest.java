package com.isell.ei.logistics.youxinda.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 创建订单请求
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-28]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SOAP-ENV:Envelope")
public class CreateOrderRequest {
	
	@XmlAttribute(name = "xmlns:ns1")
	private String nsl = "http://www.example.org/ServiceForOrder/";
	
	@XmlAttribute(name = "xmlns:SOAP-ENV")
	private String evn = "http://schemas.xmlsoap.org/soap/envelope/";
	
	@XmlElement(name = "SOAP-ENV:Body")
	private Body body;

	public String getNsl() {
		return nsl;
	}

	public void setNsl(String nsl) {
		this.nsl = nsl;
	}

	public String getEvn() {
		return evn;
	}

	public void setEvn(String evn) {
		this.evn = evn;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
