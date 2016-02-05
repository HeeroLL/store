package com.isell.ws.hangzhou.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for checkReceived complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="checkReceived">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xmlStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xmlType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkReceived", propOrder = { "xmlStr", "xmlType",
		"sourceType" })
public class CheckReceived {

	protected String xmlStr;
	protected String xmlType;
	protected String sourceType;

	/**
	 * Gets the value of the xmlStr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmlStr() {
		return xmlStr;
	}

	/**
	 * Sets the value of the xmlStr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmlStr(String value) {
		this.xmlStr = value;
	}

	/**
	 * Gets the value of the xmlType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmlType() {
		return xmlType;
	}

	/**
	 * Sets the value of the xmlType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmlType(String value) {
		this.xmlType = value;
	}

	/**
	 * Gets the value of the sourceType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * Sets the value of the sourceType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSourceType(String value) {
		this.sourceType = value;
	}

}
