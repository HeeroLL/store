package com.isell.ei.pay.yijifu.bean;

import java.math.BigDecimal;

/**
 * 易极付订单信息同步-物流信息
 * 
 * @author lilin
 * @version [版本号, 2016年2月19日]
 */
public class YijifuGoodsClause {
    /**
     * 商品外部id
     */
    private String outId;
    
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品详情
     */
    private String memo = "";
    
    /**
     * 商品单价
     */
    private BigDecimal price;
    
    /**
     * 商品数量
     */
    private Integer quantity;
    
    /**
     * 其他费用
     */
    private Integer otherFee = 0;
    
    /**
     * 商品单元
     */
    private String unit = "";
    
    /**
     * 商品描述网址
     */
    private String detailUrl = "";
    
    /**
     * 商品来源网址
     */
    private String referUrl = "";
    
    /**
     * 商品类目
     */
    private String category = "0104";
    
    /**
     * 币种
     */
    private String currency = "CNY";
    
    public String getOutId() {
        return outId;
    }
    
    public void setOutId(String outId) {
        this.outId = outId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMemo() {
        return memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Integer getOtherFee() {
        return otherFee;
    }
    
    public void setOtherFee(Integer otherFee) {
        this.otherFee = otherFee;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public String getDetailUrl() {
        return detailUrl;
    }
    
    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
    
    public String getReferUrl() {
        return referUrl;
    }
    
    public void setReferUrl(String referUrl) {
        this.referUrl = referUrl;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
