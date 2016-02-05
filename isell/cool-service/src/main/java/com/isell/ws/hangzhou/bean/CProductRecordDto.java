package com.isell.ws.hangzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 产品备案信息对象
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class CProductRecordDto {

	/** 电商平台编号 */
	@XmlElement
	private String companyCode;
	/** 电商平台名称 */
	@XmlElement
	private String companyName;
	/** 行邮税号 */
	@XmlElement
	private String postTaxNo;
	/** 商品类别 */
	@XmlElement
	private String goodsType;
	/** 商品名称 */
	@XmlElement
	private String goodsName;
	/** 条形码 */
	@XmlElement
	private String barCode;
	/** 品牌 */
	@XmlElement
	private String brand;
	/** 规格型号 */
	@XmlElement
	private String goodsModel;
	/** 主要成份 */
	@XmlElement
	private String mainElement;
	/** 用途 */
	@XmlElement
	private String purpose;
	/** 适用标准 */
	@XmlElement
	private String standards;
	/** 生产企业 */
	@XmlElement
	private String productionEnterprise;
	/** 生产国 */
	@XmlElement
	private String productionCountry;
	/** 许可证号 */
	@XmlElement
	private String licenceKey;
	/** 类目编码 */
	@XmlElement
	private String categoryCode;
	/** 材料地址 */
	@XmlElement
	private String materialAddress;
	/** 申请时间 */
	@XmlElement
	private String declareTimeStr;
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPostTaxNo() {
		return postTaxNo;
	}
	public void setPostTaxNo(String postTaxNo) {
		this.postTaxNo = postTaxNo;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getGoodsModel() {
		return goodsModel;
	}
	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}
	public String getMainElement() {
		return mainElement;
	}
	public void setMainElement(String mainElement) {
		this.mainElement = mainElement;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getStandards() {
		return standards;
	}
	public void setStandards(String standards) {
		this.standards = standards;
	}
	public String getProductionEnterprise() {
		return productionEnterprise;
	}
	public void setProductionEnterprise(String productionEnterprise) {
		this.productionEnterprise = productionEnterprise;
	}
	public String getProductionCountry() {
		return productionCountry;
	}
	public void setProductionCountry(String productionCountry) {
		this.productionCountry = productionCountry;
	}
	public String getLicenceKey() {
		return licenceKey;
	}
	public void setLicenceKey(String licenceKey) {
		this.licenceKey = licenceKey;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getMaterialAddress() {
		return materialAddress;
	}
	public void setMaterialAddress(String materialAddress) {
		this.materialAddress = materialAddress;
	}
	public String getDeclareTimeStr() {
		return declareTimeStr;
	}
	public void setDeclareTimeStr(String declareTimeStr) {
		this.declareTimeStr = declareTimeStr;
	}
	
	
}
