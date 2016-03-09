package com.isell.ei.pay.yijifu.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 易极付跨境退款申请异步返回信息
 * 
 * @author wangpeng
 * @version [版本号, 2016年2月23日]
 */
public class CoolYjfRefundMessage{
    /**
     * 
     */
    private Integer id;
    /**
     * 通知时间
     */
    private String notifytime;
    /**
     * 返回码:EXECUTE_SUCCESS-为处理成功
     */
    private String resultcode;
    /**
     * 返回信息
     */
    private String resultmessage;
    /**
     * 交易号，商家发起交易时的编号
     */
    private String tradeno;
    /**
     * 订单号 申请退款时传入outOrderNo
     */
    private String orderno;
    /**
     * 易极付的退款编号
     */
    private String refundno;
    /**
     * 退款金额
     */
    private BigDecimal refundamount;
    /**
     * 退款完成时间 格式yyyy-MM-dd hh:mm:ss
     */
    private String refundfinishtime;
    /**
     * 货币种类
     */
    private String currency;
    /**
     * 退款状态  true退款成功,false退款失败
     */
    private String executestatus;
    /**
     * 退款处理描述信息
     */
    private String message;
    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 
     */
    public Integer getId(){
        return this.id;
    }

    /**
     * 
     */
    public void setId(Integer id){
        this.id = id;
    }    
    /**
     * 通知时间
     */
    public String getNotifytime(){
        return this.notifytime;
    }

    /**
     * 通知时间
     */
    public void setNotifytime(String notifytime){
        this.notifytime = notifytime;
    }    
    /**
     * 返回码:EXECUTE_SUCCESS-为处理成功
     */
    public String getResultcode(){
        return this.resultcode;
    }

    /**
     * 返回码:EXECUTE_SUCCESS-为处理成功
     */
    public void setResultcode(String resultcode){
        this.resultcode = resultcode;
    }    
    /**
     * 返回信息
     */
    public String getResultmessage(){
        return this.resultmessage;
    }

    /**
     * 返回信息
     */
    public void setResultmessage(String resultmessage){
        this.resultmessage = resultmessage;
    }    
    /**
     * 交易号，商家发起交易时的编号
     */
    public String getTradeno(){
        return this.tradeno;
    }

    /**
     * 交易号，商家发起交易时的编号
     */
    public void setTradeno(String tradeno){
        this.tradeno = tradeno;
    }    
    /**
     * 订单号 申请退款时传入outOrderNo
     */
    public String getOrderno(){
        return this.orderno;
    }

    /**
     * 订单号 申请退款时传入outOrderNo
     */
    public void setOrderno(String orderno){
        this.orderno = orderno;
    }    
    /**
     * 易极付的退款编号
     */
    public String getRefundno(){
        return this.refundno;
    }

    /**
     * 易极付的退款编号
     */
    public void setRefundno(String refundno){
        this.refundno = refundno;
    }    
    /**
     * 退款金额
     */
    public BigDecimal getRefundamount(){
        return this.refundamount;
    }

    /**
     * 退款金额
     */
    public void setRefundamount(BigDecimal refundamount){
        this.refundamount = refundamount;
    }    
    /**
     * 退款完成时间 格式yyyy-MM-dd hh:mm:ss
     */
    public String getRefundfinishtime(){
        return this.refundfinishtime;
    }

    /**
     * 退款完成时间 格式yyyy-MM-dd hh:mm:ss
     */
    public void setRefundfinishtime(String refundfinishtime){
        this.refundfinishtime = refundfinishtime;
    }    
    /**
     * 货币种类
     */
    public String getCurrency(){
        return this.currency;
    }

    /**
     * 货币种类
     */
    public void setCurrency(String currency){
        this.currency = currency;
    }    
    /**
     * 退款状态  true退款成功,false退款失败
     */
    public String getExecutestatus(){
        return this.executestatus;
    }

    /**
     * 退款状态  true退款成功,false退款失败
     */
    public void setExecutestatus(String executestatus){
        this.executestatus = executestatus;
    }    
    /**
     * 退款处理描述信息
     */
    public String getMessage(){
        return this.message;
    }

    /**
     * 退款处理描述信息
     */
    public void setMessage(String message){
        this.message = message;
    }    
    /**
     * 创建时间
     */
    public Date getCreatetime(){
        return this.createtime;
    }

    /**
     * 创建时间
     */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }    
}