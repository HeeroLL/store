package com.isell.service.order.vo;

import java.util.Date;

/**
 *
 * @author 
 */
public class CoolShopPush{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private String shopId;
    /**
     * 
     */
    private String orderNo;
    /**
     * 
     */
    private Date ceatetime;
    /**
     * 0未删除1已删除
     */
    private Boolean isDel;

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
    public String getShopId(){
        return this.shopId;
    }

    /**
     * 
     */
    public void setShopId(String shopId){
        this.shopId = shopId;
    }    
    /**
     * 
     */
    public String getOrderNo(){
        return this.orderNo;
    }

    /**
     * 
     */
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }    
    /**
     * 
     */
    public Date getCeatetime(){
        return this.ceatetime;
    }

    /**
     * 
     */
    public void setCeatetime(Date ceatetime){
        this.ceatetime = ceatetime;
    }    
    /**
     * 0未删除1已删除
     */
    public Boolean getIsDel(){
        return this.isDel;
    }

    /**
     * 0未删除1已删除
     */
    public void setIsDel(Boolean isDel){
        this.isDel = isDel;
    }    
}