package com.isell.ws.ningbo.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 宁波优贝信息--订单参数
 * 
 * @author wangpeng
 * @version [版本号, 2015年11月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OrderMsg")
@XmlType(propOrder = {})
public class OrderMsg {
    /**
     * 订单主信息
     */
    @XmlElement(name = "Order")
    private Order order;
    
    /**
     * 用户注册信息
     */
    @XmlElement(name = "UserReg")
    private UserReg userReg;
    
    /**
     * 订单明细信息
     */
    @XmlElementWrapper(name ="OrderItem")
    @XmlElement(name = "Detail")
    private List<Detail> orderItems;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UserReg getUserReg() {
        return userReg;
    }

    public void setUserReg(UserReg userReg) {
        this.userReg = userReg;
    }

    public List<Detail> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Detail> orderItems) {
        this.orderItems = orderItems;
    }
}
