package com.isell.ws.hangzhou.bean;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 订单表体明细
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class CJkfOrderDetail {

	/** 商品序号 */
	@XmlElement
	private int goodsOrder;
	/** 物品名称 */
	@XmlElement
	private String goodsName;
	/** 商品规格、型号 */
	@XmlElement
	private String goodsModel;
	/** 行邮税号 */
	@XmlElement
	private String codeTs;
	/** 商品毛重 */
	@XmlElement
	private BigDecimal grossWeight;
	/** 申报单价 商品实际支付的金额 */
	@XmlElement
	private BigDecimal unitPrice;
	/** 申报计量单位 */
	@XmlElement
	private String goodsUnit;
	/** 申报数量 */
	@XmlElement
	private BigDecimal goodsCount;
	/** 产销国 */
	@XmlElement
	private String originCountry;
	public int getGoodsOrder() {
		return goodsOrder;
	}
	public void setGoodsOrder(int goodsOrder) {
		this.goodsOrder = goodsOrder;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsModel() {
		return goodsModel;
	}
	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}
	public String getCodeTs() {
		return codeTs;
	}
	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}
	public BigDecimal getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public BigDecimal getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(BigDecimal goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	
	
}
