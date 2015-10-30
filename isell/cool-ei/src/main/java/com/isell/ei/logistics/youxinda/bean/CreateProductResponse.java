package com.isell.ei.logistics.youxinda.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 创建商品响应
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SOAP-ENV:Envelope")
public class CreateProductResponse {
	
	@XmlAttribute(name = "xmlns:ns1")
	private String nsl = "http://www.example.org/EZ-WMS/";
	
	@XmlAttribute(name = "xmlns:SOAP-ENV")
	private String env = "http://schemas.xmlsoap.org/soap/envelope/";	
	
	@XmlElement(name = "SOAP-ENV:Body")
	private Body body;

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getNsl() {
		return nsl;
	}

	public void setNsl(String nsl) {
		this.nsl = nsl;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
