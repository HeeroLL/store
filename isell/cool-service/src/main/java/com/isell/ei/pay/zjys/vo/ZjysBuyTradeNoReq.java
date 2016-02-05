package com.isell.ei.pay.zjys.vo;

import java.util.List;

/**
 * 浙江银商购买支付流水号请求参数信息
 * 
 * @author lilin
 * @version [版本号, 2016年1月28日]
 */
public class ZjysBuyTradeNoReq {
    /**
     * 后台配置登录帐号
     */
    private String account;
    
    /**
     * 当前时间戳
     */
    private String timestamp;
    
    /**
     * API方法  addorder:添加订单
     */
    private String method;
    
    /**
     * 签名
     */
    private String sign;
    
    /**
     * 订单集合
     */
    private List<ZjysOrderInfo> orders;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<ZjysOrderInfo> getOrders() {
        return orders;
    }

    public void setOrders(List<ZjysOrderInfo> orders) {
        this.orders = orders;
    }
}
