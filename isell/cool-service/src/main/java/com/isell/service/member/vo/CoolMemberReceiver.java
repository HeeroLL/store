package com.isell.service.member.vo;

import java.util.Date;

import com.isell.core.mybatis.Mapper;

/**
 * 会员收货地址mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04] 
 */
@Mapper
public class CoolMemberReceiver{
    /**
     * 
     */
    private Integer id;
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
    private String address;
    /**
     * 
     */
    private String zipcode;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private String mobile;
    /**
     * 
     */
    private String tel;
    /**
     * 
     */
    private Boolean def;
    /**
     * 
     */
    private Integer mId;
    /**
     * 
     */
    private Date createtime;

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
}