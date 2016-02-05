package com.isell.service.shop.vo;

import java.util.Date;

import com.isell.core.mybatis.page.PageConfig;

/**
 * 酷店收藏表vo
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-12]
 */
public class CoonShopFav extends PageConfig{
    /**
     * 
     */
    private String id;
    /**
     * 
     */
    private String mId;
    /**
     * 酷店主键
     */
    private String sId;
    /**
     * 
     */
    private Date createtime;
    
    /**
     * 会员名称
     */
    private String name;
    
    /**
     * 会员logo
     */
    private String logo;
    
    private String shopname;
    
    private String shoplogo;
    
    
    
    public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getShoplogo() {
		return shoplogo;
	}

	public void setShoplogo(String shoplogo) {
		this.shoplogo = shoplogo;
	}

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
    public String getmId(){
        return this.mId;
    }

    /**
     * 
     */
    public void setmId(String mId){
        this.mId = mId;
    }    
    /**
     * 
     */
    public String getsId(){
        return this.sId;
    }

    /**
     * 
     */
    public void setsId(String sId){
        this.sId = sId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}    
}