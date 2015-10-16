package com.isell.ps.jooge.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.exolab.castor.types.DateTime;

/**
 * 商品
 * 
 * @author wangweihua
 * @version [版本号, 2015年10月16日]
 */
public class Merch {
    /**
     * 商品唯一标识
     */
    @JsonProperty("Id")
    private String id;
    
    /**
     * 商品商家编码
     */
    @JsonProperty("Code")
    private String code;
    
    /**
     * 商品条码
     */
    @JsonProperty("BarCode")
    private String barCode;
    
    /**
     * 名称
     */
    @JsonProperty("Name")
    private String name;
    
    /**
     * 商品详情
     */
    @JsonProperty("Desc")
    private String desc;
    
    /**
     * 商品计量单位，如个、件
     */
    @JsonProperty("Unit")
    private String unit;
    
    /**
     * 单价
     */
    @JsonProperty("Price")
    private Number price;
    
    /**
     * 主图路径
     */
    @JsonProperty("PicUrl")
    private String picUrl;
    
    /**
     * 规格集合，如果没有规格则为空集合
     */
    @JsonProperty("Skus")
    private List<SKU> skus;
    
    /**
     * 商品最后修改时间
     */
    @JsonProperty("Modified")
    private DateTime modified;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public Number getPrice() {
        return price;
    }
    
    public void setPrice(Number price) {
        this.price = price;
    }
    
    public String getPicUrl() {
        return picUrl;
    }
    
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    
    public List<SKU> getSkus() {
        return skus;
    }
    
    public void setSkus(List<SKU> skus) {
        this.skus = skus;
    }
    
    public DateTime getModified() {
        return modified;
    }
    
    public void setModified(DateTime modified) {
        this.modified = modified;
    }
    
}
