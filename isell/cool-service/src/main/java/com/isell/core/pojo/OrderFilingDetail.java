package com.isell.core.pojo;

import java.math.BigDecimal;

/**
 * 订单申报商品明细对象
 * @author 一代魔笛
 *
 */
public class OrderFilingDetail {

	/** 物品名称 */
	private String goodsName;
	/** 行邮税号 */
	private String codeTs;
	/** 商品规格、型号 */
	private String goodsModel;
	/** 产销国 */
	private String originCountry;
	/** 申报单价 */
	private BigDecimal unitPrice;
	/** 申报数量 */
	private BigDecimal goodsCount;
	/** 申报计量单位 */
	private String goodsUnit;
	/** 商品毛重 非必填*/
	private BigDecimal grossWeight;
	
	/** 物品名称 */
	public String getGoodsName() {
		return goodsName;
	}
	/** 物品名称 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/** 行邮税号 */
	public String getCodeTs() {
		return codeTs;
	}
	/** 行邮税号 */
	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}
	/** 商品规格、型号 */
	public String getGoodsModel() {
		return goodsModel;
	}
	/** 商品规格、型号 */
	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}
	/** 产销国 */
	public String getOriginCountry() {
		return originCountry;
	}
	/** 产销国 */
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	/** 申报单价 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	/** 申报单价 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	/** 申报数量 */
	public BigDecimal getGoodsCount() {
		return goodsCount;
	}
	/** 申报数量 */
	public void setGoodsCount(BigDecimal goodsCount) {
		this.goodsCount = goodsCount;
	}
	/** 申报计量单位 */
	public String getGoodsUnit() {
		return goodsUnit;
	}
	/** 申报计量单位 */
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	/** 商品毛重 非必填*/
	public BigDecimal getGrossWeight() {
		return grossWeight;
	}
	/** 商品毛重 非必填*/
	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}	
}
