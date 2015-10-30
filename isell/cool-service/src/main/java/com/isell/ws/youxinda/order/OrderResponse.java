package com.isell.ws.youxinda.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for OrderResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="OrderResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ask" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderStatu" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderResponse", propOrder = { "ask", "message", "orderCode",
		"orderStatu" })
public class OrderResponse {

	@XmlElement(required = true)
	protected String ask;
	@XmlElement(required = true)
	protected String message;
	@XmlElement(required = true)
	protected String orderCode;
	protected int orderStatu;

	/**
	 * Gets the value of the ask property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAsk() {
		return ask;
	}

	/**
	 * Sets the value of the ask property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAsk(String value) {
		this.ask = value;
	}

	/**
	 * Gets the value of the message property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the value of the message property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMessage(String value) {
		this.message = value;
	}

	/**
	 * Gets the value of the orderCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * Sets the value of the orderCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderCode(String value) {
		this.orderCode = value;
	}

	/**
	 * Gets the value of the orderStatu property.
	 * 
	 */
	public int getOrderStatu() {
		return orderStatu;
	}

	/**
	 * Sets the value of the orderStatu property.
	 * 
	 */
	public void setOrderStatu(int value) {
		this.orderStatu = value;
	}

}
