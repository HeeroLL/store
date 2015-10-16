package com.isell.ps.jooge.bean;

/**
 * 珊瑚云参数数据
 * 
 * @author lilin
 * @version [版本号, 2015年10月15日]
 */
public class JoogeParam {
    /**
     * 应用标识
     */
    private String appkey;
    
    /**
     * API方法名称
     */
    private String method;
    
    /**
     * API协议版本
     */
    private String v;
    
    /**
     * 时间戳
     */
    private String timestamp;
    
    /**
     * 参数内容（json格式）
     */
    private String param;
    
    /**
     * 参数的md5加密字符串，详情参考系统签名
     */
    private String sign;

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
