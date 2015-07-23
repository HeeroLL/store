package com.sell.ei.pay.weixin.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sell.core.base.BaseInfo;

/**
 * 微信支付结果通知参数
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class PayResultInfo extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 8081516179336445242L;

    /**
     * 返回状态码 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    
    /**
     * 返回信息
     */
    @XmlElement(name = "return_msg")
    private String returnMsg;
    
    /** 以下字段在return_code为SUCCESS的时候有返回 */
    
    /**
     * 公众账号ID
     */
    private String appid;
    
    /**
     * 商户号
     */
    @XmlElement(name = "mch_id")
    private String mchId;
    
    /**
     * 设备号
     */
    @XmlElement(name = "device_info")
    private String deviceInfo;
    
    /**
     * 随机字符串
     */
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    
    /**
     * 签名
     */
    private String sign;
    
    /**
     * 业务结果
     */
    @XmlElement(name = "result_code")
    private String resultCode;
    
    /**
     * 错误代码
     */
    @XmlElement(name = "err_code")
    private String errCode;
    
    /**
     * 错误代码描述
     */
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    
    /**
     * 用户标识
     */
    private String openid;
    
    /**
     * 是否关注公众账号
     */
    @XmlElement(name = "is_subscribe")
    private String isSubscribe;
    
    /**
     * 交易类型
     */
    @XmlElement(name = "trade_type")
    private String tradeType;
    
    /**
     * 付款银行
     */
    @XmlElement(name = "bank_type")
    private String bankType;
    
    /**
     * 总金额
     */
    @XmlElement(name = "total_fee")
    private Integer totalFee;
    
    /**
     * 货币种类
     */
    @XmlElement(name = "fee_type")
    private String feeType;
    
    /**
     * 现金支付金额
     */
    @XmlElement(name = "cash_fee")
    private Integer cashFee;
    
    /**
     * 现金支付货币类型
     */
    @XmlElement(name = "cash_fee_type")
    private String cashFeeType;
    
    /**
     * 微信支付订单号
     */
    @XmlElement(name = "transaction_id")
    private String transactionId;
    
    /**
     * 商户订单号
     */
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    
    /**
     * 商家数据包
     */
    private String attach;
    
    /**
     * 支付完成时间
     */
    @XmlElement(name = "time_end")
    private String timeEnd;
    
    public String getReturnCode() {
        return returnCode;
    }
    
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    
    public String getReturnMsg() {
        return returnMsg;
    }
    
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
    
    public String getAppid() {
        return appid;
    }
    
    public void setAppid(String appid) {
        this.appid = appid;
    }
    
    public String getMchId() {
        return mchId;
    }
    
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
    
    public String getDeviceInfo() {
        return deviceInfo;
    }
    
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
    
    public String getNonceStr() {
        return nonceStr;
    }
    
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
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
    
    public String getErrCode() {
        return errCode;
    }
    
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
    
    public String getErrCodeDes() {
        return errCodeDes;
    }
    
    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
    
    public String getOpenid() {
        return openid;
    }
    
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    
    public String getIsSubscribe() {
        return isSubscribe;
    }
    
    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }
    
    public String getTradeType() {
        return tradeType;
    }
    
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
    
    public String getBankType() {
        return bankType;
    }
    
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
    
    public Integer getTotalFee() {
        return totalFee;
    }
    
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }
    
    public String getFeeType() {
        return feeType;
    }
    
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    
    public Integer getCashFee() {
        return cashFee;
    }
    
    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }
    
    public String getCashFeeType() {
        return cashFeeType;
    }
    
    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getOutTradeNo() {
        return outTradeNo;
    }
    
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    
    public String getAttach() {
        return attach;
    }
    
    public void setAttach(String attach) {
        this.attach = attach;
    }
    
    public String getTimeEnd() {
        return timeEnd;
    }
    
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
