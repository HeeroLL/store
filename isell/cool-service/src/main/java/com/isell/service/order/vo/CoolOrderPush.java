package com.isell.service.order.vo;

import java.util.Date;

/**
 * 订单推送表VO
 * 
 * @author lilin
 * @version [版本号, 2015年12月25日]
 */
public class CoolOrderPush{
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 错误原因
     */
    private String error;
    /**
     * 发生时间
     */
    private Date createtime;

    /**
     * 主键id
     */
    public Integer getId(){
        return this.id;
    }

    /**
     * 主键id
     */
    public void setId(Integer id){
        this.id = id;
    }    
    /**
     * 订单号
     */
    public String getOrderNo(){
        return this.orderNo;
    }

    /**
     * 订单号
     */
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }    
    /**
     * 错误原因
     */
    public String getError(){
        return this.error;
    }

    /**
     * 错误原因
     */
    public void setError(String error){
        this.error = error;
    }    
    /**
     * 发生时间
     */
    public Date getCreatetime(){
        return this.createtime;
    }

    /**
     * 发生时间
     */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }    
}