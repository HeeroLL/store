package com.isell.service.shop.vo;
/**
 * 系统海报表vo
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-23]
 */
public class CoonBanner{
    /**
     * 主键
     */
    private String id;
    /**
     * 商品名
     */
    private String name;
    /**
     * 图片路径
     */
    private String img;
    /**
     * 商品主键
     */
    private String pId;

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
    public String getpId(){
        return this.pId;
    }

    /**
     * 
     */
    public void setpId(String pId){
        this.pId = pId;
    }    
}