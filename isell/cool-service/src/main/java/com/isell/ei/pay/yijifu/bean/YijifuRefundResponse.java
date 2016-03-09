package com.isell.ei.pay.yijifu.bean;

import java.math.BigDecimal;


/**
 * 易极付退款申请异步通知请求参数
 * 
 * @author wangpeng
 * @version [版本号, 2016年2月24日]
 */
public class YijifuRefundResponse extends YijifuResponse {
    /**
     * 交易号 商家发起交易时的编号
     */
    private String tradeNo;

    /**
     * 订单号 申请退款时传入outOrderNo
     */
    private String orderNo;
    
    /**
     * 退款编号 易极付的退款编号
     */
    private String refundNo;
    
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    
    /**
     * 退款完成时间 格式yyyy-MM-dd hh:mm:ss
     */
    private String refundFinishTime;
    
    /**
     * 货币种类
     */
    private String currency;
    
    /**
     * 退款状态 true退款成功,false退款失败
     */
    private String executeStatus;
    
    /**
     * 退款处理描述信息
     */
    private String message;

    /**
     * 交易号 商家发起交易时的编号
     */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
     * 交易号 商家发起交易时的编号
     */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/**
     * 订单号 申请退款时传入outOrderNo
     */
	public String getOrderNo() {
		return orderNo;
	}

	/**
     * 订单号 申请退款时传入outOrderNo
     */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
     * 退款编号 易极付的退款编号
     */
	public String getRefundNo() {
		return refundNo;
	}

	/**
     * 退款编号 易极付的退款编号
     */
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	 /**
     * 退款金额
     */
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	 /**
     * 退款金额
     */
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	/**
     * 退款完成时间 格式yyyy-MM-dd hh:mm:ss
     */
	public String getRefundFinishTime() {
		return refundFinishTime;
	}

	/**
     * 退款完成时间 格式yyyy-MM-dd hh:mm:ss
     */
	public void setRefundFinishTime(String refundFinishTime) {
		this.refundFinishTime = refundFinishTime;
	}

	/**
     * 货币种类
     */
	public String getCurrency() {
		return currency;
	}

	/**
     * 货币种类
     */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
     * 退款状态 true退款成功,false退款失败
     */
	public String getExecuteStatus() {
		return executeStatus;
	}

	/**
     * 退款状态 true退款成功,false退款失败
     */
	public void setExecuteStatus(String executeStatus) {
		this.executeStatus = executeStatus;
	}

	 /**
     * 退款处理描述信息
     */
	public String getMessage() {
		return message;
	}

	 /**
     * 退款处理描述信息
     */
	public void setMessage(String message) {
		this.message = message;
	}
    
}
