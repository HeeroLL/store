package com.isell.ps.order.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.config.BisConfig;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.core.util.Record;
import com.isell.service.order.po.CoolOrderParam;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;

/**
 * 订单相关对外接口
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Controller
@RequestMapping("ps/order")
public class PsOrderController {
    /**
     * 订单接口
     */
    @Resource
    private OrderService orderService;
    
    /**
     * 基础配置信息
     */
    @Resource
    private BisConfig config;
    
    /**
     * 保存订单信息接口
     *
     * @param jsonObj 订单参数
     * @return 订单信息
     */
    @RequestMapping("saveOrder")
    @ResponseBody
    public JsonData saveOrder(String jsonObj) {
        CoolOrderParam order = JsonUtil.readValue(jsonObj, CoolOrderParam.class);
        Record record = orderService.saveCoolOrder(order);
        JsonData jsonData = new JsonData();
        jsonData.setSuccess(record.getBoolean("success"));
        jsonData.setData(record.remove("success").getColumns());
        
        return jsonData;
    }
    
    /**
     * 支付页面
     *
     * @param jsonObj 支付参数
     * @return 跳转到支付页面
     */
    @RequestMapping("payPage")
    public String payPage(String jsonObj) {
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        order = orderService.getCoolOrderByOrderNo(order.getOrderNo());
        if (order == null) {
            throw new RuntimeException("exception.order.null");
        }
        
        return "redirect:" + config.getDomain() + "/user/payPage/" + order.getId();
    }
}
