package com.isell.ws.ningbo.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.isell.ws.ningbo.service.YoubeiService;

/**
 * 宁波优贝信息--订单参数--Order订单主信息
 * 
 * @author wangpeng
 * @version [版本号, 2015年11月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class Order {
    /**
     * 购物网站代码(0001淘宝，0002天猫，0003天猫国际，0004善融商务，0000电商自主平台)
     */
    @XmlElement(name = "KJ_OrderFrom")
    private String klOrderFrom = "0000";
    
    /**
     * 店铺
     */
    @XmlElement(name = "ShopID")
    private Integer shopID = YoubeiService.SHOPID;
    
    /**
     * 支付方式(01中国银联，02支付宝，03盛付通，04建设银行，05中国银行)
     */
    @XmlElement(name = "KJ_PayType")
    private String kjPayType = "02";
    
    /**
     * 交易号(包括支付宝和其他平台)
     */
    @XmlElement(name = "PayNo")
    private String payNo;
    
    /**
     * 订单编号
     */
    @XmlElement(name = "Tid")
    private String tid;
    
    /**
     * 交易创建时间
     */
    @XmlElement(name = "Created")
    private Date created;
    
    /**
     * 付款时间
     */
    @XmlElement(name = "PayTime")
    private Date payTime;
    
    /**
     * 买家昵称
     */
    @XmlElement(name = "BuyerNick")
    private String buyerNick;
    
    /**
     * 买家留言[可空]
     */
    @XmlElement(name = "BuyerMessage")
    private String buyerMessage = "";
    
    /**
     * 邮费
     */
    @XmlElement(name = "PostFee")
    private BigDecimal postFee;
    
    /**
     * 实付金额
     */
    @XmlElement(name = "Payment")
    private BigDecimal payment;
    
    /**
     * 总税额
     */
    @XmlElement(name = "TotalRate")
    private BigDecimal totalRate;
    
    /**
     * 收货人
     */
    @XmlElement(name = "ReceiverName")
    private String receiverName;
    
    /**
     * 收货人所在省份(省份需和电子口岸提供的省份名称一致)
     */
    @XmlElement(name = "ReceiverState")
    private String receiverState;
    
    /**
     * 收货人所在城市
     */
    @XmlElement(name = "ReceiverCity")
    private String receiverCity;
    
    /**
     * 收货人所在街道
     */
    @XmlElement(name = "ReceiverDistrict")
    private String receiverDistrict;
    
    /**
     * 收货人地址[可空]
     */
    @XmlElement(name = "ReceiverAddress")
    private String receiverAddress;
    
    /**
     * 邮编[可空]
     */
    @XmlElement(name = "ReceiverZip")
    private String receiverZip = "";
    
    /**
     * 收货人手机
     */
    @XmlElement(name = "ReceiverMobile")
    private String receiverMobile;
    
    /**
     * 收货人电话[可空]
     */
    @XmlElement(name = "ReceiverPhone")
    private String receiverPhone = "";
    
    /**
     * 卖家备注[可空]
     */
    @XmlElement(name = "SellMemo")
    private String sellMemo = "";
    
    /**
     * 物流名称[可空]
     */
    @XmlElement(name = "LogName")
    private String logName = "";
    
    /**
     * 优惠金额[可空]
     */
    @XmlElement(name = "ProAmount")
    private Double proAmount = 0.0;
    
    /**
     * 优惠说明[可空]
     */
    @XmlElement(name = "ProRemark")
    private String proRemark = "";
    
    public String getKlOrderFrom() {
        return klOrderFrom;
    }
    
    public void setKlOrderFrom(String klOrderFrom) {
        this.klOrderFrom = klOrderFrom;
    }
    
    public Integer getShopID() {
        return shopID;
    }
    
    public void setShopID(Integer shopID) {
        this.shopID = shopID;
    }
    
    public String getKjPayType() {
        return kjPayType;
    }
    
    public void setKjPayType(String kjPayType) {
        this.kjPayType = kjPayType;
    }
    
    public String getPayNo() {
        return payNo;
    }
    
    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }
    
    public String getTid() {
        return tid;
    }
    
    public void setTid(String tid) {
        this.tid = tid;
    }
    
    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    
    public Date getPayTime() {
        return payTime;
    }
    
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    
    public String getBuyerNick() {
        return buyerNick;
    }
    
    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }
    
    public String getBuyerMessage() {
        return buyerMessage;
    }
    
    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }
    
    public String getReceiverName() {
        return receiverName;
    }
    
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    
    public String getReceiverState() {
        return receiverState;
    }
    
    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }
    
    public String getReceiverCity() {
        return receiverCity;
    }
    
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }
    
    public String getReceiverDistrict() {
        return receiverDistrict;
    }
    
    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }
    
    public String getReceiverAddress() {
        return receiverAddress;
    }
    
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
    
    public String getReceiverZip() {
        return receiverZip;
    }
    
    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
    }
    
    public String getReceiverMobile() {
        return receiverMobile;
    }
    
    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }
    
    public String getReceiverPhone() {
        return receiverPhone;
    }
    
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
    
    public String getSellMemo() {
        return sellMemo;
    }
    
    public void setSellMemo(String sellMemo) {
        this.sellMemo = sellMemo;
    }
    
    public String getLogName() {
        return logName;
    }
    
    public void setLogName(String logName) {
        this.logName = logName;
    }
    
    public Double getProAmount() {
        return proAmount;
    }
    
    public void setProAmount(Double proAmount) {
        this.proAmount = proAmount;
    }
    
    public String getProRemark() {
        return proRemark;
    }
    
    public void setProRemark(String proRemark) {
        this.proRemark = proRemark;
    }
    
    public BigDecimal getPostFee() {
        return postFee;
    }
    
    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }
    
    public BigDecimal getPayment() {
        return payment;
    }
    
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }
    
    public BigDecimal getTotalRate() {
        return totalRate;
    }
    
    public void setTotalRate(BigDecimal totalRate) {
        this.totalRate = totalRate;
    }
}
