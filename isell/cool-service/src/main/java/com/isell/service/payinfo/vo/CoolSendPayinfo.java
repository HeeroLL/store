package com.isell.service.payinfo.vo;

import java.util.Date;

/**
 * 支付单报关信息
 * 
 * @author lilin
 * @version [版本号, 2016年1月20日]
 */
public class CoolSendPayinfo{
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 支付单是否发送 0：未发送，1：已发送
     */
    private Boolean zfFlag;
    /**
     * 支付单失败原因
     */
    private String zfCause;
    /**
     * 支付报关时间
     */
    private Date zfSendtime;
    /**
     * 物流单是否发送 0：未发送，1：已发送
     */
    private Boolean wlFlag;
    /**
     * 物流单单失败原因
     */
    private String wlCause;
    /**
     * 物流单报关时间
     */
    private Date wlSendtime;

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
     * 订单编号
     */
    public String getOrderNo(){
        return this.orderNo;
    }

    /**
     * 订单编号
     */
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }    
    /**
     * 支付单是否发送 0：未发送，1：已发送
     */
    public Boolean getZfFlag(){
        return this.zfFlag;
    }

    /**
     * 支付单是否发送 0：未发送，1：已发送
     */
    public void setZfFlag(Boolean zfFlag){
        this.zfFlag = zfFlag;
    }    
    /**
     * 支付单失败原因
     */
    public String getZfCause(){
        return this.zfCause;
    }

    /**
     * 支付单失败原因
     */
    public void setZfCause(String zfCause){
        this.zfCause = zfCause;
    }    
    /**
     * 支付报关时间
     */
    public Date getZfSendtime(){
        return this.zfSendtime;
    }

    /**
     * 支付报关时间
     */
    public void setZfSendtime(Date zfSendtime){
        this.zfSendtime = zfSendtime;
    }    
    /**
     * 物流单是否发送 0：未发送，1：已发送
     */
    public Boolean getWlFlag(){
        return this.wlFlag;
    }

    /**
     * 物流单是否发送 0：未发送，1：已发送
     */
    public void setWlFlag(Boolean wlFlag){
        this.wlFlag = wlFlag;
    }    
    /**
     * 物流单单失败原因
     */
    public String getWlCause(){
        return this.wlCause;
    }

    /**
     * 物流单单失败原因
     */
    public void setWlCause(String wlCause){
        this.wlCause = wlCause;
    }    
    /**
     * 物流单报关时间
     */
    public Date getWlSendtime(){
        return this.wlSendtime;
    }

    /**
     * 物流单报关时间
     */
    public void setWlSendtime(Date wlSendtime){
        this.wlSendtime = wlSendtime;
    }    
}