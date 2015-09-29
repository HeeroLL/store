package com.isell.core.auth.bean;

import com.isell.core.base.BaseInfo;

/**
 * 请求参数
 * 
 * @author lilin
 * @version [版本号, 2015年7月22日]
 */
public class RequestParameter extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5516034673381444676L;

    /**
     * 接入编码（由BIS统一分配给客户）
     */
    private String accessCode;
    
    /**
     * 验证码
     */
    private String authCode;
    
    /**
     * json参数
     */
    private String jsonObj;
    
    public String getAccessCode() {
        return accessCode;
    }
    
    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
    
    public String getAuthCode() {
        return authCode;
    }
    
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
    
    public String getJsonObj() {
        return jsonObj;
    }
    
    public void setJsonObj(String jsonObj) {
        this.jsonObj = jsonObj;
    }
}
