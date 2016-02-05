package com.isell.ps.logistics.chongqing.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GoodsSkuInfo {
     
     @XmlElement(name="ESHOP_ENT_CODE")
     private String eshopEntCode;
     @XmlElement(name="ESHOP_ENT_NAME")
     private String eshopEntName;
     @XmlElement(name="SKU")
     private String sku;
     @XmlElement(name="GOODS_NAME")
     private String goodsName;
     @XmlElement(name="GOODS_SPEC")
     private String goodsSpec;
     @XmlElement(name="DECLARE_UNIT")
     private String declareUnit;
     @XmlElement(name="POST_TAX_NO")
     private String postTaxNo;
     @XmlElement(name="LEGAL_UNIT")
     private String legalUnit;
     @XmlElement(name="CONV_LEGAL_UNIT_NUM")
     private String convLegalUnitNum;
     @XmlElement(name="HS_CODE")
     private String hsCode;
     @XmlElement(name="IN_AREA_UNIT")
     private String inAreaUnit;
     @XmlElement(name="CONV_IN_AREA_UNIT_NUM")
     private String convInAreaUnitNum;
	public String getEshopEntCode() {
		return eshopEntCode;
	}
	public void setEshopEntCode(String eshopEntCode) {
		this.eshopEntCode = eshopEntCode;
	}
	public String getEshopEntName() {
		return eshopEntName;
	}
	public void setEshopEntName(String eshopEntName) {
		this.eshopEntName = eshopEntName;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsSpec() {
		return goodsSpec;
	}
	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}
	public String getDeclareUnit() {
		return declareUnit;
	}
	public void setDeclareUnit(String declareUnit) {
		this.declareUnit = declareUnit;
	}
	public String getPostTaxNo() {
		return postTaxNo;
	}
	public void setPostTaxNo(String postTaxNo) {
		this.postTaxNo = postTaxNo;
	}
	public String getLegalUnit() {
		return legalUnit;
	}
	public void setLegalUnit(String legalUnit) {
		this.legalUnit = legalUnit;
	}
	public String getConvLegalUnitNum() {
		return convLegalUnitNum;
	}
	public void setConvLegalUnitNum(String convLegalUnitNum) {
		this.convLegalUnitNum = convLegalUnitNum;
	}
	public String getHsCode() {
		return hsCode;
	}
	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}
	public String getInAreaUnit() {
		return inAreaUnit;
	}
	public void setInAreaUnit(String inAreaUnit) {
		this.inAreaUnit = inAreaUnit;
	}
	public String getConvInAreaUnitNum() {
		return convInAreaUnitNum;
	}
	public void setConvInAreaUnitNum(String convInAreaUnitNum) {
		this.convInAreaUnitNum = convInAreaUnitNum;
	}
     
}
