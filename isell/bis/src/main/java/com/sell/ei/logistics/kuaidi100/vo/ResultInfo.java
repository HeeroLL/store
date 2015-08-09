package com.sell.ei.logistics.kuaidi100.vo;

import java.util.List;

import com.sell.core.base.BaseInfo;

/**
 * 快递100返回结果
 * 
 * @author lilin
 * @version [版本号, 2015年8月9日]
 */
public class ResultInfo extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2160885722314873391L;
    
    /**
     * 快递单当前的状态 。0：在途中,1：已发货，2：疑难件，3： 已签收 ，4：已退货。
     */
    private String status;
    
    /**
     * 成功返回ok
     */
    private String message;
    
    /**
     * 数据集合
     */
    private List<ContentInfo> data;
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public List<ContentInfo> getData() {
        return data;
    }
    
    public void setData(List<ContentInfo> data) {
        this.data = data;
    }
}
