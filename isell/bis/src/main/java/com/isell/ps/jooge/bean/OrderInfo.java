package com.isell.ps.jooge.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 订单简要信息
 * 
 * @author wangweihua
 * @version [版本号, 2015年10月16日]
 */
public class OrderInfo {
    /**
     * 订单号，可唯一标识订单的订单号
     */
    @JsonProperty("OrderId")
    private String orderId;
    
    /**
     * 订单最后修改时间
     */
    @JsonProperty("Modified")
    private String modified;
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public String getModified() {
        return modified;
    }
    
    public void setModified(String modified) {
        this.modified = modified;
    }
    
}
