package com.isell.ei.pay.ehking.bean;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * 易汇金海关报关参数
 * 
 * @author lilin
 * @version [版本号, 2015年12月10日]
 */
@JsonPropertyOrder(value = {"customsChannel", "amount", "freight", "goodsAmount", "tax", "merchantCommerceName",
    "merchantCommerceCode"})
public class EhkingCustomsInfo {
    /**
     * 报关通道
     */
    private String customsChannel = "GUANGZHOU";
    
    /**
     * 报关金额
     */
    private Long amount;
    
    /**
     * 运费，单位分
     */
    private Long freight;
    
    /**
     * 支付货款，单位分
     */
    private Long goodsAmount;
    
    /**
     * 支付税款，单位分
     */
    private Long tax = 0l;
    
    /**
     * 企业备案名称
     */
    private String merchantCommerceName = "上海琨铭文化传播有限公司";
    
    /**
     * 企业备案号
     */
    private String merchantCommerceCode = "IE150708758484";

    public String getCustomsChannel() {
        return customsChannel;
    }

    public void setCustomsChannel(String customsChannel) {
        this.customsChannel = customsChannel;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getFreight() {
        return freight;
    }

    public void setFreight(Long freight) {
        this.freight = freight;
    }

    public Long getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Long goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public String getMerchantCommerceName() {
        return merchantCommerceName;
    }

    public void setMerchantCommerceName(String merchantCommerceName) {
        this.merchantCommerceName = merchantCommerceName;
    }

    public String getMerchantCommerceCode() {
        return merchantCommerceCode;
    }

    public void setMerchantCommerceCode(String merchantCommerceCode) {
        this.merchantCommerceCode = merchantCommerceCode;
    }
    
}
