package com.isell.service.shop.vo;

import java.util.Date;

/**
 * 体验店表vo
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-07]
 */
public class CoonShopExperience{
    /**
     * ID主键
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 体验店编码
     */
    private String code;
    /**
     * 体验店名称
     */
    private String name;
    /**
     * 体验店地址
     */
    private String address;
    /**
     * logo
     */
    private String logo;
    /**
     * 体验店公告
     */
    private String annInfo;
    /**
     * 体验店图片
     */
    private String img;
    /**
     * 
     */
    private String qrCode;
    /**
     * 体验店序号
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private Date createtime;

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
     * 用户id
     */
    public String getUserId(){
        return this.userId;
    }

    /**
     * 用户id
     */
    public void setUserId(String userId){
        this.userId = userId;
    }    
    /**
     * 体验店编码
     */
    public String getCode(){
        return this.code;
    }

    /**
     * 体验店编码
     */
    public void setCode(String code){
        this.code = code;
    }    
    /**
     * 体验店名称
     */
    public String getName(){
        return this.name;
    }

    /**
     * 体验店名称
     */
    public void setName(String name){
        this.name = name;
    }    
    /**
     * 体验店地址
     */
    public String getAddress(){
        return this.address;
    }

    /**
     * 体验店地址
     */
    public void setAddress(String address){
        this.address = address;
    }    
    /**
     * logo
     */
    public String getLogo(){
        return this.logo;
    }

    /**
     * logo
     */
    public void setLogo(String logo){
        this.logo = logo;
    }    
    /**
     * 体验店公告
     */
    public String getAnnInfo(){
        return this.annInfo;
    }

    /**
     * 体验店公告
     */
    public void setAnnInfo(String annInfo){
        this.annInfo = annInfo;
    }    
    /**
     * 体验店图片
     */
    public String getImg(){
        return this.img;
    }

    /**
     * 体验店图片
     */
    public void setImg(String img){
        this.img = img;
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
     * 体验店序号
     */
    public Integer getSort(){
        return this.sort;
    }

    /**
     * 体验店序号
     */
    public void setSort(Integer sort){
        this.sort = sort;
    }    
    /**
     * 创建时间
     */
    public Date getCreatetime(){
        return this.createtime;
    }

    /**
     * 创建时间
     */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }    
}