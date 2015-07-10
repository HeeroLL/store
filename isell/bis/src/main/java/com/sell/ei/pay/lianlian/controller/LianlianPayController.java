package com.sell.ei.pay.lianlian.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sell.ei.pay.lianlian.util.LianlianPayInfo;

@Controller
@RequestMapping("pay/lianlian")
public class LianlianPayController {
    
    /**
     * 手机端连连支付
     *
     * @param lianlianPayInfo 连连支付信息
     * @throws IOException IOException
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
     * @throws IOException IOException
     */
    @RequestMapping("webPay")
    public String webPay(@RequestBody LianlianPayInfo lianlianPayInfo, ModelMap map) {
        map.put("inputparams", lianlianPayInfo.getParamInputs());
        return "pay/lianlian/webPay";
    }
}
