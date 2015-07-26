package com.sell.ei.pay.lianlian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sell.ei.pay.lianlian.util.LianlianPayInfo;

/**
 * 连连支付控制层
 * 
 * @author lilin
 * @version [版本号, 2015年6月26日]
 */
@Controller
@RequestMapping("pay/lianlian")
public class LianlianPayController {
    
    /**
     * 手机端连连支付
     * 
     * @param lianlianPayInfo 连连支付信息
     * @param map 返回值
     */
    @RequestMapping("wapPay")
    public String wapPay(@RequestBody LianlianPayInfo lianlianPayInfo, ModelMap map) {
        map.put("inputparams", lianlianPayInfo.getParamInputs());
        return "pay/lianlian/wapPay";
    }
    
    /**
     * PC端连连支付
     * 
     * @param lianlianPayInfo 连连支付信息
     * @param map 返回值
     */
    @RequestMapping("webPay")
    public String webPay(@RequestBody LianlianPayInfo lianlianPayInfo, ModelMap map) {
        map.put("inputparams", lianlianPayInfo.getParamInputs());
        return "pay/lianlian/webPay";
    }
}
