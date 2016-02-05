package com.isell.service.shop.vo;

import java.util.Date;

/**
 * 酷店分享（经验心得）表vo
 * 
 * @author wangpeng
 * @version [版本号, 2016-01-25]
 */
public class CoonShopShareExperience{
    /**
     * 主键id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 分享图片
     */
    private String img;
    /**
     * 分享主题
     */
    private String subject;
    /**
     * 分享内容
     */
    private String content;
    /**
     * 店铺主键
     */
    private String sShop;
    /**
     * 会员主键
     */
    private Integer mId;
    /**
     * 创建时间
     */
    private Date createtime;
    /** 店铺名称 */
    private String shopName;
    /** 店铺LOGO */
    private String shopLogo;
    /** 店铺等级 */
    private String shopLevel;

    /**
     * 主键id
     */
    public String getId(){
        return this.id;
    }

    /**
     * 主键id
     */
    public void setId(String id){
        this.id = id;
    }    
    /**
     * 名称
     */
    public String getName(){
        return this.name;
    }

    /**
     * 名称
     */
    public void setName(String name){
        this.name = name;
    }    
    /**
     * 分享图片
     */
    public String getImg(){
        return this.img;
    }

    /**
     * 分享图片
     */
    public void setImg(String img){
        this.img = img;
    }    
    /**
     * 分享主题
     */
    public String getSubject(){
        return this.subject;
    }

    /**
     * 分享主题
     */
    public void setSubject(String subject){
        this.subject = subject;
    }    
    /**
     * 分享内容
     */
    public String getContent(){
        return this.content;
    }

    /**
     * 分享内容
     */
    public void setContent(String content){
        this.content = content;
    }    
    /**
     * 店铺主键
     */
    public String getsShop(){
        return this.sShop;
    }

    /**
     * 店铺主键
     */
    public void setsShop(String sShop){
        this.sShop = sShop;
    }    
    /**
     * 会员主键
     */
    public Integer getmId(){
        return this.mId;
    }

    /**
     * 会员主键
     */
    public void setmId(Integer mId){
        this.mId = mId;
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