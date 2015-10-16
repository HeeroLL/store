package com.isell.ps.jooge.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 商品详情
 * 
 * @author wangweihua
 * @version [版本号, 2015年10月16日]
 */
public class ProductInfo {
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
     * 商品信息
     */
    @JsonProperty("Item")
    private Merch item;
    
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
    
    public Merch getItem() {
        return item;
    }
    
    public void setItem(Merch item) {
        this.item = item;
    }
    
}
