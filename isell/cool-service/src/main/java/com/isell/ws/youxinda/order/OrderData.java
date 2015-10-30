package com.isell.ws.youxinda.order;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for OrderData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="OrderData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="warehouseCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toWarehouseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provinceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="referenceNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="trackingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consigneeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consigneeCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consigneePostcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consigneeAddress1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consigneeAddress2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consigneePhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consigneeEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="grossWt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shippingFeeEstimate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shipperCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shipperPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shipperAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OrderDetailType" type="{http://www.example.org/ServiceForOrder/}OrderDetailType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderData", propOrder = { "orderCode", "orderType",
		"warehouseCode", "toWarehouseCode", "smCode", "orderStatus",
		"countryName", "provinceName", "referenceNo", "trackingNumber",
		"consigneeName", "consigneeCompany", "consigneePostcode",
		"consigneeAddress1", "consigneeAddress2", "consigneePhone",
		"consigneeEmail", "grossWt", "currencyCode", "idType", "idNumber",
		"shippingFeeEstimate", "shipperCountry", "shipperPhone",
		"shipperAddress", "remark", "orderDetailType" })
public class OrderData {

	@XmlElement(required = true)
	protected String orderCode;
	@XmlElement(required = true)
	protected String orderType;
	@XmlElement(required = true)
	protected String warehouseCode;
	protected String toWarehouseCode;
	@XmlElement(required = true)
	protected String smCode;
	@XmlElement(required = true)
	protected String orderStatus;
	@XmlElement(required = true)
	protected String countryName;
	@XmlElement(required = true)
	protected String provinceName;
	@XmlElement(required = true)
	protected String referenceNo;
	@XmlElement(required = true)
	protected String trackingNumber;
	@XmlElement(required = true)
	protected String consigneeName;
	@XmlElement(required = true)
	protected String consigneeCompany;
	@XmlElement(required = true)
	protected String consigneePostcode;
	@XmlElement(required = true)
	protected String consigneeAddress1;
	@XmlElement(required = true)
	protected String consigneeAddress2;
	@XmlElement(required = true)
	protected String consigneePhone;
	@XmlElement(required = true)
	protected String consigneeEmail;
	@XmlElement(required = true)
	protected String grossWt;
	@XmlElement(required = true)
	protected String currencyCode;
	@XmlElement(required = true)
	protected String idType;
	@XmlElement(required = true)
	protected String idNumber;
	@XmlElement(required = true)
	protected String shippingFeeEstimate;
	@XmlElement(required = true)
	protected String shipperCountry;
	@XmlElement(required = true)
	protected String shipperPhone;
	@XmlElement(required = true)
	protected String shipperAddress;
	@XmlElement(required = true)
	protected String remark;
	@XmlElement(name = "OrderDetailType")
	protected List<OrderDetailType> orderDetailType;

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
	 * Gets the value of the orderType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * Sets the value of the orderType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderType(String value) {
		this.orderType = value;
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
	 * Gets the value of the toWarehouseCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getToWarehouseCode() {
		return toWarehouseCode;
	}

	/**
	 * Sets the value of the toWarehouseCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setToWarehouseCode(String value) {
		this.toWarehouseCode = value;
	}

	/**
	 * Gets the value of the smCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSmCode() {
		return smCode;
	}

	/**
	 * Sets the value of the smCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSmCode(String value) {
		this.smCode = value;
	}

	/**
	 * Gets the value of the orderStatus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * Sets the value of the orderStatus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderStatus(String value) {
		this.orderStatus = value;
	}

	/**
	 * Gets the value of the countryName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Sets the value of the countryName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCountryName(String value) {
		this.countryName = value;
	}

	/**
	 * Gets the value of the provinceName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * Sets the value of the provinceName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProvinceName(String value) {
		this.provinceName = value;
	}

	/**
	 * Gets the value of the referenceNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReferenceNo() {
		return referenceNo;
	}

	/**
	 * Sets the value of the referenceNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReferenceNo(String value) {
		this.referenceNo = value;
	}

	/**
	 * Gets the value of the trackingNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTrackingNumber() {
		return trackingNumber;
	}

	/**
	 * Sets the value of the trackingNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTrackingNumber(String value) {
		this.trackingNumber = value;
	}

	/**
	 * Gets the value of the consigneeName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeName() {
		return consigneeName;
	}

	/**
	 * Sets the value of the consigneeName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeName(String value) {
		this.consigneeName = value;
	}

	/**
	 * Gets the value of the consigneeCompany property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeCompany() {
		return consigneeCompany;
	}

	/**
	 * Sets the value of the consigneeCompany property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeCompany(String value) {
		this.consigneeCompany = value;
	}

	/**
	 * Gets the value of the consigneePostcode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneePostcode() {
		return consigneePostcode;
	}

	/**
	 * Sets the value of the consigneePostcode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneePostcode(String value) {
		this.consigneePostcode = value;
	}

	/**
	 * Gets the value of the consigneeAddress1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeAddress1() {
		return consigneeAddress1;
	}

	/**
	 * Sets the value of the consigneeAddress1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeAddress1(String value) {
		this.consigneeAddress1 = value;
	}

	/**
	 * Gets the value of the consigneeAddress2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeAddress2() {
		return consigneeAddress2;
	}

	/**
	 * Sets the value of the consigneeAddress2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeAddress2(String value) {
		this.consigneeAddress2 = value;
	}

	/**
	 * Gets the value of the consigneePhone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneePhone() {
		return consigneePhone;
	}

	/**
	 * Sets the value of the consigneePhone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneePhone(String value) {
		this.consigneePhone = value;
	}

	/**
	 * Gets the value of the consigneeEmail property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeEmail() {
		return consigneeEmail;
	}

	/**
	 * Sets the value of the consigneeEmail property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeEmail(String value) {
		this.consigneeEmail = value;
	}

	/**
	 * Gets the value of the grossWt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGrossWt() {
		return grossWt;
	}

	/**
	 * Sets the value of the grossWt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGrossWt(String value) {
		this.grossWt = value;
	}

	/**
	 * Gets the value of the currencyCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * Sets the value of the currencyCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCurrencyCode(String value) {
		this.currencyCode = value;
	}

	/**
	 * Gets the value of the idType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * Sets the value of the idType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdType(String value) {
		this.idType = value;
	}

	/**
	 * Gets the value of the idNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * Sets the value of the idNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdNumber(String value) {
		this.idNumber = value;
	}

	/**
	 * Gets the value of the shippingFeeEstimate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getShippingFeeEstimate() {
		return shippingFeeEstimate;
	}

	/**
	 * Sets the value of the shippingFeeEstimate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setShippingFeeEstimate(String value) {
		this.shippingFeeEstimate = value;
	}

	/**
	 * Gets the value of the shipperCountry property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getShipperCountry() {
		return shipperCountry;
	}

	/**
	 * Sets the value of the shipperCountry property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setShipperCountry(String value) {
		this.shipperCountry = value;
	}

	/**
	 * Gets the value of the shipperPhone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getShipperPhone() {
		return shipperPhone;
	}

	/**
	 * Sets the value of the shipperPhone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setShipperPhone(String value) {
		this.shipperPhone = value;
	}

	/**
	 * Gets the value of the shipperAddress property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getShipperAddress() {
		return shipperAddress;
	}

	/**
	 * Sets the value of the shipperAddress property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setShipperAddress(String value) {
		this.shipperAddress = value;
	}

	/**
	 * Gets the value of the remark property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets the value of the remark property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRemark(String value) {
		this.remark = value;
	}

	/**
	 * Gets the value of the orderDetailType property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the orderDetailType property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getOrderDetailType().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link OrderDetailType }
	 * 
	 * 
	 */
	public List<OrderDetailType> getOrderDetailType() {
		if (orderDetailType == null) {
			orderDetailType = new ArrayList<OrderDetailType>();
		}
		return this.orderDetailType;
	}

}
