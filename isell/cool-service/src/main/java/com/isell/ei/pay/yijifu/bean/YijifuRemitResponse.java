package com.isell.ei.pay.yijifu.bean;


/**
 * 易极付汇款申请异步通知请求参数
 * 
 * @author lilin
 * @version [版本号, 2016年2月24日]
 */
public class YijifuRemitResponse extends YijifuResponse {
    /**
     * 跨境付款批次号
     */
    private String remittranceBatchNo;

    /**
     * 汇款申请状态
     */
    private String status;
    
    /**
     * 申请说明说明信息
     */
    private String message;

    public String getRemittranceBatchNo() {
        return remittranceBatchNo;
    }

    public void setRemittranceBatchNo(String remittranceBatchNo) {
        this.remittranceBatchNo = remittranceBatchNo;
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
