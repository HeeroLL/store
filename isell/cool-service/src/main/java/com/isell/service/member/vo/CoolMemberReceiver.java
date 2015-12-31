package com.isell.service.member.vo;

import java.util.Date;

/**
 * 会员收货地址mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04] 
 */
public class CoolMemberReceiver{
    /**
     * 主键
     */
    private Integer id;
    /**
     * 省
     */
    private String locationP;
    /**
     * 市
     */
    private String locationC;
    /**
     * 区
     */
    private String locationA;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮编
     */
    private String zipcode;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 电话号码
     */
    private String tel;
    /**
     * 是否默认收货地址 0：否 1：是 
     */
    private Boolean def;
    /**
     * 会员主键
     */
    private Integer mId;
    /**
     * 创建时间
     */
    private Date createtime;
    
    /**
     * 身份号
     */
    private String idcard;

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
    public String getZipcode(){
        return this.zipcode;
    }

    /**
     * 
     */
    public void setZipcode(String zipcode){
        this.zipcode = zipcode;
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
     * 
     */
    public Boolean getDef(){
        return this.def;
    }

    /**
     * 
     */
    public void setDef(Boolean def){
        this.def = def;
    }    
    /**
     * 
     */
    public Integer getmId(){
        return this.mId;
    }

    /**
     * 
     */
    public void setmId(Integer mId){
        this.mId = mId;
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

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}    
}