package com.isell.service.member.vo;

import java.util.Date;

/**
 * 
 * 实名认证表VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-12]
 *
 */
public class CoolIdentityAuth{
	/**
	 * 0审核中1审核通过2审核不通过3重新认证
	 */
	public static final String IS_PASS_0 = "0";
	/**
	 * 0审核中1审核通过2审核不通过3重新认证
	 */
	public static final String IS_PASS_1 = "1";
	/**
	 * 0审核中1审核通过2审核不通过3重新认证
	 */
	public static final String IS_PASS_2 = "2";
	/**
	 * 0审核中1审核通过2审核不通过3重新认证
	 */
	public static final String IS_PASS_3 = "3";
	
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private Integer userId;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private String idcard;
    /**
     * 
     */
    private String tel;
    /**
     * 联系地址
     */
    private String thirdParty;
    /**
     * 
     */
    private String backImgUrl;
    /**
     * 
     */
    private String imgUrl;
    /**
     * 
     */
    private Date authTime;
    /**
     * 0审核中1审核通过2审核不通过3重新认证
     */
    private String ispass;
    
    /**
     * 验证码
     */
    private String sms;

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
    public String getTel(){
        return this.tel;
    }

    /**
     * 
     */
    public void setTel(String tel){
        this.tel = tel;
    }    
    /**
     * 联系地址
     */
    public String getThirdParty(){
        return this.thirdParty;
    }

    /**
     * 联系地址
     */
    public void setThirdParty(String thirdParty){
        this.thirdParty = thirdParty;
    }    
    /**
     * 
     */
    public String getBackImgUrl(){
        return this.backImgUrl;
    }

    /**
     * 
     */
    public void setBackImgUrl(String backImgUrl){
        this.backImgUrl = backImgUrl;
    }    
    /**
     * 
     */
    public String getImgUrl(){
        return this.imgUrl;
    }

    /**
     * 
     */
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }    
    /**
     * 
     */
    public Date getAuthTime(){
        return this.authTime;
    }

    /**
     * 
     */
    public void setAuthTime(Date authTime){
        this.authTime = authTime;
    }    
    /**
     * 0审核中1审核通过2审核不通过3重新认证
     */
    public String getIspass(){
        return this.ispass;
    }

    /**
     * 0审核中1审核通过2审核不通过3重新认证
     */
    public void setIspass(String ispass){
        this.ispass = ispass;
    }

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}    
}