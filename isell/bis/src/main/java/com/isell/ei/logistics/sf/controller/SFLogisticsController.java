package com.isell.ei.logistics.sf.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.web.JsonData;
import com.isell.ei.logistics.sf.service.SFService;
import com.isell.ei.logistics.sf.vo.SOrder;
import com.isell.ei.logistics.sf.vo.SOrderSearch;
import com.isell.ei.logistics.sf.vo.SRouteRequest;

/**
 * 
 * 顺丰物流接口Controller
 * 
 * @author lilin
 * @version [版本号, 2015年7月4日]
 */
@Controller
@RequestMapping("logistics/sf")
public class SFLogisticsController {
    
    /**
     * 顺丰接口
     */
    @Resource
    private SFService sfService;
    
    /**
     * 顺丰下订单接口
     * 
     * @param order 订单明细
     * @return 封装后的处理结果
     */
    @RequestMapping("orderService")
    @ResponseBody
    public JsonData orderService(@RequestBody SOrder order) {
        JsonData jsonData = new JsonData();
        jsonData.setData(sfService.orderService(order));
        return jsonData;
    }
    
    /**
     * 路由查询接口
     * 
     * @param routeRequest 路由查询参数
     * @return 封装后的处理结果
     */
    @RequestMapping("routeService")
    @ResponseBody
    public JsonData routeService(@RequestBody SRouteRequest routeRequest) {
        JsonData jsonData = new JsonData();
        jsonData.setData(sfService.routeService(routeRequest));
        return jsonData;
    }
    
    /**
     * 订单结果查询接口
     * 
     * @param orderSearch 订单查询参数
     * @return 封装后的处理结果
     */
    @RequestMapping("orderSearchService")
    @ResponseBody
    public JsonData orderSearchService(@RequestBody SOrderSearch orderSearch) {
        JsonData jsonData = new JsonData();
        jsonData.setData(sfService.orderSearchService(orderSearch));
        return jsonData;
    }
}
