package com.isell.ei.pay.yijifu.bean;

/**
 * 易极付订单信息同步-物流信息
 * 
 * @author lilin
 * @version [版本号, 2016年2月19日]
 */
public class YijifuLogisticInfo {
    /**
     * 物流机构
     */
    private String logisticsCompany = "";
    
    /**
     * 运输单号
     */
    private String transportNumber = "";
    
    /**
     * 外部订单号
     */
    private String outOrderNo = "";
    
    /**
     * 收货人姓名
     */
    private String consigneeName;
    
    /**
     * 收货地址
     */
    private String consigneeAddress;
    
    /**
     * 收货人联系方式
     */
    private String consigneeContact;
    
    public String getLogisticsCompany() {
        return logisticsCompany;
    }
    
    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }
    
    public String getTransportNumber() {
        return transportNumber;
    }
    
    public void setTransportNumber(String transportNumber) {
        this.transportNumber = transportNumber;
    }
    
    public String getOutOrderNo() {
        return outOrderNo;
    }
    
    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }
    
    public String getConsigneeName() {
        return consigneeName;
    }
    
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }
    
    public String getConsigneeAddress() {
        return consigneeAddress;
    }
    
    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }
    
    public String getConsigneeContact() {
        return consigneeContact;
    }
    
    public void setConsigneeContact(String consigneeContact) {
        this.consigneeContact = consigneeContact;
    }
    
}
