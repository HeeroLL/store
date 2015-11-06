package com.isell.ei.logistics.zhongtong.bean;

import java.util.Map;

/**
 * 中通响应信息
 * 
 * @author lilin
 * @version [版本号, 2015年10月23日]
 */
public class ZTOrderResponse {
    /**
     * 状态
     */
    private Boolean status;
    
    /**
     * 状态码
     */
    private String statusCode;
    
    /**
     * 消息
     */
    private String message;
    
    /**
     * 响应数据
     */
    private Map<String, String> result;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

}
