package com.isell.ws.zhengzhou.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 消息详情
 * 
 * @author lilin
 * @version [版本号, 2015年10月21日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class Bodydetail {
    /**
     * 订单信息
     */
    @XmlElement(name = "ORDERLIST") 
    private List<OrderInfo> orderList;

    public List<OrderInfo> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderInfo> orderList) {
        this.orderList = orderList;
    }
}
