package com.isell.ei.logistics.yuantong.bean;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.isell.ei.logistics.yuantong.service.YuantongService;

/**
 * 订单请求信息
 * 
 * @author lilin
 * @version [版本号, 2015年9月6日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RequestOrder")
public class OrderRequest {
    /** 商家代码 */
    private String clientID = YuantongService.CLIENT_ID;
    
    /** 物流公司ID */
    private String logisticProviderID = "YTO";
    
    /** customerID与clientID的值一样) */
    private String customerId = YuantongService.CLIENT_ID;
    
    /** 物流订单号 */
    private String txLogisticID;
    
    /** 成功绑定的面单号 */
    private String mailNo;
    
    /** 成功绑定的大头笔信息 */
    private String bigPen;
    
    /** 订单类型(0-COD,1-普通订单,2-便携式订单3-退货单) */
    private int orderType = 1;
    
    /** 订单类型（该字段是为向下兼容预留） */
    private int type;
    
    /** 服务类型(1-上门揽收, 2-次日达 4-次晨达 8-当日达,0-自己联系)。默认为0 */
    private long serviceType = 0;
    
    /** 订单flag标识，便于以后分拣和标识默认为 0 */
    private int flag = 1;
    
    /** 发件人 */
    private AddressInfo sender;
    
    /** 收件人 */
    private AddressInfo receiver;
    
    /** 物流公司上门取货时间段，通过”yyyy-MM-dd HH:mm:ss”格式化，本文中所有时间格式相同。 */
    private Date sendStartTime;
    
    /** 物流公司上门取货时间段，通过”yyyy-MM-dd HH:mm:ss”格式化，本文中所有时间格式相同。 */
    private Date sendEndTime;
    
    /** 商品金额，包括优惠和运费，但无服务费 */
    private double goodsValue;
    
    /** goodsValue+总服务费 */
    private double totalValue;
    
    /** 保值金额（暂时没有使用，默认为0.0） */
    private double insuranceValue = 0.0;
    
    /** 商品类型（保留字段，暂时不用） */
    private int special = 0;
    
    /** 备注 */
    private String remark;
    
    /** 总服务费[COD] */
    private double totalServiceFee = 0.0;
    
    /** 物流公司分润[COD] */
    private double codSplitFee = 0.0;
    
    /** 商品集合 */
    @XmlElementWrapper(name ="items")
    @XmlElement(name ="item")
    private List<ItemInfo> items;
    
    public String getClientID() {
        return clientID;
    }
    
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
    
    public String getLogisticProviderID() {
        return logisticProviderID;
    }
    
    public void setLogisticProviderID(String logisticProviderID) {
        this.logisticProviderID = logisticProviderID;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getTxLogisticID() {
        return txLogisticID;
    }
    
    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }
    
    public int getOrderType() {
        return orderType;
    }
    
    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }
    
    public long getServiceType() {
        return serviceType;
    }
    
    public void setServiceType(long serviceType) {
        this.serviceType = serviceType;
    }
    
    public int getFlag() {
        return flag;
    }
    
    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    public AddressInfo getSender() {
        return sender;
    }
    
    public void setSender(AddressInfo sender) {
        this.sender = sender;
    }
    
    public AddressInfo getReceiver() {
        return receiver;
    }
    
    public void setReceiver(AddressInfo receiver) {
        this.receiver = receiver;
    }
    
    public Date getSendStartTime() {
        return sendStartTime;
    }
    
    public void setSendStartTime(Date sendStartTime) {
        this.sendStartTime = sendStartTime;
    }
    
    public Date getSendEndTime() {
        return sendEndTime;
    }
    
    public void setSendEndTime(Date sendEndTime) {
        this.sendEndTime = sendEndTime;
    }
    
    public double getGoodsValue() {
        return goodsValue;
    }
    
    public void setGoodsValue(double goodsValue) {
        this.goodsValue = goodsValue;
    }
    
    public double getTotalValue() {
        return totalValue;
    }
    
    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
    
    public double getInsuranceValue() {
        return insuranceValue;
    }
    
    public void setInsuranceValue(double insuranceValue) {
        this.insuranceValue = insuranceValue;
    }
    
    public int getSpecial() {
        return special;
    }
    
    public void setSpecial(int special) {
        this.special = special;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public List<ItemInfo> getItems() {
        return items;
    }
    
    public void setItems(List<ItemInfo> items) {
        this.items = items;
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

    public double getTotalServiceFee() {
        return totalServiceFee;
    }

    public void setTotalServiceFee(double totalServiceFee) {
        this.totalServiceFee = totalServiceFee;
    }

    public double getCodSplitFee() {
        return codSplitFee;
    }

    public void setCodSplitFee(double codSplitFee) {
        this.codSplitFee = codSplitFee;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
