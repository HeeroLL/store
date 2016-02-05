package com.isell.service.shop.vo;

import java.util.Date;

/**
 * 酷店申请表vo
 * 
 * @author lilin
 * @version [版本号, 2015年9月25日]
 */
public class CoonShopApply{
	
	public static final String AUDIT_0 = "0";
	
	/**
	 * 审核状态  1.审核通过
	 */
	public static final Byte STATE_1 = 1;
	
    /**
     * 
     */
    private String id;
    /**
     * 
     */
    private String userId;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private String age;
    /**
     * 
     */
    private String mobile;
    /**
     * 
     */
    private String idcard;
    /**
     * 
     */
    private Byte jobType;
    /**
     * 
     */
    private String job;
    /**
     * 
     */
    private Date createtime;
    /**
     * 
     */
    private Byte state;
    /**
     * 
     */
    private String audit;
    /**
     * 
     */
    private Date auditTime;
    /**
     * 
     */
    private String auditReason;

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
    public String getUserId(){
        return this.userId;
    }

    /**
     * 
     */
    public void setUserId(String userId){
        this.userId = userId;
    }    
    /**
     * 
     */
    public String getName(){
        return this.name;
    }

    /**
     * 
     */
    public void setName(String name){
        this.name = name;
    }    
    /**
     * 
     */
    public String getAge(){
        return this.age;
    }

    /**
     * 
     */
    public void setAge(String age){
        this.age = age;
    }    
    /**
     * 
     */
    public String getMobile(){
        return this.mobile;
    }

    /**
     * 
     */
    public void setMobile(String mobile){
        this.mobile = mobile;
    }    
    /**
     * 
     */
    public String getIdcard(){
        return this.idcard;
    }

    /**
     * 
     */
    public void setIdcard(String idcard){
        this.idcard = idcard;
    }    
    /**
     * 
     */
    public Byte getJobType(){
        return this.jobType;
    }

    /**
     * 
     */
    public void setJobType(Byte jobType){
        this.jobType = jobType;
    }    
    /**
     * 
     */
    public String getJob(){
        return this.job;
    }

    /**
     * 
     */
    public void setJob(String job){
        this.job = job;
    }    
    /**
     * 
     */
    public Date getCreatetime(){
        return this.createtime;
    }

    /**
     * 
     */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }    
    /**
     * 
     */
    public Byte getState(){
        return this.state;
    }

    /**
     * 
     */
    public void setState(Byte state){
        this.state = state;
    }    
    /**
     * 
     */
    public String getAudit(){
        return this.audit;
    }

    /**
     * 
     */
    public void setAudit(String audit){
        this.audit = audit;
    }    
    /**
     * 
     */
    public Date getAuditTime(){
        return this.auditTime;
    }

    /**
     * 
     */
    public void setAuditTime(Date auditTime){
        this.auditTime = auditTime;
    }    
    /**
     * 
     */
    public String getAuditReason(){
        return this.auditReason;
    }

    /**
     * 
     */
    public void setAuditReason(String auditReason){
        this.auditReason = auditReason;
    }    
}