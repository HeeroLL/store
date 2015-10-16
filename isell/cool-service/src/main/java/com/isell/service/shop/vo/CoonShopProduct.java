package com.isell.service.shop.vo;

import java.math.BigDecimal;

/**
 * 酷店商品表vo
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 */
public class CoonShopProduct{
    /**
     * 
     */
    private String id;
    /**
     * 
     */
    private String pId;
    /**
     * 
     */
    private Boolean added;
    /**
     * 
     */
    private String sId;
    /**
     * 
     */
    private String qrCode;
    /**
     * 
     */
    private BigDecimal brokerage;

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
    public String getpId(){
        return this.pId;
    }

    /**
     * 
     */
    public void setpId(String pId){
        this.pId = pId;
    }    
    /**
     * 
     */
    public Boolean getAdded(){
        return this.added;
    }

    /**
     * 
     */
    public void setAdded(Boolean added){
        this.added = added;
    }    
    /**
     * 
     */
    public String getsId(){
        return this.sId;
    }

    /**
     * 
     */
    public void setsId(String sId){
        this.sId = sId;
    }    
    /**
     * 
     */
    public String getQrCode(){
        return this.qrCode;
    }

    /**
     * 
     */
    public void setQrCode(String qrCode){
        this.qrCode = qrCode;
    }    
    /**
     * 
     */
    public BigDecimal getBrokerage(){
        return this.brokerage;
    }

    /**
     * 
     */
    public void setBrokerage(BigDecimal brokerage){
        this.brokerage = brokerage;
    }    
}