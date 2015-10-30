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
 *         &lt;element name="orderCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "headerRequest", "orderCode", "orderStatus" })
@XmlRootElement(name = "updateOrderStatus")
public class UpdateOrderStatus {

	@XmlElement(name = "HeaderRequest", required = true)
	protected HeaderRequest headerRequest;
	protected String orderCode;
	protected int orderStatus;

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
	 * Gets the value of the orderStatus property.
	 * 
	 */
	public int getOrderStatus() {
		return orderStatus;
	}

	/**
	 * Sets the value of the orderStatus property.
	 * 
	 */
	public void setOrderStatus(int value) {
		this.orderStatus = value;
	}

}
