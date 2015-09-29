package com.isell.ei.logistics.ecm.vo;

import java.util.Date;

import com.isell.core.base.BaseInfo;

/**
 * ECM订单生产状态接口-状态参数
 * 
 * @author lilin
 * @version [版本号, 2015年7月24日]
 */
public class EcmOrderStatus extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 4481174412603651745L;
    
    /**
     * 订单生产状态代码
     */
    private Integer orderStatus;
    
    /**
     * 订单生产处理状态
     */
    private String orderComment;
    
    /**
     * 订单节点处理时间
     */
    private Date orderMakeDate;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public Date getOrderMakeDate() {
        return orderMakeDate;
    }

    public void setOrderMakeDate(Date orderMakeDate) {
        this.orderMakeDate = orderMakeDate;
    }
    
}
