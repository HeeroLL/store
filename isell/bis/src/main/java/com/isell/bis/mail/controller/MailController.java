package com.isell.bis.mail.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.bis.mail.service.SimpleMailService;
import com.isell.bis.mail.vo.MailSenderInfo;
import com.isell.core.web.JsonData;

/**
 * 邮件服务controller
 * 
 * @author lilin
 * @version [版本号, 2015年7月9日]
 */
@Controller
@RequestMapping("mail")
public class MailController {
    /**
     * 邮件服务
     */
    @Resource
    private SimpleMailService simpleMailService;
    
    /**
     * 发送文本格式邮件
     * 
     * @param mailInfo 邮件信息
     * @return 封装后的处理结果
     */
    @RequestMapping("sendTextMail")
    @ResponseBody
    public JsonData sendTextMail(@RequestBody MailSenderInfo mailInfo) {
        JsonData jsonData = new JsonData();
        jsonData.setData(simpleMailService.sendTextMail(mailInfo));
        return jsonData;
    }
    
    /**
     * 发送HTML格式邮件
     * 
     * @param mailInfo 邮件信息
     * @return 封装后的处理结果
     */
    @RequestMapping("sendHtmlMail")
    @ResponseBody
    public JsonData sendHtmlMail(@RequestBody MailSenderInfo mailInfo) {
        JsonData jsonData = new JsonData();
        jsonData.setData(simpleMailService.sendHtmlMail(mailInfo));
        return jsonData;
    }
}
