package com.isell.ei.logistics.yitong.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 易通响应
 * 
 * @author lilin
 * @version [版本号, 2015年12月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class YitongResult {
    /**
     * 校验码
     */
    @XmlElement(name = "CheckData")
    private String checkData;
    
    /**
     * ecpCode
     */
    private String ecpCode;
    
    /**
     * 订单号
     */
    private String orderNo;
    
    /**
     * 申通运单编号
     */
    private String logisticsNo;
    
    /**
     * 状态
     */
    @XmlElement(name = "Status")
    private String status;
    
    /**
     * 备注
     */
    @XmlElement(name = "Remark")
    private String remark;
    
    /**
     * time
     */
    private String inputTime;

    public String getCheckData() {
        return checkData;
    }

    public void setCheckData(String checkData) {
        this.checkData = checkData;
    }

    public String getEcpCode() {
        return ecpCode;
    }

    public void setEcpCode(String ecpCode) {
        this.ecpCode = ecpCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }
}
