package com.isell.ei.pay.ehking.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * 易汇金海关报关异步响应参数
 * 
 * @author lilin
 * @version [版本号, 2015年12月11日]
 */
@JsonPropertyOrder(value = {"merchantId", "paySerialNumber", "customsInfos", "hamc"})
public class EhkingCustomsAsync {
    /**
     * 商户编号
     */
    private String merchantId;
    
    /**
     * 支付流水号
     */
    private String paySerialNumber;
    
    /**
     * 报关信息
     */
    private List<EhkingCustomsAsyncInfo> customsInfos;
    
    /**
     * 参数签名
     */
    private String hmac;
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getPaySerialNumber() {
        return paySerialNumber;
    }
    
    public void setPaySerialNumber(String paySerialNumber) {
        this.paySerialNumber = paySerialNumber;
    }
    
    public String getHmac() {
        return hmac;
    }
    
    public void setHmac(String hmac) {
        this.hmac = hmac;
    }

    public List<EhkingCustomsAsyncInfo> getCustomsInfos() {
        return customsInfos;
    }

    public void setCustomsInfos(List<EhkingCustomsAsyncInfo> customsInfos) {
        this.customsInfos = customsInfos;
    }
}
