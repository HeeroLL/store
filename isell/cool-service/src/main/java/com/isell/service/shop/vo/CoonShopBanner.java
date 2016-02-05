package com.isell.service.shop.vo;

import com.isell.core.mybatis.page.PageConfig;

/**
 * 酷店海报表vo
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-10]
 */
public class CoonShopBanner extends PageConfig{
    /**
     * 
     */
    private String id;
    /**
     * 
     */
    private String img;
    /**
     * 
     */
    private String pId;
    /**
     * 
     */
    private String sId;
    
    /**
     * 主键（以","分隔）
     */
    private String ids;

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
    public String getpId(){
        return this.pId;
    }

    /**
     * 
     */
    public void setpId(String pId){
        this.pId = pId;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}    
}