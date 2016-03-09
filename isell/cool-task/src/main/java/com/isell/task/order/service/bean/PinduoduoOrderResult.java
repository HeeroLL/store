package com.isell.task.order.service.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 拼多多订单查询结果
 * 
 * @author lilin
 * @version [版本号, 2015年12月23日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Order")
public class PinduoduoOrderResult {
    /**
     * 1，成功，0失败
     */
    @XmlElement(name = "Result")
    private String result;
    
    /**
     * 如果失败，返回原因
     */
    @XmlElement(name = "Cause")
    private String cause;
    
    /**
     * 订单第几页
     */
    @XmlElement(name = "Page")
    private Integer page;
    
    /**
     * 订单数量
     */
    @XmlElement(name = "OrderCount")
    private Integer orderCount;
    
    /**
     * 订单数组
     */
    @XmlElementWrapper(name = "OrderList")
    @XmlElement(name = "OrderNO")
    private List<String> orderList;
    
    /**
     * 订单号
     */
    @XmlElement(name = "OrderNO")
    private String orderNO;
    
    /**
     * 成交日期
     */
    @XmlElement(name = "DateTime")
    private String dateTime;
    
    /**
     * 买家用户名
     */
    @XmlElement(name = "BuyerID")
    private String buyerID;
    
    /**
     * 买家姓名
     */
    @XmlElement(name = "BuyerName")
    private String buyerName;
    
    /**
     * 国家
     */
    @XmlElement(name = "Country")
    private String country;
    
    /**
     * 省/州
     */
    @XmlElement(name = "Province")
    private String province;
    
    /**
     * 市/县
     */
    @XmlElement(name = "City")
    private String city;
    
    /**
     * 区/镇
     */
    @XmlElement(name = "Town")
    private String town;
    
    /**
     * 地址
     */
    @XmlElement(name = "Adr")
    private String adr;
    
    /**
     * 邮编
     */
    @XmlElement(name = "Zip")
    private String zip;
    
    /**
     * Email
     */
    @XmlElement(name = "Email")
    private String email;
    
    /**
     * 联系电话
     */
    @XmlElement(name = "Phone")
    private String phone;
    
    /**
     * 货款总额
     */
    @XmlElement(name = "Total")
    private BigDecimal total;
    
    /**
     * 货运费用
     */
    @XmlElement(name = "Postage")
    private BigDecimal postage;
    
    /**
     * 支付方式
     */
    @XmlElement(name = "PayAccount")
    private String payAccount;
    
    /**
     * 支付编号
     */
    @XmlElement(name = "PayID")
    private String payID;
    
    /**
     * 发货方式
     */
    @XmlElement(name = "LogisticsName")
    private String logisticsName;
    
    /**
     * 结算方式
     */
    @XmlElement(name = "Chargetype")
    private String chargetype;
    
    /**
     * 客户备注
     */
    @XmlElement(name = "CustomerRemark")
    private String customerRemark;
    
    /**
     * 发票抬头
     */
    @XmlElement(name = "InvoiceTitle")
    private String invoiceTitle;
    
    /**
     * 客服备注
     */
    @XmlElement(name = "Remark")
    private String remark;
    
    /**
     * 商品集
     */
    @XmlElement(name = "Item")
    private List<PinduoduoOrderItem> itemList;
    
    public String getResult() {
        return result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }
    
    public String getCause() {
        return cause;
    }
    
    public void setCause(String cause) {
        this.cause = cause;
    }
    
    public Integer getPage() {
        return page;
    }
    
    public void setPage(Integer page) {
        this.page = page;
    }
    
    public Integer getOrderCount() {
        return orderCount;
    }
    
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }
    
    public List<String> getOrderList() {
        return orderList;
    }
    
    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
    }
    
    public String getOrderNO() {
        return orderNO;
    }
    
    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }
    
    public String getDateTime() {
        return dateTime;
    }
    
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    
    public String getBuyerID() {
        return buyerID;
    }
    
    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }
    
    public String getBuyerName() {
        return buyerName;
    }
    
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
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
    
    public String getTown() {
        return town;
    }
    
    public void setTown(String town) {
        this.town = town;
    }
    
    public String getAdr() {
        return adr;
    }
    
    public void setAdr(String adr) {
        this.adr = adr;
    }
    
    public String getZip() {
        return zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public BigDecimal getPostage() {
        return postage;
    }
    
    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }
    
    public String getPayAccount() {
        return payAccount;
    }
    
    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }
    
    public String getPayID() {
        return payID;
    }
    
    public void setPayID(String payID) {
        this.payID = payID;
    }
    
    public String getLogisticsName() {
        return logisticsName;
    }
    
    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }
    
    public String getChargetype() {
        return chargetype;
    }
    
    public void setChargetype(String chargetype) {
        this.chargetype = chargetype;
    }
    
    public String getCustomerRemark() {
        return customerRemark;
    }
    
    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }
    
    public String getInvoiceTitle() {
        return invoiceTitle;
    }
    
    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PinduoduoOrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<PinduoduoOrderItem> itemList) {
        this.itemList = itemList;
    }
    
}
