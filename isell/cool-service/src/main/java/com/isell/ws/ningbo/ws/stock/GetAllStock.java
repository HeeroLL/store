package com.isell.ws.ningbo.ws.stock;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="erpKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="erpSecret" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "erpKey", "erpSecret", "xml" })
@XmlRootElement(name = "GetAllStock")
public class GetAllStock {

	protected String erpKey;
	protected String erpSecret;
	protected String xml;

	/**
	 * Gets the value of the erpKey property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getErpKey() {
		return erpKey;
	}

	/**
	 * Sets the value of the erpKey property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setErpKey(String value) {
		this.erpKey = value;
	}

	/**
	 * Gets the value of the erpSecret property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getErpSecret() {
		return erpSecret;
	}

	/**
	 * Sets the value of the erpSecret property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setErpSecret(String value) {
		this.erpSecret = value;
	}

	/**
	 * Gets the value of the xml property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * Sets the value of the xml property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXml(String value) {
		this.xml = value;
	}

}
