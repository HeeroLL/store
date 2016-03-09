package com.isell.ei.pay.yijifu.bean;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 易极付支付异步通知请求参数
 * 
 * @author lilin
 * @version [版本号, 2015年10月29日]
 */
public class YijifuPayResponse extends YijifuResponse {
    /**
     * 商户订单号
     */
    private String outOrderNo;
    
    /**
     * 交易号
     */
    private String tradeNo;
    
    /**
     * 账期
     */
    private String accountDay;
    
    /**
     * 合作商id
     */
    private String partnerId;
    
    /**
     * 交易类型
     */
    private String tradeType;
    
    /**
     * 交易金额
     */
    private String tradeAmount;
    
    /**
     * 交易状态
     */
    private String tradeStatus;
    
    /**
     * 转map
     * 
     * @return map
     */
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        Method[] m = this.getClass().getMethods();
        for (int i = 0; i < m.length; i++) {
            String method = m[i].getName();
            if (method.startsWith("get") && !"getClass".equals(method)) {
                try {
                    Object value = m[i].invoke(this);
                    if (value != null) {
                        String key = method.substring(3);
                        key = key.substring(0, 1).toUpperCase() + key.substring(1);
                        map.put(key, (String)value);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        
        return map;
    }
    
    public String getOutOrderNo() {
        return outOrderNo;
    }
    
    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }
    
    public String getTradeNo() {
        return tradeNo;
    }
    
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
    
    public String getAccountDay() {
        return accountDay;
    }
    
    public void setAccountDay(String accountDay) {
        this.accountDay = accountDay;
    }
    
    public String getPartnerId() {
        return partnerId;
    }
    
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
    public String getTradeType() {
        return tradeType;
    }
    
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
    
    public String getTradeAmount() {
        return tradeAmount;
    }
    
    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }
    
    public String getTradeStatus() {
        return tradeStatus;
    }
    
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
}
