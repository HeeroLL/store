package com.isell.ei.pay.ehking.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isell.core.util.JsonData;
import com.isell.ei.pay.ehking.bean.EhkingCustomsRequest;
import com.isell.ei.pay.ehking.bean.EhkingPayInfo;
import com.isell.ei.pay.ehking.service.EhkingService;

/**
 * 易汇金支付控制层
 * 
 * @author lilin
 * @version [版本号, 2015年11月30日]
 */
@Controller
@RequestMapping("pay/ehking")
public class EhkingController {
    /**
     * 易汇金支付service层接口
     */
    @Resource
    private EhkingService ehkingService;
    
    /**
     * PC端易汇金支付
     * 
     * @param paramMap 参数信息
     * @param map 返回值
     * @return 返回的参数字符串
     */
    @RequestMapping("webPay")
    public String webPay(@RequestBody EhkingPayInfo ehkingPayInfo, ModelMap map) {
        map.addAttribute("result", ehkingService.getPayUrl(ehkingPayInfo));
        return "result";
    }
    
    /**
     * 发送订单消息
     *
     * @param request 易汇金海关报关参数
     * @return 同步响应结果
     */
    @RequestMapping("sendOrder")
    public JsonData sendOrder(@RequestBody EhkingCustomsRequest request) {
        JsonData jsonData = new JsonData();
        jsonData.setSuccess("SUCCESS".equals(ehkingService.sendOrder(request)));
        return jsonData;
    }
}
