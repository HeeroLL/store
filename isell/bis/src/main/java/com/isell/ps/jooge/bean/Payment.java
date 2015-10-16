package com.isell.ps.jooge.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 支付方式
 * 
 * @author wangweihua
 * @version [版本号, 2015年10月16日]
 */
public class Payment {
    
    /**
     * 支付方式编码，见数据字典
     */
    @JsonProperty("PaymentMethod")
    private String paymentMethod;
    
    /**
     * 支付金额，精确到分
     */
    @JsonProperty("Amount")
    private Number amount;
    
    /**
     * 支付流水号
     */
    @JsonProperty("PayNumber")
    private String payNumber;
    
    /**
     * 支付信息
     */
    @JsonProperty("PaymentInfo")
    private String paymentInfo;
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public Number getAmount() {
        return amount;
    }
    
    public void setAmount(Number amount) {
        this.amount = amount;
    }
    
    public String getPayNumber() {
        return payNumber;
    }
    
    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }
    
    public String getPaymentInfo() {
        return paymentInfo;
    }
    
    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
    
}
