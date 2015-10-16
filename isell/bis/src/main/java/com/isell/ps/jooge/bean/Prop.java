package com.isell.ps.jooge.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 商品属性
 * 
 * @author wangweihua
 * @version [版本号, 2015年10月16日]
 */
public class Prop {
    /**
     * 属性名称，如“颜色”
     */
    @JsonProperty("Name")
    private String name;
    
    /**
     * 属性值，如“白色”
     */
    @JsonProperty("Value")
    private String value;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
}
