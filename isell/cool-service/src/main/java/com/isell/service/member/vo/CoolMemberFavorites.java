package com.isell.service.member.vo;

import java.util.Date;

/**
 * 
 * 收藏商品VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 *
 */
public class CoolMemberFavorites{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private Integer mId;
    /**
     * 
     */
    private Integer pId;
    /**
     * 
     */
    private Date createtime;
    
    /**
     * 商品名称
     */
    private String pName;
    
    private String logo;
    
    /**
     * 商品价格
     */
    private String favPrice;
    /** 店铺ID */
    private String sId;
    /** 店铺名称 */
    private String sName;
    
    /**
     * 主键，以逗号分隔
     */
    private String ids;
    
    
    public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

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
    public Date getCreatetime(){
        return this.createtime;
    }

    /**
     * 
     */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getFavPrice() {
		return favPrice;
	}

	public void setFavPrice(String favPrice) {
		this.favPrice = favPrice;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}    
}