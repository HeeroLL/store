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
	 * 是否新品 0：否 1：是
	 */
	public static final String IS_NEW_0 = "0";
	
	/**
	 * 是否新品 0：否 1：是
	 */
	public static final String IS_NEW_1 = "1";
	
    /**
     * 
     */
    private String id;
    /**
     * 商品主键
     */
    private String pId;
    /**
     * 0：仓库中（未上架） 1：出售中（已上架）
     */
    private Boolean added;
    /**
     * 酷店主键
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
     * 排序号
     */
    private Integer order;
    
    /**
     * 是否新品 0：否 1：是
     */
    private String isNew;

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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}    
}