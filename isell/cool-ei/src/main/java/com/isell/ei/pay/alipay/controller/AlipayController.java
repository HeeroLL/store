package com.isell.ei.pay.alipay.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isell.ei.pay.alipay.service.AlipayService;

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
     * @return 返回的参数字符串
     */
    @RequestMapping("wapPay")
    public String wapPay(@RequestBody Map<String, String> paramMap, ModelMap map) {
        paramMap.put("service", "alipay.wap.create.direct.pay.by.user"); // 手机支付接口名
        map.putAll(paramMap);
        map.addAttribute("result", alipayService.getPayParams(paramMap));
        return "result";
    }
    
    /**
     * PC端支付宝支付
     * 
     * @param paramMap 参数信息
     * @param map 返回值
     * @return 返回的参数字符串
     */
    @RequestMapping("webPay")
    public String webPay(@RequestBody Map<String, String> paramMap, ModelMap map) {
        paramMap.put("service", "create_direct_pay_by_user"); // PC支付接口名
        map.putAll(paramMap);
        map.addAttribute("result", alipayService.getPayParams(paramMap));
        return "result";
    }
    
    /**
     * PC端批量付款到支付宝账户
     * 
     * @param paramMap 参数信息
     * @param map 返回值
     * @return 返回的参数字符串
     */
    @RequestMapping("batchTrans")
    public String batchTrans(@RequestBody Map<String, String> paramMap, ModelMap map) {
        paramMap.put("service", "batch_trans_notify"); // PC批量付款接口名
        map.putAll(paramMap);
        map.addAttribute("result", alipayService.getBatchTransParams(paramMap));
        return "result";
    }
}
