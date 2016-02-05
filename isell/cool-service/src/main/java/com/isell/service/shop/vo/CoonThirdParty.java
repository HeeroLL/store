package com.isell.service.shop.vo;

import java.util.Date;

/**
 * 一件代发申请表vo
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-16]
 */
public class CoonThirdParty{
	
	/**
	 * 审核状态 0.未审核 1.审核中 2.通过3.未通过
	 */
	public static final Byte AUDIT_STATE_0 = 0;
	
	/**
	 * 审核状态 0.未审核 1.审核中 2.通过3.未通过
	 */
	public static final Byte AUDIT_STATE_1 = 1;
	
	/**
	 * 审核状态 0.未审核 1.审核中 2.通过3.未通过
	 */
	public static final Byte AUDIT_STATE_2 = 2;
	
	/**
	 * 审核状态 0.未审核 1.审核中 2.通过3.未通过
	 */
	public static final Byte AUDIT_STATE_3 = 3;
	
    /**
     * ID主键
     */
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 酷店ID
     */
    private String coolStoreId;
    /**
     * 状态 0.未审核 1.审核中 2.通过3.未通过
     */
    private Byte auditState;
    /**
     * 审核人
     */
    private String auditUser;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 审核原因
     */
    private String auditReason;
    /**
     * 第三方平台
     */
    private String thirdParty;
    /**
     * 店铺昵称
     */
    private String storeName;
    /**
     * 支付宝账号
     */
    private String alipayname;
    /**
     * 店铺链接
     */
    private String storeUrl;
    /**
     * 主营类目
     */
    private String mainCategory;
    /**
     * 开店时间
     */
    private Date openTime;
    /**
     * 0.正常权限 1.拥有欠费权限
     */
    private Boolean arrearsEd;

    /**
     * ID主键
     */
    public String getId(){
        return this.id;
    }

    /**
     * ID主键
     */
    public void setId(String id){
        this.id = id;
    }    
    /**
     * 用户ID
     */
    public String getUserId(){
        return this.userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(String userId){
        this.userId = userId;
    }    
    /**
     * 酷店ID
     */
    public String getCoolStoreId(){
        return this.coolStoreId;
    }

    /**
     * 酷店ID
     */
    public void setCoolStoreId(String coolStoreId){
        this.coolStoreId = coolStoreId;
    }    
    /**
     * 状态 0.未审核 1.审核中 2.通过3.未通过
     */
    public Byte getAuditState(){
        return this.auditState;
    }

    /**
     * 状态 0.未审核 1.审核中 2.通过3.未通过
     */
    public void setAuditState(Byte auditState){
        this.auditState = auditState;
    }    
    /**
     * 审核人
     */
    public String getAuditUser(){
        return this.auditUser;
    }

    /**
     * 审核人
     */
    public void setAuditUser(String auditUser){
        this.auditUser = auditUser;
    }    
    /**
     * 审核时间
     */
    public Date getAuditTime(){
        return this.auditTime;
    }

    /**
     * 审核时间
     */
    public void setAuditTime(Date auditTime){
        this.auditTime = auditTime;
    }    
    /**
     * 审核原因
     */
    public String getAuditReason(){
        return this.auditReason;
    }

    /**
     * 审核原因
     */
    public void setAuditReason(String auditReason){
        this.auditReason = auditReason;
    }    
    /**
     * 第三方平台
     */
    public String getThirdParty(){
        return this.thirdParty;
    }

    /**
     * 第三方平台
     */
    public void setThirdParty(String thirdParty){
        this.thirdParty = thirdParty;
    }    
    /**
     * 店铺昵称
     */
    public String getStoreName(){
        return this.storeName;
    }

    /**
     * 店铺昵称
     */
    public void setStoreName(String storeName){
        this.storeName = storeName;
    }    
    /**
     * 支付宝账号
     */
    public String getAlipayname(){
        return this.alipayname;
    }

    /**
     * 支付宝账号
     */
    public void setAlipayname(String alipayname){
        this.alipayname = alipayname;
    }    
    /**
     * 店铺链接
     */
    public String getStoreUrl(){
        return this.storeUrl;
    }

    /**
     * 店铺链接
     */
    public void setStoreUrl(String storeUrl){
        this.storeUrl = storeUrl;
    }    
    /**
     * 主营类目
     */
    public String getMainCategory(){
        return this.mainCategory;
    }

    /**
     * 主营类目
     */
    public void setMainCategory(String mainCategory){
        this.mainCategory = mainCategory;
    }    
    /**
     * 开店时间
     */
    public Date getOpenTime(){
        return this.openTime;
    }

    /**
     * 开店时间
     */
    public void setOpenTime(Date openTime){
        this.openTime = openTime;
    }    
    /**
     * 0.正常权限 1.拥有欠费权限
     */
    public Boolean getArrearsEd(){
        return this.arrearsEd;
    }

    /**
     * 0.正常权限 1.拥有欠费权限
     */
    public void setArrearsEd(Boolean arrearsEd){
        this.arrearsEd = arrearsEd;
    }    
}