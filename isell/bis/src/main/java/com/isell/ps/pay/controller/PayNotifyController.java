package com.isell.ps.pay.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isell.ps.pay.vo.AlipayNotifyInfo;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;

@RequestMapping("payNotify")
@Controller
public class PayNotifyController {
    /**
     * 订单服务
     */
    @Resource
    private OrderService orderService;
    
    /**
     * 支付宝支付
     *
     * @param alipayNotify 支付宝异步参数
     * @param map 返回参数
     * @param response response
     * @return String
     */
    @RequestMapping("/alipay")
    public String alipayNotify(AlipayNotifyInfo alipayNotify, ModelMap map, HttpServletResponse response) {
        response.setContentType("text/plain; charset=UTF-8");
        CoolOrder order = orderService.getCoolOrderByOrderNo(alipayNotify.getOut_trade_no());
        
        if (order == null) {
            throw new RuntimeException("exception.order.null");
        }
        if (order.getState() == 0 || order.getState() == 99) {
            order.setTradeNo(alipayNotify.getTrade_no());
            order.setState(CoolOrder.ORDER_STATE_1);
            order.setPayTime(new Date());
            orderService.updateOrder(order);
        }
        map.put("result", "success");
        return "result";

    }
}
