package com.isell.ps.pay.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isell.ei.pay.ehking.bean.EhkingCustomsAsync;
import com.isell.ei.pay.ehking.bean.EhkingPayNotify;
import com.isell.ei.pay.yijifu.bean.YijifuPayResponse;
import com.isell.ei.pay.yijifu.service.YijifuService;
import com.isell.ei.pay.yijifu.util.YijifuUtil;
import com.isell.ei.sms.service.SmsService;
import com.isell.ei.sms.vo.TemplateSMS;
import com.isell.ps.pay.vo.AlipayNotifyInfo;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;

/**
 * 支付异步通知Controller
 * 
 * @author lilin
 * @version [版本号, 2015年10月17日]
 */
@RequestMapping("payNotify")
@Controller
public class PayNotifyController {
    /**
     * 订单服务
     */
    @Resource
    private OrderService orderService;
    
    /**
     * 短信平台接口
     */
    @Resource
    private SmsService smsService;
    
    /**
     * 支付宝支付异步通知
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
            
            sendMessage(order);
        }
        map.put("result", "success");
        return "result";
    }
    
    /**
     * 易极付支付异步通知
     * 
     * @param payInfo 易极付异步参数
     * @param map 返回参数
     * @param response response
     * @return String
     */
    @RequestMapping("/yijifu")
    public String yijifuNotify(YijifuPayResponse payInfo, ModelMap map, HttpServletResponse response) {
        String sign = YijifuUtil.encryptString(YijifuUtil.getParameter(payInfo.toMap()), YijifuService.PAY_KEY);
        // 验证消息准确性
        if (sign.equals(payInfo.getSign())) {
            map.put("result", "fail");
            return "result";
        }
        
        response.setContentType("text/plain; charset=UTF-8");
        CoolOrder order = orderService.getCoolOrderByOrderNo(payInfo.getOutOrderNo());
        
        if (order == null) {
            throw new RuntimeException("exception.order.null");
        }
        if ("true".equals(payInfo.getSuccess()) && "EXECUTE_SUCCESS".equals(payInfo.getResultCode())
            && (order.getState() == 0 || order.getState() == 99)) {
            order.setTradeNo(payInfo.getTradeNo());
            order.setState(CoolOrder.ORDER_STATE_1);
            order.setPayTime(new Date());
            orderService.updateOrder(order);
            
            sendMessage(order);
        }
        map.put("result", "success");
        return "result";
    }
    
    /**
     * 易汇金支付异步通知
     * 
     * @param ehkingPayNotify 易汇金异步参数
     * @param map 返回参数
     * @param response response
     * @return String
     */
    @RequestMapping("/ehking")
    public String ehkingNotify(@RequestBody EhkingPayNotify ehkingPayNotify, ModelMap map, HttpServletResponse response) {
        response.setContentType("text/plain; charset=UTF-8");
        CoolOrder order = orderService.getCoolOrderByOrderNo(ehkingPayNotify.getRequestId());
        
        if (order == null) {
            throw new RuntimeException("exception.order.null");
        }
        if ("SUCCESS".equals(ehkingPayNotify.getStatus()) && (order.getState() == 0 || order.getState() == 99)) {
            order.setTradeNo(ehkingPayNotify.getSerialNumber());
            order.setState(CoolOrder.ORDER_STATE_1);
            order.setPayTime(new Date());
            orderService.updateOrder(order);
            
            sendMessage(order);
        }
        map.put("result", "success");
        return "result";
    }
    
    /**
     * 易汇金报关异步通知
     * 
     * @param ehkingCustomsAsync 易汇金异步报关响应参数
     * @param map 返回参数
     * @param response response
     * @return String
     */
    @RequestMapping("/ehkingBg")
    public String ehkingNotifyBg(@RequestBody EhkingCustomsAsync ehkingCustomsAsync, ModelMap map, HttpServletResponse response) {
        response.setContentType("text/plain; charset=UTF-8");
        System.out.println(ehkingCustomsAsync.getCustomsInfos());
        map.put("result", "success");
        return "result";
    }
    
    private void sendMessage(CoolOrder orderR) {
        if (!"56510b6f384c4f8e881ee1614913a3ef".equals(orderR.getSupplier())
            && !"b4124c7271094479ae2c20cce611b5f4".equals(orderR.getSupplier())) {// 可爱淘、KK馆不需要发货短信
            TemplateSMS templateSMS = new TemplateSMS();
            templateSMS.setTemplateId("15127");
            templateSMS.setTo(orderR.getMobile());
            templateSMS.setParam(orderR.getOrderNo());
            // smsService.sendMessage(templateSMS);
        }
    }
    
    /**
     * 易极付支付报关异步通知
     * 
     * @param payInfo 易极付异步参数
     * @param map 返回参数
     * @param response response
     * @return String
     */
    @RequestMapping("/yijifuBg")
    public String yijifuBg(ModelMap map, HttpServletResponse response) {
        response.setContentType("text/plain; charset=UTF-8");
        map.put("result", "success");
        return "result";
    }
}
