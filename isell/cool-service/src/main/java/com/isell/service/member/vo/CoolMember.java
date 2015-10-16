package com.isell.service.member.vo;

import java.util.Date;

/**
 * 
 * 会员VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 *
 */
public class CoolMember{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private String mobile;
    /**
     * 
     */
    private String realname;
    /**
     * 
     */
    private String sex;
    /**
     * 
     */
    private String email;
    /**
     * 
     */
    private String locationP;
    /**
     * 
     */
    private String locationC;
    /**
     * 
     */
    private String locationA;
    /**
     * 
     */
    private Date birthday;
    /**
     * 
     */
    private Boolean firstUpdate;
    /**
     * 
     */
    private String qq;
    /**
     * 会员类型 1.普通会员
     */
    private Integer level;
    /**
     * 
     */
    private Integer score;
    /**
     * 注册类型 1.网站注册 2.WAP注册
     */
    private String regState;
    /**
     * 
     */
    private String openid;
    /**
     * 
     */
    private String address;
    /**
     * 
     */
    private String idcard;
    /**
     * 
     */
    private String petname;
    /**
     * 
     */
    private Integer distributors;
    /**
     * 
     */
    private String shopname;
    /**
     * 
     */
    private String auditInfo;
    /**
     * 
     */
    private String distributorsToken;
    /**
     * 
     */
    private String logo;
    /**
     * 
     */
    private String parentId;
    /**
     * 
     */
    private Integer pId;
    /**
     * 
     */
    private Integer userId;
    /**
     * 
     */
    private String no;
    /**
     * 
     */
    private Integer skin;
    /**
     * 
     */
    private Integer hair;
    /**
     * 
     */
    private Integer costPerYear;
    /**
     * 
     */
    private String description;
    /**
     * 
     */
    private String verifyCode;
    /**
     * 
     */
    private String verifyEmail;
    /**
     * 付支账号
     */
    private String payNum;

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
    public String getRealname(){
        return this.realname;
    }

    /**
     * 
     */
    public void setRealname(String realname){
        this.realname = realname;
    }    
    /**
     * 
     */
    public String getSex(){
        return this.sex;
    }

    /**
     * 
     */
    public void setSex(String sex){
        this.sex = sex;
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
    public String getLocationP(){
        return this.locationP;
    }

    /**
     * 
     */
    public void setLocationP(String locationP){
        this.locationP = locationP;
    }    
    /**
     * 
     */
    public String getLocationC(){
        return this.locationC;
    }

    /**
     * 
     */
    public void setLocationC(String locationC){
        this.locationC = locationC;
    }    
    /**
     * 
     */
    public String getLocationA(){
        return this.locationA;
    }

    /**
     * 
     */
    public void setLocationA(String locationA){
        this.locationA = locationA;
    }    
    /**
     * 
     */
    public Date getBirthday(){
        return this.birthday;
    }

    /**
     * 
     */
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }    
    /**
     * 
     */
    public Boolean getFirstUpdate(){
        return this.firstUpdate;
    }

    /**
     * 
     */
    public void setFirstUpdate(Boolean firstUpdate){
        this.firstUpdate = firstUpdate;
    }    
    /**
     * 
     */
    public String getQq(){
        return this.qq;
    }

    /**
     * 
     */
    public void setQq(String qq){
        this.qq = qq;
    }    
    /**
     * 会员类型 1.普通会员
     */
    public Integer getLevel(){
        return this.level;
    }

    /**
     * 会员类型 1.普通会员
     */
    public void setLevel(Integer level){
        this.level = level;
    }    
    /**
     * 
     */
    public Integer getScore(){
        return this.score;
    }

    /**
     * 
     */
    public void setScore(Integer score){
        this.score = score;
    }    
    /**
     * 注册类型 1.网站注册 2.WAP注册
     */
    public String getRegState(){
        return this.regState;
    }

    /**
     * 注册类型 1.网站注册 2.WAP注册
     */
    public void setRegState(String regState){
        this.regState = regState;
    }    
    /**
     * 
     */
    public String getOpenid(){
        return this.openid;
    }

    /**
     * 
     */
    public void setOpenid(String openid){
        this.openid = openid;
    }    
    /**
     * 
     */
    public String getAddress(){
        return this.address;
    }

    /**
     * 
     */
    public void setAddress(String address){
        this.address = address;
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
    public String getShopname(){
        return this.shopname;
    }

    /**
     * 
     */
    public void setShopname(String shopname){
        this.shopname = shopname;
    }    
    /**
     * 
     */
    public String getAuditInfo(){
        return this.auditInfo;
    }

    /**
     * 
     */
    public void setAuditInfo(String auditInfo){
        this.auditInfo = auditInfo;
    }    
    /**
     * 
     */
    public String getDistributorsToken(){
        return this.distributorsToken;
    }

    /**
     * 
     */
    public void setDistributorsToken(String distributorsToken){
        this.distributorsToken = distributorsToken;
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
    public String getParentId(){
        return this.parentId;
    }

    /**
     * 
     */
    public void setParentId(String parentId){
        this.parentId = parentId;
    }    
    /**
     * 
     */
    public Integer getpId(){
        return this.pId;
    }

    /**
     * 
     */
    public void setpId(Integer pId){
        this.pId = pId;
    }    
    /**
     * 
     */
    public Integer getUserId(){
        return this.userId;
    }

    /**
     * 
     */
    public void setUserId(Integer userId){
        this.userId = userId;
    }    
    /**
     * 
     */
    public String getNo(){
        return this.no;
    }

    /**
     * 
     */
    public void setNo(String no){
        this.no = no;
    }    
    /**
     * 
     */
    public Integer getSkin(){
        return this.skin;
    }

    /**
     * 
     */
    public void setSkin(Integer skin){
        this.skin = skin;
    }    
    /**
     * 
     */
    public Integer getHair(){
        return this.hair;
    }

    /**
     * 
     */
    public void setHair(Integer hair){
        this.hair = hair;
    }    
    /**
     * 
     */
    public Integer getCostPerYear(){
        return this.costPerYear;
    }

    /**
     * 
     */
    public void setCostPerYear(Integer costPerYear){
        this.costPerYear = costPerYear;
    }    
    /**
     * 
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * 
     */
    public void setDescription(String description){
        this.description = description;
    }    
    /**
     * 
     */
    public String getVerifyCode(){
        return this.verifyCode;
    }

    /**
     * 
     */
    public void setVerifyCode(String verifyCode){
        this.verifyCode = verifyCode;
    }    
    /**
     * 
     */
    public String getVerifyEmail(){
        return this.verifyEmail;
    }

    /**
     * 
     */
    public void setVerifyEmail(String verifyEmail){
        this.verifyEmail = verifyEmail;
    }    
    /**
     * 付支账号
     */
    public String getPayNum(){
        return this.payNum;
    }

    /**
     * 付支账号
     */
    public void setPayNum(String payNum){
        this.payNum = payNum;
    }    
}