package com.sell.bis.mail.controller;

import org.junit.Test;

import com.sell.bis.mail.vo.MailSenderInfo;
import com.sell.core.util.HttpUtils;
import com.sell.core.util.JsonUtil;

public class MailControllerTest {
    
    @Test
    public void testSendTextMail() {
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.i-sell.cn");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("admin@i-sell.cn");
        mailInfo.setPassword("Abc12345");// 您的邮箱密码
        mailInfo.setFromAddress("admin@i-sell.cn");
        mailInfo.setToAddress("114046323@qq.com");
        mailInfo.setSubject("小酷儿商城商家注册");
        StringBuffer str = new StringBuffer();
        str.append("<p>亲爱的 lilin, </p>").append("<p>你好！</p>");
        mailInfo.setContent(str.toString());
        
        String result = HttpUtils.httpPost("http://localhost:8080/bis/mail/sendTextMail", JsonUtil.writeValueAsString(mailInfo));
        System.out.println("result=" + result);
    }
    
    @Test
    public void testSendHtmlMail() {
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.i-sell.cn");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("admin@i-sell.cn");
        mailInfo.setPassword("Abc12345");// 您的邮箱密码
        mailInfo.setFromAddress("admin@i-sell.cn");
        mailInfo.setToAddress("114046323@qq.com");
        mailInfo.setSubject("小酷儿商城商家注册");
        StringBuffer str = new StringBuffer();
        str.append("<p>亲爱的 lilin, </p>").append("<p>你好！</p>");
        mailInfo.setContent(str.toString());
        
        String result = HttpUtils.httpPost("http://localhost:8080/bis/mail/sendHtmlMail", JsonUtil.writeValueAsString(mailInfo));
        System.out.println("result=" + result);
    }
    
}
