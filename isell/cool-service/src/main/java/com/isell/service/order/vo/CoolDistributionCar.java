package com.isell.service.order.vo;

import com.isell.core.mybatis.page.PageConfig;

/**
 * 一件代发进货单表VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-16]
 */
public class CoolDistributionCar extends PageConfig{
    /**
     * 
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商品id
     */
    private Integer pId;
    /**
     * 规格id
     */
    private Integer gId;
    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 商品名称
     */
    private String name;
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
     * 用户id
     */
    public Integer getUserId(){
        return this.userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Integer userId){
        this.userId = userId;
    }    
    /**
     * 商品id
     */
    public Integer getpId(){
        return this.pId;
    }

    /**
     * 商品id
     */
    public void setpId(Integer pId){
        this.pId = pId;
    }    
    /**
     * 规格id
     */
    public Integer getgId(){
        return this.gId;
    }

    /**
     * 规格id
     */
    public void setgId(Integer gId){
        this.gId = gId;
    }    
    /**
     * 数量
     */
    public Integer getQuantity(){
        return this.quantity;
    }

    /**
     * 数量
     */
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}    
}