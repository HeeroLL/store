package com.isell.ps.jooge.bean;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 订单信息
 * 
 * @author wangweihua
 * @version [版本号, 2015年10月16日]
 */
public class Order {
    /**
     * 订单号，可唯一标识订单的编号
     */
    @JsonProperty("OrderId")
    private String orderId;
    
    /**
     * 订单创建时间
     */
    @JsonProperty("Created")
    private String created;
    
    /**
     * 订单最后修改时间
     */
    @JsonProperty("Modified")
    private String modified;
    
    /**
     * 订单付款时间，未付款订单可以不填
     */
    @JsonProperty("PayTime")
    private String payTime;
    
    /**
     * 订单状态，见数据字典
     */
    @JsonProperty("Status")
    private String status;
    
    /**
     * 发货仓库
     */
    @JsonProperty("ShippingWarehouse")
    private String shippingWarehouse;
    
    /**
     * 收货人姓名
     */
    @JsonProperty("ReceiverName")
    private String receiverName;
    
    /**
     * 收货人所在省
     */
    @JsonProperty("ReceiverProvince")
    private String receiverProvince;
    
    /**
     * 收货人所在市
     */
    @JsonProperty("ReceiverCity")
    private String receiverCity;
    
    /**
     * 收货人所在区县
     */
    @JsonProperty("ReceiverDistrict")
    private String receiverDistrict;
    
    /**
     * 收货人街道地址
     */
    @JsonProperty("ReceiverAddress")
    private String receiverAddress;
    
    /**
     * 收货人手机，和座机必须要有一项不能为空
     */
    @JsonProperty("ReceiverPhone1")
    private String receiverPhone1;
    
    /**
     * 收货人座机，和手机必须要有一项不能为空
     */
    @JsonProperty("ReceiverPhone2")
    private String receiverPhone2;
    
    /**
     * 邮编
     */
    @JsonProperty("ReceiverPostcode")
    private String receiverPostcode;
    
    /**
     * 顾客留言
     */
    @JsonProperty("BuyerMessage")
    private String buyerMessage;
    
    /**
     * 卖家备注
     */
    @JsonProperty("SellerComment")
    private String sellerComment;
    
    /**
     * 是否需要发票，0：否，1：是，默认值0
     */
    @JsonProperty("HasInvoice")
    private String hasInvoice;
    
    /**
     * 发票抬头，需要开具发票时，必填
     */
    @JsonProperty("InvoiceHead")
    private String invoiceHead;
    
    /**
     * 发票内容，需要开具发票时，必填
     */
    @JsonProperty("InvoiceComment")
    private String invoiceComment;
    
    /**
     * 顾客编码
     */
    @JsonProperty("BuyerCode")
    private String buyerCode;
    
    /**
     * 买家真实姓名，跨境订单必填
     */
    @JsonProperty("BuyerTrueName")
    private String buyerTrueName;
    
    /**
     * 买家身份证号码，跨境订单必填
     */
    @JsonProperty("BuyerIdCardNo")
    private String buyerIdCardNo;
    
    /**
     * 买家邮箱地址，跨境订单必填
     */
    @JsonProperty("BuyerEmail")
    private String buyerEmail;
    
    /**
     * 买家手机号，跨境订单必填
     */
    @JsonProperty("BuyerPhone")
    private String buyerPhone;
    
    /**
     * 向买家收取的运费
     */
    @JsonProperty("FeeAmount")
    private Number feeAmount;
    
    /**
     * 整单优惠金额
     */
    @JsonProperty("Discount")
    private Number discount;
    
    /**
     * 整单付款金额 PayAmount = sum(row.Amount) – Discount+FeeAmount
     */
    @JsonProperty("PayAmount")
    private Number payAmount;
    
    /**
     * 发货方式（物流，自提）
     */
    @JsonProperty("DeliveryWay")
    private String deliveryWay;
    
    /**
     * 预约提货时间，用于自提
     */
    @JsonProperty("DeliveryTime")
    private Date deliveryTime;
    
    /**
     * 提货验证码，用于自提
     */
    @JsonProperty("VerificationCode")
    private Date verificationCode;
    
    /**
     * 订单支付信息，跨境订单必填
     */
    @JsonProperty("Payments")
    private List<Payment> payments;
    
    /**
     * 订单行信息
     */
    @JsonProperty("Rows")
    private List<OrderRow> rows;
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public String getCreated() {
        return created;
    }
    
    public void setCreated(String created) {
        this.created = created;
    }
    
    public String getModified() {
        return modified;
    }
    
    public void setModified(String modified) {
        this.modified = modified;
    }
    
    public String getPayTime() {
        return payTime;
    }
    
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getShippingWarehouse() {
        return shippingWarehouse;
    }
    
    public void setShippingWarehouse(String shippingWarehouse) {
        this.shippingWarehouse = shippingWarehouse;
    }
    
    public String getReceiverName() {
        return receiverName;
    }
    
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    
    public String getReceiverProvince() {
        return receiverProvince;
    }
    
    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
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
    
    public String getReceiverPhone1() {
        return receiverPhone1;
    }
    
    public void setReceiverPhone1(String receiverPhone1) {
        this.receiverPhone1 = receiverPhone1;
    }
    
    public String getReceiverPhone2() {
        return receiverPhone2;
    }
    
    public void setReceiverPhone2(String receiverPhone2) {
        this.receiverPhone2 = receiverPhone2;
    }
    
    public String getReceiverPostcode() {
        return receiverPostcode;
    }
    
    public void setReceiverPostcode(String receiverPostcode) {
        this.receiverPostcode = receiverPostcode;
    }
    
    public String getBuyerMessage() {
        return buyerMessage;
    }
    
    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }
    
    public String getSellerComment() {
        return sellerComment;
    }
    
    public void setSellerComment(String sellerComment) {
        this.sellerComment = sellerComment;
    }
    
    public String getInvoiceHead() {
        return invoiceHead;
    }
    
    public void setInvoiceHead(String invoiceHead) {
        this.invoiceHead = invoiceHead;
    }
    
    public String getInvoiceComment() {
        return invoiceComment;
    }
    
    public void setInvoiceComment(String invoiceComment) {
        this.invoiceComment = invoiceComment;
    }
    
    public String getBuyerCode() {
        return buyerCode;
    }
    
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }
    
    public String getBuyerTrueName() {
        return buyerTrueName;
    }
    
    public void setBuyerTrueName(String buyerTrueName) {
        this.buyerTrueName = buyerTrueName;
    }
    
    public String getBuyerIdCardNo() {
        return buyerIdCardNo;
    }
    
    public void setBuyerIdCardNo(String buyerIdCardNo) {
        this.buyerIdCardNo = buyerIdCardNo;
    }
    
    public String getBuyerEmail() {
        return buyerEmail;
    }
    
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }
    
    public String getBuyerPhone() {
        return buyerPhone;
    }
    
    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }
    
    public Number getFeeAmount() {
        return feeAmount;
    }
    
    public void setFeeAmount(Number feeAmount) {
        this.feeAmount = feeAmount;
    }
    
    public Number getDiscount() {
        return discount;
    }
    
    public void setDiscount(Number discount) {
        this.discount = discount;
    }
    
    public Number getPayAmount() {
        return payAmount;
    }
    
    public void setPayAmount(Number payAmount) {
        this.payAmount = payAmount;
    }
    
    public String getDeliveryWay() {
        return deliveryWay;
    }
    
    public void setDeliveryWay(String deliveryWay) {
        this.deliveryWay = deliveryWay;
    }
    
    public Date getDeliveryTime() {
        return deliveryTime;
    }
    
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    
    public Date getVerificationCode() {
        return verificationCode;
    }
    
    public void setVerificationCode(Date verificationCode) {
        this.verificationCode = verificationCode;
    }
    
    public List<Payment> getPayments() {
        return payments;
    }
    
    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
    
    public List<OrderRow> getRows() {
        return rows;
    }
    
    public void setRows(List<OrderRow> rows) {
        this.rows = rows;
    }
    
    public String getHasInvoice() {
        return hasInvoice;
    }
    
    public void setHasInvoice(String hasInvoice) {
        this.hasInvoice = hasInvoice;
    }
    
}
