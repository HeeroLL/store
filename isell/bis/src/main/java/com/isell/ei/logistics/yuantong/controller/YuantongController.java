package com.isell.ei.logistics.yuantong.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @RequestMapping("placeOrder")
    @ResponseBody
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        return yuantongService.placeOrder(orderRequest);
    }
}
