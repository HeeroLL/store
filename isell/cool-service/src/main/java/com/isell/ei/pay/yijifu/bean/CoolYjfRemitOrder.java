package com.isell.ei.pay.yijifu.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 易极付跨境汇款订单信息
 * 
 * @author lilin
 * @version [版本号, 2016年2月23日]
 */
public class CoolYjfRemitOrder {
    /**
     * id
     */
    private Integer id;
    
    /**
     * 跨境付款批次号
     */
    private String remittranceBatchno;
    
    /**
     * 订单号
     */
    private String orderNo;
    
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;
    
    /**
     * 创建时间
     */
    private Date createtime;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 申请说明信息
     */
    private String message;
    
    /**
     * id
     */
    public Integer getId() {
        return this.id;
    }
    
    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * 跨境付款批次号
     */
    public String getRemittranceBatchno() {
        return this.remittranceBatchno;
    }
    
    /**
     * 跨境付款批次号
     */
    public void setRemittranceBatchno(String remittranceBatchno) {
        this.remittranceBatchno = remittranceBatchno;
    }
    
    /**
     * 订单号
     */
    public String getOrderNo() {
        return this.orderNo;
    }
    
    /**
     * 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    /**
     * 订单金额
     */
    public BigDecimal getOrderAmount() {
        return this.orderAmount;
    }
    
    /**
     * 订单金额
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }
    
    /**
     * 创建时间
     */
    public Date getCreatetime() {
        return this.createtime;
    }
    
    /**
     * 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}