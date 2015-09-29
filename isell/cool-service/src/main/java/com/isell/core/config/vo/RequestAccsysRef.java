package com.isell.core.config.vo;

import java.util.Date;

/**
 * 请求与接入系统关系类
 * 
 * @author lilin
 * @version [版本号, 2015年7月24日]
 */
public class RequestAccsysRef {
    /**
     * 主键id
     */
    private Integer id;
    
    /**
     * 请求唯一标示
     */
    private String requestId;
    
    /**
     * 接入编码
     */
    private String accessCode;
    
    /**
     * 请求时间
     */
    private Date requestTime;
    
    /**
     * 是否结束
     */
    private Boolean finish;
    
    /**
     * 默认构造函数
     */
    public RequestAccsysRef() {
    }
    
    /**
     * 带参构造函数
     */
    public RequestAccsysRef(String requestId, String accessCode) {
        this.requestId = requestId;
        this.accessCode = accessCode;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getRequestId() {
        return this.requestId;
    }
    
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    public String getAccessCode() {
        return this.accessCode;
    }
    
    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
    
    public Date getRequestTime() {
        return this.requestTime;
    }
    
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
    
    public Boolean getFinish() {
        return this.finish;
    }
    
    public void setFinish(Boolean finish) {
        this.finish = finish;
    }
}