package com.isell.ws.youxinda.product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for inventoryRow complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="inventoryRow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="skuNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warehouseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="onwayQty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pendingQty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellableQty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unsellableQty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reservedQty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shippedQty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expireQty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inventoryRow", propOrder = { "skuNo", "warehouseCode",
		"onwayQty", "pendingQty", "sellableQty", "unsellableQty",
		"reservedQty", "shippedQty", "expireQty" })
public class InventoryRow {

	protected String skuNo;
	protected String warehouseCode;
	protected String onwayQty;
	protected String pendingQty;
	protected String sellableQty;
	protected String unsellableQty;
	@XmlElement(required = true)
	protected String reservedQty;
	protected String shippedQty;
	protected String expireQty;

	/**
	 * Gets the value of the skuNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSkuNo() {
		return skuNo;
	}

	/**
	 * Sets the value of the skuNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSkuNo(String value) {
		this.skuNo = value;
	}

	/**
	 * Gets the value of the warehouseCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWarehouseCode() {
		return warehouseCode;
	}

	/**
	 * Sets the value of the warehouseCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWarehouseCode(String value) {
		this.warehouseCode = value;
	}

	/**
	 * Gets the value of the onwayQty property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOnwayQty() {
		return onwayQty;
	}

	/**
	 * Sets the value of the onwayQty property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOnwayQty(String value) {
		this.onwayQty = value;
	}

	/**
	 * Gets the value of the pendingQty property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPendingQty() {
		return pendingQty;
	}

	/**
	 * Sets the value of the pendingQty property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPendingQty(String value) {
		this.pendingQty = value;
	}

	/**
	 * Gets the value of the sellableQty property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSellableQty() {
		return sellableQty;
	}

	/**
	 * Sets the value of the sellableQty property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSellableQty(String value) {
		this.sellableQty = value;
	}

	/**
	 * Gets the value of the unsellableQty property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUnsellableQty() {
		return unsellableQty;
	}

	/**
	 * Sets the value of the unsellableQty property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUnsellableQty(String value) {
		this.unsellableQty = value;
	}

	/**
	 * Gets the value of the reservedQty property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReservedQty() {
		return reservedQty;
	}

	/**
	 * Sets the value of the reservedQty property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReservedQty(String value) {
		this.reservedQty = value;
	}

	/**
	 * Gets the value of the shippedQty property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getShippedQty() {
		return shippedQty;
	}

	/**
	 * Sets the value of the shippedQty property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setShippedQty(String value) {
		this.shippedQty = value;
	}

	/**
	 * Gets the value of the expireQty property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getExpireQty() {
		return expireQty;
	}

	/**
	 * Sets the value of the expireQty property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setExpireQty(String value) {
		this.expireQty = value;
	}

}
