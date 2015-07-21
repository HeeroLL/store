package com.sell.ei.logistics.ecm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sell.core.web.JsonData;
import com.sell.ei.logistics.ecm.service.EcmService;
import com.sell.ei.logistics.ecm.vo.EcmOrders;
import com.sell.ei.logistics.ecm.vo.EcmParam;

/**
 * 费舍尔ECM接口Controller
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
@Controller
@RequestMapping("logistics/ecm")
public class EcmController {
    /**
     * 费舍尔ECM服务接口
     */
    @Resource
    private EcmService ecmService;
    
    /**
     * ECM回调服务（订单生产状态回传）
     * 
     * @param param 参数
     * @return 返回值
     */
    @ResponseBody
    @RequestMapping("sendOrderStatus")
    public JsonData sendOrderStatus(EcmParam param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(ecmService.sendOrderStatus(param));
        return jsonData;
    }
    
    /**
     * ECM回调服务（订单批量发货）
     * 
     * @param param 参数
     * @return 返回值
     */
    @ResponseBody
    @RequestMapping("sendShipOrder")
    public JsonData sendShipOrder(EcmParam param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(ecmService.sendShipOrder(param));
        return jsonData;
    }
    
    /**
     * ECM推送订单
     * 
     * @param ecmOrders 订单列表
     * @return 封装后的ECM返回的处理结果
     */
    @ResponseBody
    @RequestMapping("pushSaleOrder")
    public JsonData pushSaleOrder(@RequestBody EcmOrders ecmOrders) {
        JsonData jsonData = new JsonData();
        jsonData.setData(ecmService.pushSaleOrder(ecmOrders));
        return jsonData;
    }
}
