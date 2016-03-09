package com.isell.task.order.service.bean;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("jsonData")
public class KalemaoResult {
    
    /** 提示信息 */
    @JsonProperty("msg")
    private String msg;
    
    /** 扩展数据 */
    @JsonProperty("data")
    private boolean data;
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public boolean isData() {
        return data;
    }
    
    public void setData(boolean data) {
        this.data = data;
    }
    
}
