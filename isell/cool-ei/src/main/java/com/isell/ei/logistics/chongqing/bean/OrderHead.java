package com.isell.ei.logistics.chongqing.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderHead {
    /* 申报海关代码 */
    @XmlElement(name = "CUSTOMS_CODE")
    private String customsCode;
    
    /* 业务类型 */
    @XmlElement(name = "BIZ_TYPE_CODE")
    private String bizTypeCode;
    
    /* 原始订单编号 */
    @XmlElement(name = "ORIGINAL_ORDER_NO")
    private String originalOrderNo;
    
    /* 电商企业代码 */
    @XmlElement(name = "ESHOP_ENT_CODE")
    private String eshopEntCode;
    
    /* 电商企业名称 */
    @XmlElement(name = "ESHOP_ENT_NAME")
    private String eshopEntName;
    
    /* 起运国 */
    @XmlElement(name = "DESP_ARRI_COUNTRY_CODE")
    private String despArriCountryCode;
    
    /* 运输方式 */
    @XmlElement(name = "SHIP_TOOL_CODE")
    private String shipToolCode;
    
    /* 收货人身份证号码 */
    @XmlElement(name = "RECEIVER_ID_NO")
    private String receiverIdNo;
    
    /* 收货人姓名 */
    @XmlElement(name = "RECEIVER_NAME")
    private String receiverName;
    
    /* 收货人地址 */
    @XmlElement(name = "RECEIVER_ADDRESS")
    private String receiverAddress;
    
    /* 收货人电话 */
    @XmlElement(name = "RECEIVER_TEL")
    private String receiverTel;
    
    /* 货款总额 */
    @XmlElement(name = "GOODS_FEE")
    private Double goodsFee;
    
    /* 税金总额 */
    @XmlElement(name = "TAX_FEE")
    private Integer taxFee;
    
    @XmlElement(name = "GROSS_WEIGHT")
    private Double grossWeight;
    
    @XmlElement(name = "PROXY_ENT_CODE")
    private String proxyEntCode;
    
    @XmlElement(name = "PROXY_ENT_NAME")
    private String proxyEntName;
    
    @XmlElement(name = "SORTLINE_ID")
    private String sortlineId;
    
    @XmlElement(name = "ORDER_DETAIL")
    private List<OrderDetail> orderDetail;
    
    public String getCustomsCode() {
        return customsCode;
    }
    
    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }
    
    public String getBizTypeCode() {
        return bizTypeCode;
    }
    
    public void setBizTypeCode(String bizTypeCode) {
        this.bizTypeCode = bizTypeCode;
    }
    
    public String getOriginalOrderNo() {
        return originalOrderNo;
    }
    
    public void setOriginalOrderNo(String originalOrderNo) {
        this.originalOrderNo = originalOrderNo;
    }
    
    public String getEshopEntCode() {
        return eshopEntCode;
    }
    
    public void setEshopEntCode(String eshopEntCode) {
        this.eshopEntCode = eshopEntCode;
    }
    
    public String getEshopEntName() {
        return eshopEntName;
    }
    
    public void setEshopEntName(String eshopEntName) {
        this.eshopEntName = eshopEntName;
    }
    
    public String getDespArriCountryCode() {
        return despArriCountryCode;
    }
    
    public void setDespArriCountryCode(String despArriCountryCode) {
        this.despArriCountryCode = despArriCountryCode;
    }
    
    public String getShipToolCode() {
        return shipToolCode;
    }
    
    public void setShipToolCode(String shipToolCode) {
        this.shipToolCode = shipToolCode;
    }
    
    public String getReceiverIdNo() {
        return receiverIdNo;
    }
    
    public void setReceiverIdNo(String receiverIdNo) {
        this.receiverIdNo = receiverIdNo;
    }
    
    public String getReceiverName() {
        return receiverName;
    }
    
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    
    public String getReceiverAddress() {
        return receiverAddress;
    }
    
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
    
    public String getReceiverTel() {
        return receiverTel;
    }
    
    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }
    
    public Double getGoodsFee() {
        return goodsFee;
    }
    
    public void setGoodsFee(Double goodsFee) {
        this.goodsFee = goodsFee;
    }
    
    public Integer getTaxFee() {
        return taxFee;
    }
    
    public void setTaxFee(Integer taxFee) {
        this.taxFee = taxFee;
    }
    
    public Double getGrossWeight() {
        return grossWeight;
    }
    
    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }
    
    public String getProxyEntCode() {
        return proxyEntCode;
    }
    
    public void setProxyEntCode(String proxyEntCode) {
        this.proxyEntCode = proxyEntCode;
    }
    
    public String getProxyEntName() {
        return proxyEntName;
    }
    
    public void setProxyEntName(String proxyEntName) {
        this.proxyEntName = proxyEntName;
    }
    
    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }
    
    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
    
    public String getSortlineId() {
        return sortlineId;
    }
    
    public void setSortlineId(String sortlineId) {
        this.sortlineId = sortlineId;
    }
    
}
