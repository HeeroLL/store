package com.isell.ei.logistics.sf.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * 路由查询响应报文
 * 
 * @author lilin
 * @version [版本号, 2015年7月1日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class SRouteResponse {
    
    /** 客户订单号 */
    @XmlAttribute
    private String orderid;
    
    /** 顺丰运单号 */
    @XmlAttribute
    private String mailno;
    
    /** 响应数据 */
    @XmlElement(name = "Route")
    private List<SRoute> route;
    
    public String getOrderid() {
        return orderid;
    }
    
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    
    public String getMailno() {
        return mailno;
    }
    
    public void setMailno(String mailno) {
        this.mailno = mailno;
    }
    
    public List<SRoute> getRoute() {
        return route;
    }
    
    public void setRoute(List<SRoute> route) {
        this.route = route;
    }
}
