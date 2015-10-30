package com.isell.service.member.vo;

import java.util.Date;

/**
 * 
 * 用户VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-14]
 *
 */
public class CoolUser{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private String username;
    /**
     * 
     */
    private String password;
    /**
     * 
     */
    private Boolean locked;
    /**
     * 用户状态 0.未审核 1.审核通过 2.审核未通过
     */
    private Integer state;
    /**
     * 用户类型 0.会员 1.供应商 2.分销商 3.云仓提供者
     */
    private Integer type;
    /**
     * 
     */
    private String petname;
    /**
     * 
     */
    private String email;
    /**
     * 
     */
    private String mobile;
    /**
     * 
     */
    private Date createtime;
    /**
     * 
     */
    private String logo;
    /**
     * 审核人
     */
    private Integer auditUserId;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 审核原因
     */
    private String auditReason;
    /**
     * 
     */
    private Date loginTime;
    /**
     * 
     */
    private Integer distributors;
    /**
     * 
     */
    private Integer o2oStoreId;
    /**
     * 
     */
    private Integer parentStoreId;

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
    public String getUsername(){
        return this.username;
    }

    /**
     * 
     */
    public void setUsername(String username){
        this.username = username;
    }    
    /**
     * 
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * 
     */
    public void setPassword(String password){
        this.password = password;
    }    
    /**
     * 
     */
    public Boolean getLocked(){
        return this.locked;
    }

    /**
     * 
     */
    public void setLocked(Boolean locked){
        this.locked = locked;
    }    
    /**
     * 用户状态 0.未审核 1.审核通过 2.审核未通过
     */
    public Integer getState(){
        return this.state;
    }

    /**
     * 用户状态 0.未审核 1.审核通过 2.审核未通过
     */
    public void setState(Integer state){
        this.state = state;
    }    
    /**
     * 用户类型 0.会员 1.供应商 2.分销商 3.云仓提供者
     */
    public Integer getType(){
        return this.type;
    }

    /**
     * 用户类型 0.会员 1.供应商 2.分销商 3.云仓提供者
     */
    public void setType(Integer type){
        this.type = type;
    }    
    /**
     * 
     */
    public String getPetname(){
        return this.petname;
    }

    /**
     * 
     */
    public void setPetname(String petname){
        this.petname = petname;
    }    
    /**
     * 
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * 
     */
    public void setEmail(String email){
        this.email = email;
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
     * 审核人
     */
    public Integer getAuditUserId(){
        return this.auditUserId;
    }

    /**
     * 审核人
     */
    public void setAuditUserId(Integer auditUserId){
        this.auditUserId = auditUserId;
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
     * 
     */
    public Date getLoginTime(){
        return this.loginTime;
    }

    /**
     * 
     */
    public void setLoginTime(Date loginTime){
        this.loginTime = loginTime;
    }    
    /**
     * 
     */
    public Integer getDistributors(){
        return this.distributors;
    }

    /**
     * 
     */
    public void setDistributors(Integer distributors){
        this.distributors = distributors;
    }    
    /**
     * 
     */
    public Integer getO2oStoreId(){
        return this.o2oStoreId;
    }

    /**
     * 
     */
    public void setO2oStoreId(Integer o2oStoreId){
        this.o2oStoreId = o2oStoreId;
    }    
    /**
     * 
     */
    public Integer getParentStoreId(){
        return this.parentStoreId;
    }

    /**
     * 
     */
    public void setParentStoreId(Integer parentStoreId){
        this.parentStoreId = parentStoreId;
    }    
}