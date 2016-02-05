package com.isell.ei.pay.ehking.bean;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * 支付单申报信息
 * 
 * @author lilin
 * @version [版本号, 2015年11月30日]
 */
@JsonPropertyOrder(value = {"merchantId", "requestId", "serialNumber", "totalRefundCount", "totalRefundAmount",
    "orderCurrency", "orderAmount", "status", "completeDateTime", "remark", "hmac"})
public class EhkingPayNotify {
    /**
     * 商户编号
     */
    private String merchantId;
    
    /**
     * 订单号
     */
    private String requestId;
    
    /**
     * 易汇金系统交易流水号
     */
    private String serialNumber;
    
    /**
     * 该支付订单共计退款次数
     */
    private String totalRefundCount;
    
    /**
     * 该支付订单共计退款金额
     */
    private String totalRefundAmount;
    
    /**
     * 订单币种
     */
    private String orderCurrency;
    
    /**
     * 订单金额
     */
    private String orderAmount;
    
    /**
     * 状态： 初始化INIT, 取消CANCEL, 成功SUCCESS, 失败FAILED;
     */
    private String status;
    
    /**
     * 支付完成时间
     */
    private String completeDateTime;
    
    /**
     * 在下单请求中提交的备注信息，返回给商户
     */
    private String remark;
    
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
    
    public String getRequestId() {
        return requestId;
    }
    
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public String getTotalRefundCount() {
        return totalRefundCount;
    }
    
    public void setTotalRefundCount(String totalRefundCount) {
        this.totalRefundCount = totalRefundCount;
    }
    
    public String getTotalRefundAmount() {
        return totalRefundAmount;
    }
    
    public void setTotalRefundAmount(String totalRefundAmount) {
        this.totalRefundAmount = totalRefundAmount;
    }
    
    public String getOrderCurrency() {
        return orderCurrency;
    }
    
    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }
    
    public String getOrderAmount() {
        return orderAmount;
    }
    
    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCompleteDateTime() {
        return completeDateTime;
    }
    
    public void setCompleteDateTime(String completeDateTime) {
        this.completeDateTime = completeDateTime;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getHmac() {
        return hmac;
    }
    
    public void setHmac(String hmac) {
        this.hmac = hmac;
    }
}
