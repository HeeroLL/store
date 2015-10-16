package com.isell.service.shop.vo;

import java.math.BigDecimal;

/**
 * 
 * 酷店等级表vo
 * 
 * @author wangpegn
 * @version [版本号, 2015-10-04]
 */
public class CoonShopLevel{
    /**
     * 
     */
    private String id;
    /**
     * 
     */
    private Byte level;
    /**
     * 
     */
    private Integer orderMin;
    /**
     * 
     */
    private Integer orderMax;
    /**
     * 
     */
    private BigDecimal cRate;
    /**
     * 
     */
    private Boolean freeUse;
    /**
     * 
     */
    private Byte levelName;
    /**
     * 
     */
    private BigDecimal pRate;

    /**
     * 
     */
    public String getId(){
        return this.id;
    }

    /**
     * 
     */
    public void setId(String id){
        this.id = id;
    }    
    /**
     * 
     */
    public Byte getLevel(){
        return this.level;
    }

    /**
     * 
     */
    public void setLevel(Byte level){
        this.level = level;
    }    
    /**
     * 
     */
    public Integer getOrderMin(){
        return this.orderMin;
    }

    /**
     * 
     */
    public void setOrderMin(Integer orderMin){
        this.orderMin = orderMin;
    }    
    /**
     * 
     */
    public Integer getOrderMax(){
        return this.orderMax;
    }

    /**
     * 
     */
    public void setOrderMax(Integer orderMax){
        this.orderMax = orderMax;
    }    
    /**
     * 
     */
    public BigDecimal getcRate(){
        return this.cRate;
    }

    /**
     * 
     */
    public void setcRate(BigDecimal cRate){
        this.cRate = cRate;
    }    
    /**
     * 
     */
    public Boolean getFreeUse(){
        return this.freeUse;
    }

    /**
     * 
     */
    public void setFreeUse(Boolean freeUse){
        this.freeUse = freeUse;
    }    
    /**
     * 
     */
    public Byte getLevelName(){
        return this.levelName;
    }

    /**
     * 
     */
    public void setLevelName(Byte levelName){
        this.levelName = levelName;
    }    
    /**
     * 
     */
    public BigDecimal getpRate(){
        return this.pRate;
    }

    /**
     * 
     */
    public void setpRate(BigDecimal pRate){
        this.pRate = pRate;
    }    
}