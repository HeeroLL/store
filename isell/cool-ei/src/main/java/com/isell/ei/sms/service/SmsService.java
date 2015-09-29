package com.isell.ei.sms.service;

import com.isell.ei.sms.vo.SMSResponse;
import com.isell.ei.sms.vo.TemplateSMS;

/**
 * 短信平台接口封装业务层
 * 
 * @author lilin
 * @version [版本号, 2015年7月6日]
 */
public interface SmsService {
    /**
     * 发送短信消息
     *
     * @param templateSMS 短信模板类
     * @return 处理结果
     */
    SMSResponse sendMessage(TemplateSMS templateSMS);
}
