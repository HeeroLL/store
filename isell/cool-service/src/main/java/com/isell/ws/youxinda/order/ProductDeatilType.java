package com.isell.ws.youxinda.order;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for productDeatilType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="productDeatilType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productSku" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionPrice" type="{http://www.w3.org/2001/XMLSchema}float" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dealPrice" type="{http://www.w3.org/2001/XMLSchema}float" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="opQuantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productDeatilType", propOrder = { "productSku",
		"transactionPrice", "dealPrice", "opQuantity" })
public class ProductDeatilType {

	@XmlElement(required = true)
	protected String productSku;
	@XmlElement(type = Float.class)
	protected List<Float> transactionPrice;
	@XmlElement(type = Float.class)
	protected List<Float> dealPrice;
	protected int opQuantity;

	/**
	 * Gets the value of the productSku property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductSku() {
		return productSku;
	}

	/**
	 * Sets the value of the productSku property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductSku(String value) {
		this.productSku = value;
	}

	/**
	 * Gets the value of the transactionPrice property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the transactionPrice property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getTransactionPrice().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Float }
	 * 
	 * 
	 */
	public List<Float> getTransactionPrice() {
		if (transactionPrice == null) {
			transactionPrice = new ArrayList<Float>();
		}
		return this.transactionPrice;
	}

	/**
	 * Gets the value of the dealPrice property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the dealPrice property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getDealPrice().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Float }
	 * 
	 * 
	 */
	public List<Float> getDealPrice() {
		if (dealPrice == null) {
			dealPrice = new ArrayList<Float>();
		}
		return this.dealPrice;
	}

	/**
	 * Gets the value of the opQuantity property.
	 * 
	 */
	public int getOpQuantity() {
		return opQuantity;
	}

	/**
	 * Sets the value of the opQuantity property.
	 * 
	 */
	public void setOpQuantity(int value) {
		this.opQuantity = value;
	}

}
