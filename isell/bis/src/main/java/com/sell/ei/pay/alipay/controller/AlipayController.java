package com.sell.ei.pay.alipay.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sell.ei.pay.alipay.service.AlipayService;

/**
 * 支付宝支付控制层
 * 
 * @author lilin
 * @version [版本号, 2015年7月26日]
 */
@Controller
@RequestMapping("pay/alipay")
public class AlipayController {
    /**
     * 支付宝支付service层接口
     */
    @Resource
    private AlipayService alipayService;
    
    /**
     * 手机端支付宝支付
     * 
     * @param paramMap 参数信息
     * @param map 返回值
     */
    @RequestMapping("wapPay")
    public String wapPay(@RequestBody Map<String, String> paramMap, ModelMap map) {
        paramMap.put("service", "alipay.wap.create.direct.pay.by.user"); // 手机支付接口名
        map.putAll(paramMap);
        map.addAttribute("result", alipayService.getParamInputs(paramMap));
        return "pay/alipay/pay";
    }
    
    /**
     * PC端支付宝支付
     * 
     * @param paramMap 参数信息
     * @param map 返回值
     */
    @RequestMapping("webPay")
    public String webPay(@RequestBody Map<String, String> paramMap, ModelMap map) {
        paramMap.put("service", "create_direct_pay_by_user"); // PC支付接口名
        map.putAll(paramMap);
        map.addAttribute("result", alipayService.getParamInputs(paramMap));
        return "pay/alipay/pay";
    }
}
