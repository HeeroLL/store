package com.isell.ei.logistics.yuantong.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 订单请求信息
 * 
 * @author lilin
 * @version [版本号, 2015年9月6日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response")
public class OrderResponse{
    /** 物流公司ID */
    private String logisticProviderID;
    
    /** 请求结果 */
    private String success;
    
    /** 成功绑定的面单号 */
    private String mailNo;
    
    /** 成功绑定的大头笔信息 */
    private String bigPen;
    
    /** 提示信息 */ 
    private String noticeMessage;
    
    /** 失败原因 */
    private String reason;
    
    /** 订单详情 */
    private OrderRequest orderMessage;

    public String getLogisticProviderID() {
        return logisticProviderID;
    }

    public void setLogisticProviderID(String logisticProviderID) {
        this.logisticProviderID = logisticProviderID;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getBigPen() {
        return bigPen;
    }

    public void setBigPen(String bigPen) {
        this.bigPen = bigPen;
    }

    public String getNoticeMessage() {
        return noticeMessage;
    }

    public void setNoticeMessage(String noticeMessage) {
        this.noticeMessage = noticeMessage;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public OrderRequest getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(OrderRequest orderMessage) {
        this.orderMessage = orderMessage;
    }
    
}
