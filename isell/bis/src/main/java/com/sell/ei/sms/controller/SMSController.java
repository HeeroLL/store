package com.sell.ei.sms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sell.core.web.JsonData;
import com.sell.ei.sms.service.SmsService;
import com.sell.ei.sms.vo.TemplateSMS;

/**
 * 云之讯开放平台接口Controller
 * 
 * @author lilin
 * @version [版本号, 2015年7月6日]
 */
@Controller
@RequestMapping("sms")
public class SMSController {
    
    /**
     * 短信平台接口
     */
    @Resource
    private SmsService smsService;
    
    /**
     * 路由查询接口
     * 
     * @param templateSMS 短信信息
     * @return 封装后的处理结果
     */
    @RequestMapping("sendMessage")
    @ResponseBody
    public JsonData sendMessage(@RequestBody TemplateSMS templateSMS) {
        JsonData jsonData = new JsonData();
        jsonData.setData(smsService.sendMessage(templateSMS));
        return jsonData;
    }
}
