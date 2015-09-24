package com.isell.ei.sms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.sms.service.SmsService;
import com.isell.ei.sms.vo.TemplateSMS;

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
     * 发送短信
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
    
    /**
     * 发送短信(测试，由于发短信要钱，所以做个测试接口，直接返回成功)
     * 
     * @return 封装后的处理结果
     */
    @RequestMapping("sendTestMessage")
    @ResponseBody
    public JsonData sendTestMessage(@RequestBody TemplateSMS templateSMS) {
        return new JsonData();
    }
}
