package com.isell.bis.mail.service;

import com.isell.bis.mail.vo.MailSenderInfo;

/**
 * 邮件服务
 * 
 * @author lilin
 * @version [版本号, 2015年7月9日]
 */
public interface SimpleMailService {
    /**
     * 以文本格式发送邮件
     * 
     * @param mailInfo 待发送的邮件的信息
     */
    boolean sendTextMail(MailSenderInfo mailInfo);
    
    /**
     * 以HTML格式发送邮件
     * 
     * @param mailInfo 待发送的邮件信息
     */
    boolean sendHtmlMail(MailSenderInfo mailInfo);
}
