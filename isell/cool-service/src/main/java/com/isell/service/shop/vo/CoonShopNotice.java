package com.isell.service.shop.vo;

import java.util.Date;

/**
 * 
 * 酷店公告表vo
 * 
 * @author wangpegn
 * @version [版本号, 2015-10-04]
 */
public class CoonShopNotice{
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
    private String sId;
    /**
     * 
     */
    private String title;
    /**
     * 
     */
    private String content;
    /**
     * 
     */
    private Date createtime;
    
    /**
     * 酷店编码
     */
    private String shopCode;

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
    public String getTitle(){
        return this.title;
    }

    /**
     * 
     */
    public void setTitle(String title){
        this.title = title;
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
    public Date getCreatetime(){
        return this.createtime;
    }

    /**
     * 
     */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}    
}