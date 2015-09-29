package com.isell.core.config.vo;

/**
 * 系统映射信息类
 * 
 * @author lilin
 * @version [版本号, 2015年7月24日]
 */
public class SysMapping {
    /**
     * 主键id
     */
    private Integer id;
    
    /**
     * 接入编码 
     */
    private String accessCode;
    
    /**
     * 业务编码
     */
    private String serviceCode;
    
    /**
     * 回调URL
     */
    private String notifyUrl;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getAccessCode() {
        return this.accessCode;
    }
    
    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
    
    public String getServiceCode() {
        return this.serviceCode;
    }
    
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    public String getNotifyUrl() {
        return this.notifyUrl;
    }
    
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}