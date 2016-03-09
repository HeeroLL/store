package com.isell.service.order.po;

import java.math.BigDecimal;

import com.isell.service.order.vo.CoonShopcart;

/**
 * 
 * 购物车信息类
 * 
 * @author wangpegn
 * @version [版本号, 2015-12-04]
 */
public class CoonShopCartInfo  extends CoonShopcart{
	
	/**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品英文名称
     */
    private String nameEn;
    
    /**
     * 类型
     */
    private Integer type;
    
    /**
     * 商品logo
     */
    private String logo;
    
    /**
     * 规格
     */
    private String gg;
    
    /**
     * 价格
     */
    private BigDecimal price;
    
    /**
     * 运费
     */
    private BigDecimal psPrice;
    
    /**
     * 酷店名称
     */
    private String shopName;
    
    /**
     * 库存
     */
    private Integer stock;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getGg() {
		return gg;
	}

	public void setGg(String gg) {
		this.gg = gg;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPsPrice() {
		return psPrice;
	}

	public void setPsPrice(BigDecimal psPrice) {
		this.psPrice = psPrice;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
