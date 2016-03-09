package com.isell.service.product.vo;

import java.math.BigDecimal;

/**
 * 
 * 商品规格VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 *
 */
public class CoolProductGg{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private Integer goodsId;
    /**
     * 
     */
    private String gg;
    /**
     * 
     */
    private BigDecimal jg;
    /**
     * 
     */
    private BigDecimal cxjg;
    /**
     * 
     */
    private Float stock;
    /**
     * 
     */
    private String code;
    /**
     * 
     */
    private String logo;
    /**
     * 
     */
    private BigDecimal xsjg;
    /**
     * 
     */
    private Integer sales;
    /**
     * 
     */
    private Double weight;
    /**
     * 
     */
    private String unit;
    /**
     * 
     */
    private String spec;
    /**
     * 
     */
    private BigDecimal drpPrice;

    /**
     * vip1的价格
     */
    private BigDecimal vipPriceA;
    
    /**
     * vip1的价格
     */
    private BigDecimal vipPriceB;
    /**
     * vip1的价格
     */
    private BigDecimal vipPriceC;
    /**
     * 国别代码
     */
    private String countryCode;
    
    public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public BigDecimal getVipPriceA() {
        return vipPriceA;
    }

    public void setVipPriceA(BigDecimal vipPriceA) {
        this.vipPriceA = vipPriceA;
    }

    public BigDecimal getVipPriceB() {
        return vipPriceB;
    }

    public void setVipPriceB(BigDecimal vipPriceB) {
        this.vipPriceB = vipPriceB;
    }

    public BigDecimal getVipPriceC() {
        return vipPriceC;
    }

    public void setVipPriceC(BigDecimal vipPriceC) {
        this.vipPriceC = vipPriceC;
    }

    /**
     * 
     */
    public Integer getId(){
        return this.id;
    }

    /**
     * 
     */
    public void setId(Integer id){
        this.id = id;
    }    
    /**
     * 
     */
    public Integer getGoodsId(){
        return this.goodsId;
    }

    /**
     * 
     */
    public void setGoodsId(Integer goodsId){
        this.goodsId = goodsId;
    }    
    /**
     * 
     */
    public String getGg(){
        return this.gg;
    }

    /**
     * 
     */
    public void setGg(String gg){
        this.gg = gg;
    }    
    /**
     * 
     */
    public BigDecimal getJg(){
        return this.jg;
    }

    /**
     * 
     */
    public void setJg(BigDecimal jg){
        this.jg = jg;
    }    
    /**
     * 
     */
    public BigDecimal getCxjg(){
        return this.cxjg;
    }

    /**
     * 
     */
    public void setCxjg(BigDecimal cxjg){
        this.cxjg = cxjg;
    }    
    /**
     * 
     */
    public Float getStock(){
        return this.stock;
    }

    /**
     * 
     */
    public void setStock(Float stock){
        this.stock = stock;
    }    
    /**
     * 
     */
    public String getCode(){
        return this.code;
    }

    /**
     * 
     */
    public void setCode(String code){
        this.code = code;
    }    
    /**
     * 
     */
    public String getLogo(){
        return this.logo;
    }

    /**
     * 
     */
    public void setLogo(String logo){
        this.logo = logo;
    }    
    /**
     * 
     */
    public BigDecimal getXsjg(){
        return this.xsjg;
    }

    /**
     * 
     */
    public void setXsjg(BigDecimal xsjg){
        this.xsjg = xsjg;
    }    
    /**
     * 
     */
    public Integer getSales(){
        return this.sales;
    }

    /**
     * 
     */
    public void setSales(Integer sales){
        this.sales = sales;
    }    
    /**
     * 
     */
    public Double getWeight(){
        return this.weight;
    }

    /**
     * 
     */
    public void setWeight(Double weight){
        this.weight = weight;
    }    
    /**
     * 
     */
    public String getUnit(){
        return this.unit;
    }

    /**
     * 
     */
    public void setUnit(String unit){
        this.unit = unit;
    }    
    /**
     * 
     */
    public String getSpec(){
        return this.spec;
    }

    /**
     * 
     */
    public void setSpec(String spec){
        this.spec = spec;
    }    
    /**
     * 
     */
    public BigDecimal getDrpPrice(){
        return this.drpPrice;
    }

    /**
     * 
     */
    public void setDrpPrice(BigDecimal drpPrice){
        this.drpPrice = drpPrice;
    }    
}