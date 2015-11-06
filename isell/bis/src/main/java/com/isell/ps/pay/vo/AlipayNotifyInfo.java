package com.isell.ps.pay.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 支付宝异步通知信息
 * 
 * @author lilin
 * @version [版本号, 2015年10月12日]
 */
public class AlipayNotifyInfo {
    /**
     * 通知的发送时间。格式为 yyyy-MM-ddHH:mm:ss。
     */
    private String notify_time;
    
    /**
     * 通知的类型。trade_status_sync
     */
    private String notify_type;
    
    /**
     * 通知校验ID
     */
    private String notify_id;
    
    /**
     * 签名方式 MD5
     */
    private String sign_type;
    
    /**
     * 签名
     */
    private String sign;
    
    /**
     * 对应商户网站的订单系统中的唯一订单号，非支付宝交易号。需保证在商户网站中的唯一性。是请求时对应的参数，原样返回。
     */
    private String out_trade_no;
    
    /**
     * 通知动作类型： 创建： createDirectPayTradeBy BuyerAction 支付： payByAccountAction 退款：refundFPAction 撤销：reverseAction
     * 关闭：closeTradeAction 交易完成： finishFPAction
     */
    private String notify_action_type;
    
    /**
     * 该交易在支付宝系统中的交易流水号。最长 64 位。
     */
    private String trade_no;
    
    /**
     * 重写 toString
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
    public String getNotify_type() {
        return notify_type;
    }
    
    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }
    
    public String getNotify_id() {
        return notify_id;
    }
    
    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }
    
    public String getSign_type() {
        return sign_type;
    }
    
    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }
    
    public String getSign() {
        return sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    public String getOut_trade_no() {
        return out_trade_no;
    }
    
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    
    public String getTrade_no() {
        return trade_no;
    }
    
    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }
    
    public String getNotify_time() {
        return notify_time;
    }
    
    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getNotify_action_type() {
        return notify_action_type;
    }

    public void setNotify_action_type(String notify_action_type) {
        this.notify_action_type = notify_action_type;
    }
}
