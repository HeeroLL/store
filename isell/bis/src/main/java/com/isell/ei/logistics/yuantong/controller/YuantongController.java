package com.isell.ei.logistics.yuantong.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.logistics.yuantong.bean.OrderRequest;
import com.isell.ei.logistics.yuantong.bean.OrderResponse;
import com.isell.ei.logistics.yuantong.service.YuantongService;

/**
 * 圆通接口Controller
 * 
 * @author lilin
 * @version [版本号, 2015年9月6日]
 */
@RequestMapping("logistics/yuantong")
@Controller
public class YuantongController {
    /**
     * 圆通接口
     */
    @Resource
    private YuantongService yuantongService;
    
    /**
     * 下订单
     *
     * @param orderRequest 请求信息
     * @return 封装后的圆通返回信息
     */
    @RequestMapping("placeOrder")
    @ResponseBody
    public JsonData placeOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = yuantongService.placeOrder(orderRequest);
        JsonData jsonData = new JsonData();
        jsonData.setData(orderResponse);
        if ("false".equals(orderResponse.getSuccess())) {
            jsonData.setSuccess(false);
            jsonData.setMsg(orderResponse.getReason());
        }
        return jsonData;
    }
}
