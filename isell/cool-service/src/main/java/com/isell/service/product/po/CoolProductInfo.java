package com.isell.service.product.po;

import java.math.BigDecimal;

/**
 * 
 * 商品信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-30]
 */
public class CoolProductInfo {
	
	/**
     * 商品id
     */
    private Integer pId;
    
    /**
     * 规格名称
     */
    private Integer gId;
    
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
     * 商品图片
     */
    private String logo;
    
    /**
     * 供货价
     */
    private BigDecimal drpPrice;
    
    private Integer stock;

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getgId() {
		return gId;
	}

	public void setgId(Integer gId) {
		this.gId = gId;
	}

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

	public BigDecimal getDrpPrice() {
		return drpPrice;
	}

	public void setDrpPrice(BigDecimal drpPrice) {
		this.drpPrice = drpPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
