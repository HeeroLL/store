package com.isell.ps.jooge.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 商品SKU,规格集合
 * 
 * @author wangweihua
 * @version [版本号, 2015年10月16日]
 */
public class SKU {
    /**
     * SKU唯一标识
     */
    @JsonProperty("SkuId")
    private String skuId;
    
    /**
     * SKU商家编码
     */
    @JsonProperty("Code")
    private String code;
    
    /**
     * 条码
     */
    @JsonProperty("BarCode")
    private String barCode;
    
    /**
     * 单价
     */
    @JsonProperty("Price")
    private Number price;
    
    /**
     * 属性集合
     */
    @JsonProperty("Props")
    private List<Prop> props;
    
    public String getSkuId() {
        return skuId;
    }
    
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getBarCode() {
        return barCode;
    }
    
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    
    public Number getPrice() {
        return price;
    }
    
    public void setPrice(Number price) {
        this.price = price;
    }
    
    public List<Prop> getProps() {
        return props;
    }
    
    public void setProps(List<Prop> props) {
        this.props = props;
    }
    
}
