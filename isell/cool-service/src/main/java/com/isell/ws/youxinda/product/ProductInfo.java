package com.isell.ws.youxinda.product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ProductInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ProductInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="skuNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="skuName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="skuEnName " type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="skuCategory" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="UOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="barcodeType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="barcodeDefine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="length" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="width" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="height" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="netWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="productDeclaredValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="productLink" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="distributorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hsGoodsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="originCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="brand" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="manufactory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="specificationsAndModels" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isFlash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductInfo", propOrder = { "skuNo", "skuName",
		"skuEnName0020", "skuCategory", "uom", "barcodeType", "barcodeDefine",
		"length", "width", "height", "netWeight", "weight",
		"productDeclaredValue", "productLink", "hsCode", "distributorCode",
		"hsGoodsName", "originCountry", "brand", "manufactory",
		"specificationsAndModels", "isFlash" })
public class ProductInfo {

	protected String skuNo;
	protected String skuName;
	@XmlElement(name = "skuEnName ")
	protected String skuEnName0020;
	protected Integer skuCategory;
	@XmlElement(name = "UOM")
	protected String uom;
	protected Integer barcodeType;
	protected String barcodeDefine;
	protected Float length;
	protected Float width;
	protected Float height;
	protected float netWeight;
	protected float weight;
	protected float productDeclaredValue;
	@XmlElement(required = true)
	protected String productLink;
	protected String hsCode;
	protected String distributorCode;
	@XmlElement(required = true)
	protected String hsGoodsName;
	@XmlElement(required = true)
	protected String originCountry;
	@XmlElement(required = true)
	protected String brand;
	@XmlElement(required = true)
	protected String manufactory;
	protected String specificationsAndModels;
	protected String isFlash;

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
	 * Gets the value of the skuName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSkuName() {
		return skuName;
	}

	/**
	 * Sets the value of the skuName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSkuName(String value) {
		this.skuName = value;
	}

	/**
	 * Gets the value of the skuEnName0020 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSkuEnName_0020() {
		return skuEnName0020;
	}

	/**
	 * Sets the value of the skuEnName0020 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSkuEnName_0020(String value) {
		this.skuEnName0020 = value;
	}

	/**
	 * Gets the value of the skuCategory property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getSkuCategory() {
		return skuCategory;
	}

	/**
	 * Sets the value of the skuCategory property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setSkuCategory(Integer value) {
		this.skuCategory = value;
	}

	/**
	 * Gets the value of the uom property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUOM() {
		return uom;
	}

	/**
	 * Sets the value of the uom property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUOM(String value) {
		this.uom = value;
	}

	/**
	 * Gets the value of the barcodeType property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getBarcodeType() {
		return barcodeType;
	}

	/**
	 * Sets the value of the barcodeType property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setBarcodeType(Integer value) {
		this.barcodeType = value;
	}

	/**
	 * Gets the value of the barcodeDefine property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBarcodeDefine() {
		return barcodeDefine;
	}

	/**
	 * Sets the value of the barcodeDefine property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBarcodeDefine(String value) {
		this.barcodeDefine = value;
	}

	/**
	 * Gets the value of the length property.
	 * 
	 * @return possible object is {@link Float }
	 * 
	 */
	public Float getLength() {
		return length;
	}

	/**
	 * Sets the value of the length property.
	 * 
	 * @param value
	 *            allowed object is {@link Float }
	 * 
	 */
	public void setLength(Float value) {
		this.length = value;
	}

	/**
	 * Gets the value of the width property.
	 * 
	 * @return possible object is {@link Float }
	 * 
	 */
	public Float getWidth() {
		return width;
	}

	/**
	 * Sets the value of the width property.
	 * 
	 * @param value
	 *            allowed object is {@link Float }
	 * 
	 */
	public void setWidth(Float value) {
		this.width = value;
	}

	/**
	 * Gets the value of the height property.
	 * 
	 * @return possible object is {@link Float }
	 * 
	 */
	public Float getHeight() {
		return height;
	}

	/**
	 * Sets the value of the height property.
	 * 
	 * @param value
	 *            allowed object is {@link Float }
	 * 
	 */
	public void setHeight(Float value) {
		this.height = value;
	}

	/**
	 * Gets the value of the netWeight property.
	 * 
	 */
	public float getNetWeight() {
		return netWeight;
	}

	/**
	 * Sets the value of the netWeight property.
	 * 
	 */
	public void setNetWeight(float value) {
		this.netWeight = value;
	}

	/**
	 * Gets the value of the weight property.
	 * 
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * Sets the value of the weight property.
	 * 
	 */
	public void setWeight(float value) {
		this.weight = value;
	}

	/**
	 * Gets the value of the productDeclaredValue property.
	 * 
	 */
	public float getProductDeclaredValue() {
		return productDeclaredValue;
	}

	/**
	 * Sets the value of the productDeclaredValue property.
	 * 
	 */
	public void setProductDeclaredValue(float value) {
		this.productDeclaredValue = value;
	}

	/**
	 * Gets the value of the productLink property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductLink() {
		return productLink;
	}

	/**
	 * Sets the value of the productLink property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductLink(String value) {
		this.productLink = value;
	}

	/**
	 * Gets the value of the hsCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHsCode() {
		return hsCode;
	}

	/**
	 * Sets the value of the hsCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHsCode(String value) {
		this.hsCode = value;
	}

	/**
	 * Gets the value of the distributorCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDistributorCode() {
		return distributorCode;
	}

	/**
	 * Sets the value of the distributorCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDistributorCode(String value) {
		this.distributorCode = value;
	}

	/**
	 * Gets the value of the hsGoodsName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHsGoodsName() {
		return hsGoodsName;
	}

	/**
	 * Sets the value of the hsGoodsName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHsGoodsName(String value) {
		this.hsGoodsName = value;
	}

	/**
	 * Gets the value of the originCountry property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOriginCountry() {
		return originCountry;
	}

	/**
	 * Sets the value of the originCountry property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOriginCountry(String value) {
		this.originCountry = value;
	}

	/**
	 * Gets the value of the brand property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the value of the brand property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBrand(String value) {
		this.brand = value;
	}

	/**
	 * Gets the value of the manufactory property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getManufactory() {
		return manufactory;
	}

	/**
	 * Sets the value of the manufactory property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setManufactory(String value) {
		this.manufactory = value;
	}

	/**
	 * Gets the value of the specificationsAndModels property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSpecificationsAndModels() {
		return specificationsAndModels;
	}

	/**
	 * Sets the value of the specificationsAndModels property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSpecificationsAndModels(String value) {
		this.specificationsAndModels = value;
	}

	/**
	 * Gets the value of the isFlash property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsFlash() {
		return isFlash;
	}

	/**
	 * Sets the value of the isFlash property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsFlash(String value) {
		this.isFlash = value;
	}

}
