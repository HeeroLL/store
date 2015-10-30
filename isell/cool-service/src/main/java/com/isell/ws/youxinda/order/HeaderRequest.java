package com.isell.ws.youxinda.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.isell.ws.youxinda.YxdConfig;

/**
 * <p>
 * Java class for HeaderRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="HeaderRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="appToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="appKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeaderRequest", propOrder = { "customerCode", "appToken",
		"appKey" })
public class HeaderRequest {

	@XmlElement(required = true)
	protected String customerCode = YxdConfig.YYD_CUSTOMERCODE;
	@XmlElement(required = true)
	protected String appToken = YxdConfig.YYD_APPTOKEN;
	@XmlElement(required = true)
	protected String appKey = YxdConfig.YYD_APPKEY;

	/**
	 * Gets the value of the customerCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Sets the value of the customerCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCustomerCode(String value) {
		this.customerCode = value;
	}

	/**
	 * Gets the value of the appToken property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAppToken() {
		return appToken;
	}

	/**
	 * Sets the value of the appToken property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAppToken(String value) {
		this.appToken = value;
	}

	/**
	 * Gets the value of the appKey property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAppKey() {
		return appKey;
	}

	/**
	 * Sets the value of the appKey property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAppKey(String value) {
		this.appKey = value;
	}

}
