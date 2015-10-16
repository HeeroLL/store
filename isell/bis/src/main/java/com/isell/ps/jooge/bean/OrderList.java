package com.isell.ps.jooge.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 订单列表
 * 
 * @author wangweihua
 * @version [版本号, 2015年10月16日]
 */
public class OrderList {
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
     * 所有页订单的记录数，需要按此计算页码
     */
    @JsonProperty("TotalResult")
    private Number totalResult;
    
    /**
     * 订单信息列表，详见数据结构章节中的定义
     */
    @JsonProperty("Orders")
    private List<OrderInfo> orders;
    
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
    
    public Number getTotalResult() {
        return totalResult;
    }
    
    public void setTotalResult(Number totalResult) {
        this.totalResult = totalResult;
    }
    
    public List<OrderInfo> getOrders() {
        return orders;
    }
    
    public void setOrders(List<OrderInfo> orders) {
        this.orders = orders;
    }
    
}
