package com.sell.ei.weixinop.bean;

import com.sell.core.base.BaseInfo;

/**
 * 微信接口配置信息
 * 
 * @author lilin
 * @version [版本号, 2015年7月29日]
 */
public class WeixinConfig extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 8536705365257357846L;

    /**
     * 公众号的唯一标识
     */
    private String appId;
    
    /**
     * 生成签名的时间戳
     */
    private String timestamp;
    
    /**
     * 生成签名的随机串
     */
    private String nonceStr;
    
    /**
     * 签名
     */
    private String signature;
    
    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getNonceStr() {
        return nonceStr;
    }
    
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
    
    public String getSignature() {
        return signature;
    }
    
    public void setSignature(String signature) {
        this.signature = signature;
    }
}
