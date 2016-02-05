package com.isell.ei.logistics.zhongtong.bean;

import java.util.Map;

/**
 * 海关运单回执
 * 
 * @author lilin
 * @version [版本号, 2015年11月6日]
 */
public class ZTInsertBillResponse {
    /**
     * 状态
     */
    private Boolean status; 
    
    /**
     * 消息
     */
    private String msg;
    
    /**
     * 响应数据
     */
    private Map<String, String> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
