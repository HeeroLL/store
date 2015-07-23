package com.sell.ei.pay.weixin.controller;

import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sell.core.web.JsonData;
import com.sell.ei.pay.weixin.bean.PayResultInfo;
import com.sell.ei.pay.weixin.service.WeixinPayService;

/**
 * 微信支付控制层
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
@Controller
@RequestMapping("pay/weixin")
public class WeixinPayController {
    
    /**
     * 微信支付服务接口
     */
    @Resource
    private WeixinPayService weixinPayService;
    
    /**
     * 统一下单接口
     * 
     * @param paramMap map参数
     * @return 返回值
     */
    @ResponseBody
    @RequestMapping("unifiedorder")
    public JsonData unifiedorder(@RequestBody TreeMap<String, Object> paramMap) {
        JsonData jsonData = new JsonData();
        jsonData.setData(weixinPayService.unifiedorder(paramMap));
        return jsonData;
    }
    
    /**
     * 查询订单接口
     * 
     * @param paramMap map参数
     * @return 返回值
     */
    @ResponseBody
    @RequestMapping("orderquery")
    public JsonData orderquery(@RequestBody TreeMap<String, Object> paramMap) {
        JsonData jsonData = new JsonData();
        jsonData.setData(weixinPayService.orderquery(paramMap));
        return jsonData;
    }
    
    /**
     * 关闭订单接口
     * 
     * @param paramMap map参数
     * @return 返回值
     */
    @ResponseBody
    @RequestMapping("closeorder")
    public JsonData closeorder(@RequestBody TreeMap<String, Object> paramMap) {
        JsonData jsonData = new JsonData();
        jsonData.setData(weixinPayService.closeorder(paramMap));
        return jsonData;
    }
    
    /**
     * 支付结果通知接口<br>
     * 该接口是通过【统一下单API】中提交的参数notify_url设置，如果链接无法访问，商户将无法接收到微信通知。<br>
     * 需要做权限认证
     * 
     * @param payResultInfo 请求参数
     * @return 返回Map
     */
    @ResponseBody
    @RequestMapping("sendPayResult")
    public PayResultInfo sendPayResult(@RequestBody PayResultInfo payResultInfo) {
        return weixinPayService.sendPayResult(payResultInfo);
    }
}
