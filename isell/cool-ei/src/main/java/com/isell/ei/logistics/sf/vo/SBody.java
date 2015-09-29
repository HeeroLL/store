package com.isell.ei.logistics.sf.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class SBody {
    
    /** 下订单请求信息 */
    @XmlElement(name = "Order")
    private SOrder order;
    
    /** 下订单响应信息 */
    @XmlElement(name = "OrderResponse")
    private SOrderResponse orderResponse;
    
    /** 路由查询请求信息 */
    @XmlElement(name = "RouteRequest")
    private SRouteRequest routeRequest;
    
    /** 路由查询响应信息 */
    @XmlElement(name = "RouteResponse")
    private SRouteResponse routeResponse;
    
    /** 订单结果查询请求信息 */
    @XmlElement(name = "OrderSearch")
    private SOrderSearch orderSearch;
    
    /** 订单结果查询响应信息 */
    @XmlElement(name = "OrderSearchResponse")
    private SOrderSearchResponse orderSearchResponse;
    
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

    public SOrderSearch getOrderSearch() {
        return orderSearch;
    }

    public void setOrderSearch(SOrderSearch orderSearch) {
        this.orderSearch = orderSearch;
    }

    public SOrderSearchResponse getOrderSearchResponse() {
        return orderSearchResponse;
    }

    public void setOrderSearchResponse(SOrderSearchResponse orderSearchResponse) {
        this.orderSearchResponse = orderSearchResponse;
    }
    
}
