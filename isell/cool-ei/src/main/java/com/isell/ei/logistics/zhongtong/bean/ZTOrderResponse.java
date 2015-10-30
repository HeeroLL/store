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
    private boolean status;
    
    /**
     * 消息
     */
    private String msg;
    
    /**
     * 响应数据
     */
    private Map<String, String> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
