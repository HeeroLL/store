package com.sell.ei.logistics.ecm.vo;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sell.core.base.BaseInfo;

/**
 * SCM推送销售订单接口
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class EcmOrder extends BaseInfo {
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 8673987182872928631L;
    
    /** 订单编号,格式：客户编码+自定义(客户编码由ECM提供) */
    private String orderCode;
    
    /** 下单时间(格式：2014-02-18 15:58:11) */
    private Date orderDate;
    
    /** 外部平台订单编号，垂直平台默认空，ERP客户必填 */
    private String outCode;
    
    /** 快递公司（客户指定快递公司） */
    private String expressName;
    
    /** 快递运单号,(客户系统默认空) */
    private String expressNo;
    
    /** 收货姓名 */
    private String receiverName;
    
    /** 买家昵称 */
    private String buyernick;
    
    /** 手机号码 */
    private String mobile;
    
    /** 仓库代码（对应ECM系统仓库编码）杭州仓库默认：FLHZ01 */
    private String warehouseCode = "FLHZ01";
    
    /** 店铺ID */
    private String shopid;
    
    /** 店铺名称 */
    private String shop;
    
    /** 买家留言 */
    private String buyerMessage;
    
    /** 客服备注 */
    private String remark;
    
    /** 系统备注 */
    private String systemRemark;
    
    /** 固定电话 */
    private String telPhone;
    
    /** 省份 */
    private String province;
    
    /** 市 */
    private String city;
    
    /** 区县 */
    private String district;
    
    /** 收货地址 */
    private String receiverAddress;
    
    /** 邮编 */
    private String receiverZip;
    
    /**  */
    private String isCashOnDelivery;
    
    /** 支付类型 01:银行卡支 02:余额支付 03:其他 */
    private String payType;
    
    /** 支付公司编码(支付平台在杭州口岸备案编号) */
    private String payCompanyCode = "AYC";
    
    /** 支付单号(支付成功后，支付平台反馈给电商平台的支付单号) */
    private String payNumber;
    
    /** 订单总金额(货款+订单税款+运费) */
    private Double orderTotalAmount;
    
    /** 订单货款 */
    private Double orderGoodsAmount;
    
    /** 订单税款(交易过程中商家向用户征收的税款，免税模式填写0) */
    private Double orderTaxAmount;
    
    /** 运费(交易过程中商家向用户征收的运费，免邮模式填写0) */
    private Double feeAmount;
    
    /** 成交时间 */
    private Date tradeTime;
    
    /** 成交币制(人民币，对应代码142) */
    private String currCode = "142";
    
    /** 成交总价(“订单总金额”扣除“折扣”之后的金额) */
    private Double totalAmount;
    
    /** 购买人ID(消费者下单时在电商平台的注册ID) */
    private String purchaserId;
    
    /** 支付人ID(购买人在电商平台上注册ID，可与purchaserId一样) */
    private String id;
    
    /** 购买人姓名 */
    private String name;
    
    /** 联系电话 */
    private String telNumber;
    
    /** 证件类型代码(01身份证（试点期间只能是身份证）02护照03其他) */
    private String paperType = "01";
    
    /** 证件号码(购买人身份证号) */
    private String paperNumber;
    
    /** 购买人地址 */
    private String address;
    
    /** 订单详情 */
    @JsonProperty("OrderDtls")
    private List<EcmCommodity> orderDtls;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getBuyernick() {
        return buyernick;
    }

    public void setBuyernick(String buyernick) {
        this.buyernick = buyernick;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSystemRemark() {
        return systemRemark;
    }

    public void setSystemRemark(String systemRemark) {
        this.systemRemark = systemRemark;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public String getIsCashOnDelivery() {
        return isCashOnDelivery;
    }

    public void setIsCashOnDelivery(String isCashOnDelivery) {
        this.isCashOnDelivery = isCashOnDelivery;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayCompanyCode() {
        return payCompanyCode;
    }

    public void setPayCompanyCode(String payCompanyCode) {
        this.payCompanyCode = payCompanyCode;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public Double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public Double getOrderGoodsAmount() {
        return orderGoodsAmount;
    }

    public void setOrderGoodsAmount(Double orderGoodsAmount) {
        this.orderGoodsAmount = orderGoodsAmount;
    }

    public Double getOrderTaxAmount() {
        return orderTaxAmount;
    }

    public void setOrderTaxAmount(Double orderTaxAmount) {
        this.orderTaxAmount = orderTaxAmount;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(String purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getPaperNumber() {
        return paperNumber;
    }

    public void setPaperNumber(String paperNumber) {
        this.paperNumber = paperNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<EcmCommodity> getOrderDtls() {
        return orderDtls;
    }

    public void setOrderDtls(List<EcmCommodity> orderDtls) {
        this.orderDtls = orderDtls;
    }
}
