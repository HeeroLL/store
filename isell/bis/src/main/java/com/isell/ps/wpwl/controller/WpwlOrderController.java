package com.isell.ps.wpwl.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isell.bis.config.BisConfig;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.ps.wpwl.vo.WpwlOrderInfo;
import com.isell.ps.wpwl.vo.WpwlOrderItem;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;

/**
 * 与杭州沃朴物联科技有限公司（防伪）商定的订单相关HTTP服务
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Controller
@RequestMapping("wpwl/order")
public class WpwlOrderController {
    /**
     * 订单接口
     */
    @Resource
    private OrderService orderService;
    
    @Resource
    private BisConfig config;
    
    /**
     * 获取订单信息接口
     *
     * @param jsonObj 订单参数
     * @return 订单信息
     */
    @RequestMapping("getOrderByOrderNo")
    public JsonData getOrderByOrderNo(String jsonObj) {
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        order = orderService.getCoolOrderDetailByOrderNo(order.getOrderNo());
        if (order == null) {
            throw new RuntimeException("exception.order.null");
        }
        // 组装封装结果
        JsonData jsonData = new JsonData();
        WpwlOrderInfo wpwlOrderInfo = new WpwlOrderInfo();
        wpwlOrderInfo.setId(String.valueOf(order.getId()));
        wpwlOrderInfo.setOrderNo(order.getOrderNo());
        wpwlOrderInfo.setAddress(order.getLocationP() + order.getLocationC() + order.getLocationA());
        wpwlOrderInfo.setCreatetime(order.getCreatetime());
        wpwlOrderInfo.setCustomerName(order.getLinkman());
        wpwlOrderInfo.setTotal(order.getTotal());
        if (order.getItemList() != null) {
            wpwlOrderInfo.setItems(new ArrayList<WpwlOrderItem>());
            for (CoolOrderItem item : order.getItemList()) {
                WpwlOrderItem wpwlOrderItem = new WpwlOrderItem();
                wpwlOrderItem.setName(item.getName());
                wpwlOrderItem.setLogo(config.getImgDomain() + item.getLogo());
                wpwlOrderItem.setCount(item.getCount());
                wpwlOrderItem.setPrice(item.getPrice());
                wpwlOrderItem.setUrl(config.getWapDomain() + "/product/" + item.getgId());
                wpwlOrderItem.setAttribute(new ArrayList<String>());
                wpwlOrderItem.getAttribute().add("gg:" + item.getGg());
                
                wpwlOrderInfo.getItems().add(wpwlOrderItem);
            }
        }
        jsonData.setData(wpwlOrderInfo);
        
        return jsonData;
    }
}
