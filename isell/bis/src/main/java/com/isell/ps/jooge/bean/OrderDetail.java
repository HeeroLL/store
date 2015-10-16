package com.isell.ps.jooge.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 订单详情
 * 
 * @author wangweihua
 * @version [版本号, 2015年10月16日]
 */
public class OrderDetail {
    /**
     * 返回编码，0表示成功，不为0则表示有错误
     */
    @JsonProperty("Code")
    private String code;
    
    /**
     * 描述，错误时为错误描述
     */
    @JsonProperty("Desc")
    private String desc;
    
    /**
     * 订单信息，详见数据结构章节中的定义
     */
    @JsonProperty("Order")
    private Order order;
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
}
