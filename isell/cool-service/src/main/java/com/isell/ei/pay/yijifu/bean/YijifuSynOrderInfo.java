package com.isell.ei.pay.yijifu.bean;

/**
 * 易极付订单同步异步信息
 * 
 * @author lilin
 * @version [版本号, 2016年2月26日]
 */
public class YijifuSynOrderInfo {
    /**
     * 订单号
     */
    private String detailOrderSerialNo;
    
    /**
     * 汇款申请状态
     */
    private String status;
    
    /**
     * 申请说明信息
     */
    private String message;
    
    public String getDetailOrderSerialNo() {
        return detailOrderSerialNo;
    }
    
    public void setDetailOrderSerialNo(String detailOrderSerialNo) {
        this.detailOrderSerialNo = detailOrderSerialNo;
    }
    
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
}
