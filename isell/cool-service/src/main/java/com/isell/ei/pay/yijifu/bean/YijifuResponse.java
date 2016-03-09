package com.isell.ei.pay.yijifu.bean;

/**
 * 易极付异步通知公共请求参数
 * 
 * @author lilin
 * @version [版本号, 2015年10月29日]
 */
public abstract class YijifuResponse {
    /**
     * 表示接口调用是否成功。 true：成功 false：失败
     */
    private String success;
    
    /**
     * 报文协议格式。
     */
    private String protocol;
    
    /**
     * 服务版本
     */
    private String service;
    
    /**
     * 接口版本
     */
    private String version;
    
    /**
     * 通知时间
     */
    private String notifyTime;
    
    /**
     * 签名方式
     */
    private String signType;
    
    /**
     * 签名
     */
    private String sign;
    
    /**
     * 返回码
     */
    private String resultCode;
    
    /**
     * 返回信息
     */
    private String resultMessage;
    
    /**
     * 合作商系统唯 一订单号
     */
    private String orderNo;
    
    public String getSuccess() {
        return success;
    }
    
    public void setSuccess(String success) {
        this.success = success;
    }
    
    public String getProtocol() {
        return protocol;
    }
    
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    
    public String getService() {
        return service;
    }
    
    public void setService(String service) {
        this.service = service;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getNotifyTime() {
        return notifyTime;
    }
    
    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }
    
    public String getSignType() {
        return signType;
    }
    
    public void setSignType(String signType) {
        this.signType = signType;
    }
    
    public String getSign() {
        return sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    public String getResultCode() {
        return resultCode;
    }
    
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    
    public String getResultMessage() {
        return resultMessage;
    }
    
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    
    public String getOrderNo() {
        return orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
