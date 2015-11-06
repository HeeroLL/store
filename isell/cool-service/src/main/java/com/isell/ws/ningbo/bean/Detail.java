package com.isell.ws.ningbo.bean;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 宁波优贝信息--订单参数--Detail订单明细信息
 * 
 * @author wangpeng
 * @version [版本号, 2015年11月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class Detail {
    /**
     * 商品编码
     */
    @XmlElement(name = "ProductCode")
    private String productCode;
    
    /**
     * 商品名称
     */
    @XmlElement(name = "ProductName")
    private String productName;
    
    /**
     * 单位
     */
    @XmlElement(name = "Unit")
    private String unit;
    
    /**
     * 数量
     */
    @XmlElement(name = "Quantity")
    private Integer quantity;
    
    /**
     * 单价
     */
    @XmlElement(name = "Price")
    private BigDecimal price;
    
    /**
     * 总额
     */
    @XmlElement(name = "TotalPrice")
    private BigDecimal totalPrice;
    
    public String getProductCode() {
        return productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
