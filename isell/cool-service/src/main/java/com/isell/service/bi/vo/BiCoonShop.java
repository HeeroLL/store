package com.isell.service.bi.vo;

import java.math.BigDecimal;

/**
 * 	酷店统计表VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-09]
 */
public class BiCoonShop{
    /**
     * 
     */
    private Integer id;
    /**
     * 酷店主键
     */
    private String sid;
    /**
     * 日期，格式：yyyy-MM-dd
     */
    private String date;
    /**
     * 酷店收入
     */
    private BigDecimal amount;
    /**
     * 订单数量
     */
    private Integer orders;
    /**
     * 酷店销售额
     */
    private BigDecimal total;
    /**
     * 酷店访客数
     */
    private Integer uv;
    /**
     * 酷店访问量
     */
    private Integer pv;

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
     * 酷店主键
     */
    public String getSid(){
        return this.sid;
    }

    /**
     * 酷店主键
     */
    public void setSid(String sid){
        this.sid = sid;
    }    
    /**
     * 日期，格式：yyyy-MM-dd
     */
    public String getDate(){
        return this.date;
    }

    /**
     * 日期，格式：yyyy-MM-dd
     */
    public void setDate(String date){
        this.date = date;
    }    
    /**
     * 酷店收入
     */
    public BigDecimal getAmount(){
        return this.amount;
    }

    /**
     * 酷店收入
     */
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }    
    /**
     * 订单数量
     */
    public Integer getOrders(){
        return this.orders;
    }

    /**
     * 订单数量
     */
    public void setOrders(Integer orders){
        this.orders = orders;
    }    
    /**
     * 酷店销售额
     */
    public BigDecimal getTotal(){
        return this.total;
    }

    /**
     * 酷店销售额
     */
    public void setTotal(BigDecimal total){
        this.total = total;
    }    
    /**
     * 酷店访客数
     */
    public Integer getUv(){
        return this.uv;
    }

    /**
     * 酷店访客数
     */
    public void setUv(Integer uv){
        this.uv = uv;
    }    
    /**
     * 酷店访问量
     */
    public Integer getPv(){
        return this.pv;
    }

    /**
     * 酷店访问量
     */
    public void setPv(Integer pv){
        this.pv = pv;
    }    
}