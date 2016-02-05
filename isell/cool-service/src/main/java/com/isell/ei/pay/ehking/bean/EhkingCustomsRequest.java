package com.isell.ei.pay.ehking.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.isell.ei.pay.ehking.service.EhkingService;

/**
 * 易汇金海关报关参数
 * 
 * @author lilin
 * @version [版本号, 2015年12月10日]
 */
@JsonPropertyOrder(value = {"merchantId", "paySerialNumber", "notifyUrl", "payer", "customsInfos", "hamc"})
public class EhkingCustomsRequest {
    /**
     * 商户编号
     */
    private String merchantId = EhkingService.MERCHANTID;
    
    /**
     * 支付流水号
     */
    private String paySerialNumber;
    
    /**
     * 通知地址
     */
    private String notifyUrl;
    
    /**
     * 买家信息
     */
    private EhkingCustomsPayer payer;
    
    /**
     * 报关信息
     */
    private List<EhkingCustomsInfo> customsInfos;
    
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

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public EhkingCustomsPayer getPayer() {
        return payer;
    }

    public void setPayer(EhkingCustomsPayer payer) {
        this.payer = payer;
    }

    public List<EhkingCustomsInfo> getCustomsInfos() {
        return customsInfos;
    }

    public void setCustomsInfos(List<EhkingCustomsInfo> customsInfos) {
        this.customsInfos = customsInfos;
    }

    public String getHmac() {
        return hmac;
    }

    public void setHmac(String hmac) {
        this.hmac = hmac;
    }
}
