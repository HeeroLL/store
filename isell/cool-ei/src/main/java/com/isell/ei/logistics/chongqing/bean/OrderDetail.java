package com.isell.ei.logistics.chongqing.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetail {
    /* 商品货号 */
    @XmlElement(name = "SKU")
    private String sku;
    
    /* 规格型号 */
    @XmlElement(name = "GOODS_SPEC")
    private String goodsSpec;
    
    /* 币制代码 */
    @XmlElement(name = "CURRENCY_CODE")
    private String currencyCode;
    
    /* 商品单价 */
    @XmlElement(name = "PRICE")
    private Double price;
    
    /* 商品数量 */
    @XmlElement(name = "QTY")
    private Integer qty;
    
    /* 商品总价 */
    @XmlElement(name = "GOODS_FEE")
    private Double goodsFee;
    
    /* 税款金额 */
    @XmlElement(name = "TAX_FEE")
    private Integer taxFee;
    
    public String getSku() {
        return sku;
    }
    
    public void setSku(String sku) {
        this.sku = sku;
    }
    
    public String getGoodsSpec() {
        return goodsSpec;
    }
    
    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }
    
    public String getCurrencyCode() {
        return currencyCode;
    }
    
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Integer getQty() {
        return qty;
    }
    
    public void setQty(Integer qty) {
        this.qty = qty;
    }
    
    public Double getGoodsFee() {
        return goodsFee;
    }
    
    public void setGoodsFee(Double goodsFee) {
        this.goodsFee = goodsFee;
    }
    
    public Integer getTaxFee() {
        return taxFee;
    }
    
    public void setTaxFee(Integer taxFee) {
        this.taxFee = taxFee;
    }
    
}
