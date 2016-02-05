package com.isell.ei.pay.ehking.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * 易汇金海关报关参数
 * 
 * @author lilin
 * @version [版本号, 2015年12月10日]
 */
@JsonPropertyOrder(value = {"customsChannel", "amount", "freight", "goodsAmount", "tax", "status"})
public class EhkingCustomsAsyncInfo {
    /**
     * 报关通道
     */
    private String customsChannel;
    
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
     * 报关状态： PROCESSING：处理中 SUCCESS:成功 FAILED:失败
     */
    private String status;
    
    /**
     * 重写toString方法
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
