package com.isell.service.shop.vo;

import java.util.Date;

/**
 * 酷店分享表vo
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-10]
 */
public class CoonShopShare{
    /**
     * 主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片路径
     */
    private String img;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
    /**
     * 酷店主键
     */
    private String sShop;
    /** 店铺名称 */
    private String shopName;
    /** 店铺LOGO */
    private String shopLogo;
    /** 店铺等级 */
    private String shopLevel;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 默认分享
     */
    private Boolean def;

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
    public String getImg(){
        return this.img;
    }

    /**
     * 
     */
    public void setImg(String img){
        this.img = img;
    }    
    /**
     * 
     */
    public String getSubject(){
        return this.subject;
    }

    /**
     * 
     */
    public void setSubject(String subject){
        this.subject = subject;
    }    
    /**
     * 
     */
    public String getContent(){
        return this.content;
    }

    /**
     * 
     */
    public void setContent(String content){
        this.content = content;
    }    
    /**
     * 
     */
    public String getsShop(){
        return this.sShop;
    }

    /**
     * 
     */
    public void setsShop(String sShop){
        this.sShop = sShop;
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
    public Boolean getDef(){
        return this.def;
    }

    /**
     * 
     */
    public void setDef(Boolean def){
        this.def = def;
    }

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopLevel() {
		return shopLevel;
	}

	public void setShopLevel(String shopLevel) {
		this.shopLevel = shopLevel;
	}    
}