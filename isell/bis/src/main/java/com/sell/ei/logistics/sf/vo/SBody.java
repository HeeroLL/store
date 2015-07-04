package com.sell.ei.logistics.sf.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class SBody {

	/** 订单主体信息 */
	@XmlElement(name = "Order")
	private SOrder order;
	/** 路由查询请求信息 */
    @XmlElement(name = "RouteRequest")
    private SRouteRequest routeRequest;
    /** 订单响应信息 */
	@XmlElement(name = "OrderResponse")
	private SOrderResponse orderResponse;
	/** 路由查询响应信息 */
    @XmlElement(name = "RouteResponse")
	private SRouteResponse routeResponse; 
	
	public SRouteResponse getRouteResponse() {
        return routeResponse;
    }
    public void setRouteResponse(SRouteResponse routeResponse) {
        this.routeResponse = routeResponse;
    }
    public SOrder getOrder() {
		return order;
	}
	public void setOrder(SOrder order) {
		this.order = order;
	}
	public SOrderResponse getOrderResponse() {
		return orderResponse;
	}
	public void setOrderResponse(SOrderResponse orderResponse) {
		this.orderResponse = orderResponse;
	}
	public SRouteRequest getRouteRequest() {
        return routeRequest;
    }
    public void setRouteRequest(SRouteRequest routeRequest) {
        this.routeRequest = routeRequest;
    }
	
	
}
