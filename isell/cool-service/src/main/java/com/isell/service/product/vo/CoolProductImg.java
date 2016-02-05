package com.isell.service.product.vo;
/**
 * 
 * 商品图片表VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-17]
 */
public class CoolProductImg{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private String img;
    /**
     * 
     */
    private String remark;
    /**
     * 
     */
    private Integer goodsId;
    /**
     * 
     */
    private Boolean home;

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
    public String getRemark(){
        return this.remark;
    }

    /**
     * 
     */
    public void setRemark(String remark){
        this.remark = remark;
    }    
    /**
     * 
     */
    public Integer getGoodsId(){
        return this.goodsId;
    }

    /**
     * 
     */
    public void setGoodsId(Integer goodsId){
        this.goodsId = goodsId;
    }    
    /**
     * 
     */
    public Boolean getHome(){
        return this.home;
    }

    /**
     * 
     */
    public void setHome(Boolean home){
        this.home = home;
    }    
}