package com.isell.ei.pay.alipay.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.pay.alipay.bean.SendOrderResponse;
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
    
    /**
     * 手机端支付宝支付(最新，支持实名认证，海关通关必备)
     * 
     * @param paramMap 参数信息
     * @param map 返回值
     * @return 返回的参数字符串
     */
    @RequestMapping("wapPayNew")
    public String wapPayNew(@RequestBody Map<String, String> paramMap, ModelMap map) {
        paramMap.put("service", "alipay.wap.create.direct.pay.by.user"); // 手机支付接口名
        paramMap.put("rn_check", "T"); // 需要买家实名认证
        map.putAll(paramMap);
        map.addAttribute("result", alipayService.getPayParams(paramMap)); // 接口没改，多了个字段
        return "result";
    }
    
    /**
     * PC端支付宝支付(最新，支持实名认证，海关通关必备)
     * 
     * @param paramMap 参数信息
     * @param map 返回值
     * @return 返回的参数字符串
     */
    @RequestMapping("webPayNew")
    public String webPayNew(@RequestBody Map<String, String> paramMap, ModelMap map) {
        paramMap.put("service", "alipay.acquire.page.createandpay"); // PC支付接口名（新）
        paramMap.put("buyer_info", "{\"needBuyerRealnamed\":\"T\"}"); // 需要买家实名认证
        map.putAll(paramMap);
        map.addAttribute("result", alipayService.getPayParamsNew(paramMap));
        return "result";
    }
    
    /**
     * 支付宝报关
     * 
     * @param paramMap 参数信息
     * @return 返回封装后的支付宝信息
     */
    @RequestMapping("sendOrder")
    @ResponseBody
    public JsonData sendOrder(@RequestBody Map<String, String> paramMap) {
        paramMap.put("service", "alipay.acquire.customs"); // 支付报关接口
        JsonData jsonData = new JsonData();
        SendOrderResponse response = alipayService.sendOrder(paramMap);
        jsonData.setSuccess("T".equals(response.getSuccess())
            && "SUCCESS".equals(response.getResponse().getAlipay().getResultCode()));
        if (StringUtils.isNotEmpty(response.getError())) {
            jsonData.setMsg(response.getError());
        }
        if (StringUtils.isNotEmpty(response.getResponse().getAlipay().getDetailErrorDes())) {
            String msg = response.getResponse().getAlipay().getDetailErrorDes();
            if (StringUtils.isNotEmpty(jsonData.getMsg())) {
                msg = jsonData.getMsg() + "|" + msg;
            }
            jsonData.setMsg(msg);
        }
        jsonData.setData(response);
        return jsonData;
    }
}
