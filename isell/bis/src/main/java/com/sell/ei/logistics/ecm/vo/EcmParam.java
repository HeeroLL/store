package com.sell.ei.logistics.ecm.vo;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sell.core.base.BaseInfo;

/**
 * ECM参数
 * 
 * @author lilin
 * @version [版本号, 2015年7月21日]
 */
public class EcmParam extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -29240074648487500L;
    
    /** 接口版本号 */
    private String v;
    
    /** ip地址 */
    private String ip;
    
    /** 会话key */
    private String sessionKey;
    
    /** 调用时间 */
    private String datetime;
    
    /** 校验码 */
    private String sign;
    
    /** JSON数据 */
    @JsonProperty("JSON_OBJ")
    private String jsonObj;
    
    public String getV() {
        return v;
    }
    
    public void setV(String v) {
        this.v = v;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getSessionKey() {
        return sessionKey;
    }
    
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
    
    public String getDatetime() {
        return datetime;
    }
    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    
    public String getSign() {
        return sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    public String getJsonObj() {
        return jsonObj;
    }
    
    public void setJsonObj(String jsonObj) {
        this.jsonObj = jsonObj;
    }
    
}
