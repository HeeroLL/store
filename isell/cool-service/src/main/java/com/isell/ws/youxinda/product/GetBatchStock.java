package com.isell.ws.youxinda.product;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="HeaderRequest" type="{http://www.example.org/ServiceForProduct/}HeaderRequest"/>
 *         &lt;element name="skuNoArr" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "headerRequest", "skuNoArr" })
@XmlRootElement(name = "getBatchStock")
public class GetBatchStock {

	@XmlElement(name = "HeaderRequest", required = true)
	protected HeaderRequest headerRequest;
	@XmlElement(required = true)
	protected List<String> skuNoArr;

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
	 * Gets the value of the skuNoArr property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the skuNoArr property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSkuNoArr().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getSkuNoArr() {
		if (skuNoArr == null) {
			skuNoArr = new ArrayList<String>();
		}
		return this.skuNoArr;
	}

}
