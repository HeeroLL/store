package com.sell.ei.logistics.sf.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 订单结果查询请求信息
 * 
 * @author lilin
 * @version [版本号, 2015年7月5日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SOrderSearch {
    /** 客户订单id */
    private String orderid;
    
    public String getOrderid() {
        return orderid;
    }
    
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
