package com.isell.ei.pay.ehking.bean;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.isell.ei.pay.ehking.service.EhkingService;

/**
 * 易汇金支付请求对象
 * 
 * @author lilin
 * @version [版本号, 2015年11月30日]
 */
@JsonPropertyOrder(value = {"merchantId", "orderAmount", "orderCurrency", "requestId", "notifyUrl", "callbackUrl",
    "remark", "paymentModeCode", "productDetails", "payer", "bankCard", "cashierVersion", "forUse", "merchantUserId",
    "bindCardId", "clientIp", "timeout", "hmac"})
public class EhkingPayInfo {
    /**
     * 商户编号
     */
    private String merchantId = EhkingService.MERCHANTID;
    
    /**
     * 订单金额
     */
    private String orderAmount = "";
    
    /**
     * 订单币种
     */
    private String orderCurrency = "CNY";
    
    /**
     * 提交的订单号必须在自身平台交易中唯一。易汇金支付系统已付或撤销的订单，商户平台不能以相同的订单号再次提交。
     */
    private String requestId = "";
    
    /**
     * 通知地址
     */
    private String notifyUrl = "";
    
    /**
     * 回调地址
     */
    private String callbackUrl = "";
    
    /**
     * 备注
     */
    private String remark = "";
    
    /**
     * 支付方式编码
     */
    private String paymentModeCode = "";
    
    /**
     * 商品信息
     */
    private List<EhkingProductDetail> productDetails = new ArrayList<EhkingProductDetail>();
    
    /**
     * 申报信息
     */
    private EhkingPayer payer = new EhkingPayer();
    
    /**
     * 快捷支付信息
     */
    private EhkingBankCard bankCard = new EhkingBankCard();
    
    /**
     * <pre>
     * STANDARD标准版DECLARE申报版
     * CUSTOMS海关版
     * 按照收银台类型值判断
     * DECLARE需要同时提交申报信息和贸易背景。
     * STANDARD不需要提交申报信息和贸易背景。
     * CUSTOMS同申报版相似，区别在于可不传银行卡号
     * </pre>
     */
    private String cashierVersion = "STANDARD";
    
    /**
     * <pre>
     * 贸易背景
     * GOODSTRADE货物贸易
     * PLANETICKET机票
     * HOTELACCOMMODATION酒店
     * STUDYABROAD留学
     * </pre>
     */
    private String forUse = "GOODSTRADE";
    
    /**
     * 用户在商户网站的会员ID，用于联名账户支付模式
     */
    private String merchantUserId = "";
    
    /**
     * 易汇金返给商户的绑卡对应信息，商户在支付请求中使用，可以不再提交用户身份信息和卡信息，通过绑卡支付完成支付
     */
    private String bindCardId = "";
    
    /**
     * 如果设置了clientIp（参数传本机ip），此订单将不能在其他ip继续支付
     */
    private String clientIp = "";
    
    /**
     * 此参数用于设置订单的超时时间（只能设置在二十四小时内），如：输入10，该订单将在10分钟后过期。
     */
    private String timeout = "1440";
    
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

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaymentModeCode() {
        return paymentModeCode;
    }

    public void setPaymentModeCode(String paymentModeCode) {
        this.paymentModeCode = paymentModeCode;
    }

    public List<EhkingProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<EhkingProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public EhkingPayer getPayer() {
        return payer;
    }

    public void setPayer(EhkingPayer payer) {
        this.payer = payer;
    }

    public EhkingBankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(EhkingBankCard bankCard) {
        this.bankCard = bankCard;
    }

    public String getCashierVersion() {
        return cashierVersion;
    }

    public void setCashierVersion(String cashierVersion) {
        this.cashierVersion = cashierVersion;
    }

    public String getForUse() {
        return forUse;
    }

    public void setForUse(String forUse) {
        this.forUse = forUse;
    }

    public String getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(String merchantUserId) {
        this.merchantUserId = merchantUserId;
    }

    public String getBindCardId() {
        return bindCardId;
    }

    public void setBindCardId(String bindCardId) {
        this.bindCardId = bindCardId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getHmac() {
        return hmac;
    }

    public void setHmac(String hmac) {
        this.hmac = hmac;
    }
}
