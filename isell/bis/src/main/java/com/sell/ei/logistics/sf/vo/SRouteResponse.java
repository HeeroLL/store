package com.sell.ei.logistics.sf.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 下订单响应报文
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class SRouteResponse {

	/** 客户订单号 */
	@XmlAttribute
	private String orderid;
	/** 顺丰运单号，一个订单只能有一个母单号，如果是子母单的情况，以半角逗号分隔，主单号在第一个位置，如“755123456789,001123456789,002123456789” ，可用于顺丰电子运单标签打印。 */
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
