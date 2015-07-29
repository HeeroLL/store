package com.sell.ei.weixinop.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 微信返回的tocken
 * 
 * @author lilin
 * @version [版本号, 2015年7月29日]
 */
public class WeixinTocken {
    /**
     * 获取到的凭证
     */
    @JsonProperty("access_token")
    private String accessToken;
    
    /**
     * 凭证有效时间，单位：秒
     */
    @JsonProperty("expires_in")
    private Integer expiresIn;
    
    /**
     * ticket
     */
    private String ticket;
    
    /**
     * 错误码
     */
    private String errcode;
    
    /**
     * 错误描述
     */
    private String errmsg;
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public Integer getExpiresIn() {
        return expiresIn;
    }
    
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
