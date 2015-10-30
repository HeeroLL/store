package com.isell.ei.logistics.zyb.bean;

import java.util.Map;

/**
 * 转运帮结果封装类
 * 
 * @author lilin
 * @version [版本号, 2015年10月27日]
 */
public class ZybResult {
    /**
     * 处理结果：0成功 1失败
     */
    private Integer s;
    
    /**
     * 提示信息
     */
    private String message;
    
    /**
     * 数据
     */
    private Map<String, Object> data;

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
