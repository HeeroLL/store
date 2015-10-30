package com.isell.ei.logistics.youxinda.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 产品(Sku)信息类型
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ProductInfo")
public class ProductInfo {
	
	/**
	 * 产品Sku代码
	 */
	private String skuNo;
	
	/**
	 * 产品名称
	 */
	private String skuName;
	
	/**
	 * 产品英文名称
	 */
	private String skuEnName;
	
	/**
	 * 产品分类  1:玩具,文体用品  2:家妆用品 3:衣服,鞋子和珠宝 4:汽摩及配件 5:书籍光盘 6:电子产品 7:食物与饮料 8:家具家电 9:保健护理用品 10:其他
	 */
	private int skuCategory;
	
	/**
	 * 产品计量单位代码，比如：7（个）
	 */
	private int UOM;
	
	/**
	 * 条码类型  0:默认条码 1:自定义条码
	 */
	private int barcodeType;
	
	/**
	 * 自定义条码 barcodeType =1时必须
	 */
	private String barcodeDefine;
	
	/**
	 * 产品长度(cm)
	 */
	private Float length;
	
	/**
	 * 产品宽度(cm)
	 */
	private Float width;
	
	/**
	 * 产品高度(cm)
	 */
	private Float height;
	
	/**
	 * 产品净重(kg)
	 */
	private Float netWeight;
	
	/**
	 * 产品毛重 (kg)
	 */
	private Float weight; 
	
	/**
	 * 是否保质期(0:否,1:是)
	 */
	private int isFlash;
	
	/**
	 * 品牌
	 */
	private String brand;
	
	/**
	 * 产品链接
	 */
	private String productLink;
	
	/**
	 * 
	 */
	private String distributorCode;
	
	/**
	 * 海关品名
	 */
	private String hsGoodsName;
	
	/**
	 * 原产国 国家二字码
	 */
	private String originCountry;
	
	/**
	 * 产品外包装生产厂商
	 */
	private String manufactory;
	
	/**
	 * 申报价值
	 */
	private Float productDeclaredValue;

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getSkuEnName() {
		return skuEnName;
	}

	public void setSkuEnName(String skuEnName) {
		this.skuEnName = skuEnName;
	}

	public int getSkuCategory() {
		return skuCategory;
	}

	public void setSkuCategory(int skuCategory) {
		this.skuCategory = skuCategory;
	}

	public int getUOM() {
		return UOM;
	}

	public void setUOM(int uOM) {
		UOM = uOM;
	}

	public int getBarcodeType() {
		return barcodeType;
	}

	public void setBarcodeType(int barcodeType) {
		this.barcodeType = barcodeType;
	}

	public String getBarcodeDefine() {
		return barcodeDefine;
	}

	public void setBarcodeDefine(String barcodeDefine) {
		this.barcodeDefine = barcodeDefine;
	}

	public Float getLength() {
		return length;
	}

	public void setLength(Float length) {
		this.length = length;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Float netWeight) {
		this.netWeight = netWeight;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public int getIsFlash() {
		return isFlash;
	}

	public void setIsFlash(int isFlash) {
		this.isFlash = isFlash;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductLink() {
		return productLink;
	}

	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

	public String getDistributorCode() {
		return distributorCode;
	}

	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}

	public String getHsGoodsName() {
		return hsGoodsName;
	}

	public void setHsGoodsName(String hsGoodsName) {
		this.hsGoodsName = hsGoodsName;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getManufactory() {
		return manufactory;
	}

	public void setManufactory(String manufactory) {
		this.manufactory = manufactory;
	}

	public Float getProductDeclaredValue() {
		return productDeclaredValue;
	}

	public void setProductDeclaredValue(Float productDeclaredValue) {
		this.productDeclaredValue = productDeclaredValue;
	}
}
