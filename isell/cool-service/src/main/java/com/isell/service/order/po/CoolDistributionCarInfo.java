package com.isell.service.order.po;

import java.math.BigDecimal;

import com.isell.service.order.vo.CoolDistributionCar;

public class CoolDistributionCarInfo extends CoolDistributionCar{
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 商品英文名称
	 */
	private String nameEn;
	
	/**
	 * 规格
	 */
	private String gg;
	
	/**
	 * 图片路径
	 */
	private String logo;
	
	/**
	 * 价格
	 */
	private BigDecimal jg;
	
	/**
	 * 促销价格
	 */
	private BigDecimal cxjg;
	
	/**
	 * 销售价格
	 */
	private BigDecimal xsjg;
	
	/**
	 * 库存
	 */
	private Float stock;
	
	/**
	 * 供货价
	 */
	private BigDecimal drpPrice;
	
	/**
	 * 
	 */
	private BigDecimal price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getGg() {
		return gg;
	}

	public void setGg(String gg) {
		this.gg = gg;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public BigDecimal getJg() {
		return jg;
	}

	public void setJg(BigDecimal jg) {
		this.jg = jg;
	}

	public BigDecimal getCxjg() {
		return cxjg;
	}

	public void setCxjg(BigDecimal cxjg) {
		this.cxjg = cxjg;
	}

	public BigDecimal getXsjg() {
		return xsjg;
	}

	public void setXsjg(BigDecimal xsjg) {
		this.xsjg = xsjg;
	}

	public Float getStock() {
		return stock;
	}

	public void setStock(Float stock) {
		this.stock = stock;
	}

	public BigDecimal getDrpPrice() {
		return drpPrice;
	}

	public void setDrpPrice(BigDecimal drpPrice) {
		this.drpPrice = drpPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
