package com.isell.ei.pay.yijifu.bean;


/**
 * 易极付订单同步异步通知请求参数
 * 
 * @author lilin
 * @version [版本号, 2016年2月26日]
 */
public class YijifuSynOrderResponse extends YijifuResponse {
    /**
     * 批次号
     */
    private String remittranceBatchNo;
    
    /**
     * 订单响应信息
     */
    private String resultInfos;
    
    public String getRemittranceBatchNo() {
        return remittranceBatchNo;
    }
    
    public void setRemittranceBatchNo(String remittranceBatchNo) {
        this.remittranceBatchNo = remittranceBatchNo;
    }
    
    public String getResultInfos() {
        return resultInfos;
    }
    
    public void setResultInfos(String resultInfos) {
        this.resultInfos = resultInfos;
    }
}
