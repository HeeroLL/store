package com.sell.ei.pay.lianlian.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sell.ei.pay.lianlian.bean.LianlianPayInfo;
import com.sell.ei.pay.lianlian.bean.LianlianRefundInfo;
import com.sell.ei.pay.lianlian.service.LianlianPayService;

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
     * 连连支付业务层
     */
    @Resource
    private LianlianPayService lianlianPayService;
    
    /**
     * 手机端连连支付
     * 
     * @param lianlianPayInfo 连连支付信息
     * @param map 返回值
     */
    @RequestMapping("wapPay")
    public String wapPay(@RequestBody LianlianPayInfo lianlianPayInfo, ModelMap map) {
        map.put("inputparams", lianlianPayService.getPayParams(lianlianPayInfo));
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
        map.put("inputparams", lianlianPayService.getPayParams(lianlianPayInfo));
        return "pay/lianlian/webPay";
    }
    
    /**
     * 连连支付退款（原路返回）
     * 
     * @param lianlianRefundInfo 连连支付退款信息
     * @param map 返回值
     */
    @RequestMapping("refund")
    public String refund(@RequestBody LianlianRefundInfo lianlianRefundInfo, ModelMap map) {
        map.put("inputparams", lianlianPayService.getRefundParams(lianlianRefundInfo));
        return "pay/lianlian/refund";
    }
}
