package com.isell.ei.weixinop.bean;

import org.codehaus.jackson.annotate.JsonProperty;

import com.isell.core.base.BaseInfo;

/**
 * 微信返回的tocken
 * 
 * @author lilin
 * @version [版本号, 2015年7月29日]
 */
public class WeixinTocken extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1250997473879286011L;

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
     * 用户刷新access_token 
     */
    @JsonProperty("refresh_token")
    private String refreshToken;
    
    /**
     * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID 
     */
    private String openid;
    
    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;
    
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
     */
    private String unionid;
    
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
