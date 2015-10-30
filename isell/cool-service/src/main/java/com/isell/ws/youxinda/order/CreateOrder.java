package com.isell.ws.youxinda.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HeaderRequest" type="{http://www.example.org/ServiceForOrder/}HeaderRequest"/>
 *         &lt;element name="OrderInfo" type="{http://www.example.org/ServiceForOrder/}OrderInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "headerRequest", "orderInfo" })
@XmlRootElement(name = "createOrder")
public class CreateOrder {

	@XmlElement(name = "HeaderRequest", required = true)
	protected HeaderRequest headerRequest;
	@XmlElement(name = "OrderInfo", required = true)
	protected OrderInfo orderInfo;

	/**
	 * Gets the value of the headerRequest property.
	 * 
	 * @return possible object is {@link HeaderRequest }
	 * 
	 */
	public HeaderRequest getHeaderRequest() {
		return headerRequest;
	}

	/**
	 * Sets the value of the headerRequest property.
	 * 
	 * @param value
	 *            allowed object is {@link HeaderRequest }
	 * 
	 */
	public void setHeaderRequest(HeaderRequest value) {
		this.headerRequest = value;
	}

	/**
	 * Gets the value of the orderInfo property.
	 * 
	 * @return possible object is {@link OrderInfo }
	 * 
	 */
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	/**
	 * Sets the value of the orderInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link OrderInfo }
	 * 
	 */
	public void setOrderInfo(OrderInfo value) {
		this.orderInfo = value;
	}

}
