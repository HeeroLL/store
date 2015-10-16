package com.isell.ps.jooge.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 商品列表
 * 
 * @author wangweihua
 * @version [版本号, 2015年10月16日]
 */
public class ProductList {
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
     * 所有页商品的记录数，需要按此计算页码
     */
    @JsonProperty("TotalResult")
    private Number totalResult;
    
    /**
     * 商品列表
     */
    @JsonProperty("Items")
    private List<Merch> items;
    
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
    
    public List<Merch> getItems() {
        return items;
    }
    
    public void setItems(List<Merch> items) {
        this.items = items;
    }
    
}
