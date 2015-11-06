package com.isell.ws.youxinda.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for OrderInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="OrderInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderModel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="warehouseCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toWarehouseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oabCounty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oabStateName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oabCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oabDistrict" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="referenceNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oabName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oabCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oabPostcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oabStreetAddress1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oabStreetAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oabPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oabEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="grossWt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shippingFeeEstimate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shipperCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderProduct" type="{http://www.example.org/ServiceForOrder/}productDeatilType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderInfo", propOrder = { "orderCode", "orderModel",
		"warehouseCode", "toWarehouseCode", "oabCounty", "oabStateName",
		"oabCity", "oabDistrict", "smCode", "referenceNo", "oabName",
		"oabCompany", "oabPostcode", "oabStreetAddress1", "oabStreetAddress2",
		"oabPhone", "oabEmail", "grossWt", "currencyCode", "idType",
		"idNumber", "remark", "orderStatus", "shippingFeeEstimate",
		"shipperCountry", "shipperPhone", "shipperAddress", "orderProduct" })
public class OrderInfo {

	protected String orderCode;
	@XmlElement(required = true)
	protected String orderModel;
	@XmlElement(required = true)
	protected String warehouseCode;
	protected String toWarehouseCode;
	@XmlElement(required = true)
	protected String oabCounty;
	@XmlElement(required = true)
	protected String oabStateName;
	@XmlElement(required = true)
	protected String oabCity;
	@XmlElement(required = true)
	protected String oabDistrict;
	@XmlElement(required = true)
	protected String smCode;
	@XmlElement(required = true)
	protected String referenceNo;
	@XmlElement(required = true)
	protected String oabName;
	protected String oabCompany;
	protected String oabPostcode;
	@XmlElement(required = true)
	protected String oabStreetAddress1;
	protected String oabStreetAddress2;
	@XmlElement(required = true)
	protected String oabPhone;
	protected String oabEmail;
	protected String grossWt;
	protected String currencyCode;
	protected String idType;
	protected String idNumber;
	protected String remark;
	protected String orderStatus;
	@XmlElement(required = true)
	protected String shippingFeeEstimate;
	protected String shipperCountry;
	protected String shipperPhone;
	protected String shipperAddress;
	@XmlElement(required = true)
	//protected List<ProductDeatilType> orderProduct;
	protected ProductDeatilType[] orderProduct;

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
	 * Gets the value of the orderModel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderModel() {
		return orderModel;
	}

	/**
	 * Sets the value of the orderModel property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderModel(String value) {
		this.orderModel = value;
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
	 * Gets the value of the oabCounty property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOabCounty() {
		return oabCounty;
	}

	/**
	 * Sets the value of the oabCounty property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOabCounty(String value) {
		this.oabCounty = value;
	}

	/**
	 * Gets the value of the oabStateName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOabStateName() {
		return oabStateName;
	}

	/**
	 * Sets the value of the oabStateName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOabStateName(String value) {
		this.oabStateName = value;
	}

	/**
	 * Gets the value of the oabCity property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOabCity() {
		return oabCity;
	}

	/**
	 * Sets the value of the oabCity property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOabCity(String value) {
		this.oabCity = value;
	}

	/**
	 * Gets the value of the oabDistrict property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOabDistrict() {
		return oabDistrict;
	}

	/**
	 * Sets the value of the oabDistrict property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOabDistrict(String value) {
		this.oabDistrict = value;
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
	 * Gets the value of the oabName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOabName() {
		return oabName;
	}

	/**
	 * Sets the value of the oabName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOabName(String value) {
		this.oabName = value;
	}

	/**
	 * Gets the value of the oabCompany property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOabCompany() {
		return oabCompany;
	}

	/**
	 * Sets the value of the oabCompany property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOabCompany(String value) {
		this.oabCompany = value;
	}

	/**
	 * Gets the value of the oabPostcode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOabPostcode() {
		return oabPostcode;
	}

	/**
	 * Sets the value of the oabPostcode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOabPostcode(String value) {
		this.oabPostcode = value;
	}

	/**
	 * Gets the value of the oabStreetAddress1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOabStreetAddress1() {
		return oabStreetAddress1;
	}

	/**
	 * Sets the value of the oabStreetAddress1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOabStreetAddress1(String value) {
		this.oabStreetAddress1 = value;
	}

	/**
	 * Gets the value of the oabStreetAddress2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOabStreetAddress2() {
		return oabStreetAddress2;
	}

	/**
	 * Sets the value of the oabStreetAddress2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOabStreetAddress2(String value) {
		this.oabStreetAddress2 = value;
	}

	/**
	 * Gets the value of the oabPhone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOabPhone() {
		return oabPhone;
	}

	/**
	 * Sets the value of the oabPhone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOabPhone(String value) {
		this.oabPhone = value;
	}

	/**
	 * Gets the value of the oabEmail property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOabEmail() {
		return oabEmail;
	}

	/**
	 * Sets the value of the oabEmail property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOabEmail(String value) {
		this.oabEmail = value;
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

	public ProductDeatilType[] getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(ProductDeatilType[] orderProduct) {
		this.orderProduct = orderProduct;
	}

	/**
	 * Gets the value of the orderProduct property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the orderProduct property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getOrderProduct().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ProductDeatilType }
	 * 
	 * 
	 */
//	public List<ProductDeatilType> getOrderProduct() {
//		if (orderProduct == null) {
//			orderProduct = new ArrayList<ProductDeatilType>();
//		}
//		return this.orderProduct;
//	}
//
//	public void setOrderProduct(List<ProductDeatilType> orderProduct) {
//		this.orderProduct = orderProduct;
//	}

}
