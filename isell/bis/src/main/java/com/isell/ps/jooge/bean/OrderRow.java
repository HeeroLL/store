package com.isell.ps.jooge.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 订单行
 * 
 * @author wangweihua
 * @version [版本号, 2015年10月16日]
 */
public class OrderRow {
    /**
     * 订单行Id
     */
    @JsonProperty("OrderRowId")
    private String orderRowId;
    
    /**
     * 商品Id 或 Sku Id
     */
    @JsonProperty("MerchId")
    private String merchId;
    
    /**
     * 行描述
     */
    @JsonProperty("RowDesc")
    private String rowDesc;
    
    /**
     * 行数量
     */
    @JsonProperty("Qty")
    private Number qty;
    
    /**
     * 单价
     */
    @JsonProperty("Price")
    private Number price;
    
    /**
     * 行调整金额
     */
    @JsonProperty("AdjustFee")
    private Number adjustFee;
    
    /**
     * 行金额， 应等于Qty * Price – AdjustFee
     */
    @JsonProperty("Amount")
    private Number amount;
    
    public String getOrderRowId() {
        return orderRowId;
    }
    
    public void setOrderRowId(String orderRowId) {
        this.orderRowId = orderRowId;
    }
    
    public String getMerchId() {
        return merchId;
    }
    
    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }
    
    public String getRowDesc() {
        return rowDesc;
    }
    
    public void setRowDesc(String rowDesc) {
        this.rowDesc = rowDesc;
    }
    
    public Number getQty() {
        return qty;
    }
    
    public void setQty(Number qty) {
        this.qty = qty;
    }
    
    public Number getPrice() {
        return price;
    }
    
    public void setPrice(Number price) {
        this.price = price;
    }
    
    public Number getAdjustFee() {
        return adjustFee;
    }
    
    public void setAdjustFee(Number adjustFee) {
        this.adjustFee = adjustFee;
    }
    
    public Number getAmount() {
        return amount;
    }
    
    public void setAmount(Number amount) {
        this.amount = amount;
    }
    
}
