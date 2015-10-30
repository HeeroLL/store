package com.isell.ei.logistics.youxinda.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 创建订单响应
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SOAP-ENV:Envelope")
public class CreateOrderResponse {
	
	@XmlAttribute(name = "xmlns:ns1")
	private String nsl = "http://www.example.org/ServiceForOrder/";
	
	@XmlAttribute(name = "xmlns:SOAP-ENV")
	private String evn = "http://schemas.xmlsoap.org/soap/envelope/";
	
	@XmlElement(name = "SOAP-ENV:Body")
	private Body body;	
	
	/**
	 * 1:成功  0:失败
	 */
	private int ask;
	
	/**
	 * 返回信息
	 */
	private String message;
	
	/**
	 * oms 的订单号
	 */
	private String orderCode;
	
	/**
	 * 错误明细 失败返回错误明细成功为空
	 */
	private List<ErrorType> error;

	public int getAsk() {
		return ask;
	}

	public void setAsk(int ask) {
		this.ask = ask;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public List<ErrorType> getError() {
		return error;
	}

	public void setError(List<ErrorType> error) {
		this.error = error;
	} 

}
