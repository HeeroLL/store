package com.isell.ps.pay.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JaxbUtil;
import com.isell.ei.pay.ehking.bean.EhkingCustomsAsync;
import com.isell.ei.pay.ehking.bean.EhkingPayNotify;
import com.isell.ei.pay.weixin.bean.WeixinPayResultInfo;
import com.isell.ei.pay.yijifu.bean.YijifuPayResponse;
import com.isell.ei.pay.yijifu.service.YijifuService;
import com.isell.ei.pay.yijifu.util.YijifuUtil;
import com.isell.ei.sms.service.SmsService;
import com.isell.ei.sms.vo.TemplateSMS;
import com.isell.ps.pay.vo.AlipayNotifyInfo;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.shop.dao.CoonShopMapper;
import com.isell.service.shop.vo.CoonShop;

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
     * 店铺信息
     */
    
    @Resource
    private CoonShopMapper coonShopMapper;
    
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
            order.setArrears(0); // 不欠费
            order.setZffs(CoolOrder.ZFFS_2);
            order.setState(CoolOrder.ORDER_STATE_1);
            order.setPayState((byte)0); // 未报关
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
            && (order.getState() == 0 || order.getState() == 1 || order.getState() == 99)) {
            order.setTradeNo(payInfo.getTradeNo());
            order.setArrears(0); // 不欠费
            order.setState(CoolOrder.ORDER_STATE_1);
            order.setZffs(CoolOrder.ZFFS_4);
            order.setPayState((byte)0); // 未报关
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
            order.setArrears(0); // 不欠费
            order.setZffs(CoolOrder.ZFFS_5);
            order.setState(CoolOrder.ORDER_STATE_1);
            order.setPayState((byte)0); // 未报关
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
    public String ehkingNotifyBg(@RequestBody EhkingCustomsAsync ehkingCustomsAsync, ModelMap map,
        HttpServletResponse response) {
        response.setContentType("text/plain; charset=UTF-8");
        map.put("result", "success");
        return "result";
    }
    
    private void sendMessage(CoolOrder orderR) {
        CoonShop coonShop = coonShopMapper.getCoonShopById(orderR.getSupplier());
        
        if (coonShop.getSmsEd() == 0) {
            TemplateSMS templateSMS = new TemplateSMS();
            templateSMS.setTemplateId("15127");
            templateSMS.setTo(orderR.getMobile());
            templateSMS.setParam(orderR.getOrderNo());
        }
        
        /*
         * if (!"56510b6f384c4f8e881ee1614913a3ef".equals(orderR.getSupplier()) &&
         * !"b4124c7271094479ae2c20cce611b5f4".equals(orderR.getSupplier())&&
         * !"dc27cddab07d4d5da74d38d9a9fe77e0".equals(orderR.getSupplier())) {// 可爱淘、KK馆不需要发货短信 TemplateSMS templateSMS
         * = new TemplateSMS(); templateSMS.setTemplateId("15127"); templateSMS.setTo(orderR.getMobile());
         * templateSMS.setParam(orderR.getOrderNo()); // smsService.sendMessage(templateSMS); }
         */
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
    
    @RequestMapping("/wxpayNotify")
    @ResponseBody
    public String wxpayNotify(@RequestBody Map<String, Object> param) {
        BufferedReader reader = (BufferedReader)param.get("reader");
        String orderId = (String)param.get("orderId");
        StringBuffer buffer = new StringBuffer();
        String str;
        String result = null;
        try {
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            WeixinPayResultInfo weixinPayResultInfo =
                JaxbUtil.converyToJavaBean(buffer.toString(), WeixinPayResultInfo.class);
            if ("SUCCESS".equals(weixinPayResultInfo.getReturnCode())
                && "SUCCESS".equals(weixinPayResultInfo.getResultCode())) {
                CoolOrder coolOrder = orderService.getCoolOrderById(Integer.valueOf(orderId));
                
                if ("0".equals(coolOrder.getState().toString()) || "99".equals(coolOrder.getState().toString())) {
                    coolOrder.setTradeNo(weixinPayResultInfo.getTransactionId());
                    coolOrder.setState(new Byte("1"));
                    coolOrder.setZffs(3);
                    coolOrder.setPayTime(new Date());
                    coolOrder.setArrears(0);
                    coolOrder.setPayState((byte)0); // 未报关
                    orderService.updateOrder(coolOrder);
                    sendMessage(coolOrder);
                    result = "<xml><return_code>SUCCESS</return_code><return_msg>OK</return_msg></xml>";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result;
        
    }
}
