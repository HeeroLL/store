package com.isell.service.code.vo;

import java.math.BigDecimal;

/**
 *
 * @author 
 */
public class CoolConfig{
	
    /**
     * 
     */
    private Integer id;
    /**
     * 人民币对日元汇率
     */
    private BigDecimal rateJpy;
    /**
     * 人民币对韩元汇率
     */
    private BigDecimal rateKer;
    /**
     * 人民币对美元汇率
     */
    private BigDecimal rateUsd;
    /**
     * 
     */
    private BigDecimal supplierDivided;
    /**
     * 
     */
    private Boolean storeAudit;
    /**
     * 
     */
    private Boolean shopBrandAudit;

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
     * 人民币对日元汇率
     */
    public BigDecimal getRateJpy(){
        return this.rateJpy;
    }

    /**
     * 人民币对日元汇率
     */
    public void setRateJpy(BigDecimal rateJpy){
        this.rateJpy = rateJpy;
    }    
    /**
     * 人民币对韩元汇率
     */
    public BigDecimal getRateKer(){
        return this.rateKer;
    }

    /**
     * 人民币对韩元汇率
     */
    public void setRateKer(BigDecimal rateKer){
        this.rateKer = rateKer;
    }    
    /**
     * 人民币对美元汇率
     */
    public BigDecimal getRateUsd(){
        return this.rateUsd;
    }

    /**
     * 人民币对美元汇率
     */
    public void setRateUsd(BigDecimal rateUsd){
        this.rateUsd = rateUsd;
    }    
    /**
     * 
     */
    public BigDecimal getSupplierDivided(){
        return this.supplierDivided;
    }

    /**
     * 
     */
    public void setSupplierDivided(BigDecimal supplierDivided){
        this.supplierDivided = supplierDivided;
    }    
    /**
     * 
     */
    public Boolean getStoreAudit(){
        return this.storeAudit;
    }

    /**
     * 
     */
    public void setStoreAudit(Boolean storeAudit){
        this.storeAudit = storeAudit;
    }    
    /**
     * 
     */
    public Boolean getShopBrandAudit(){
        return this.shopBrandAudit;
    }

    /**
     * 
     */
    public void setShopBrandAudit(Boolean shopBrandAudit){
        this.shopBrandAudit = shopBrandAudit;
    }    
}