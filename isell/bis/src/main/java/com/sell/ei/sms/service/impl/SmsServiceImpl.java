package com.sell.ei.sms.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sell.core.util.Coder;
import com.sell.core.util.DateUtil;
import com.sell.core.util.HttpUtils;
import com.sell.core.util.JsonUtil;
import com.sell.ei.sms.service.SmsService;
import com.sell.ei.sms.vo.SMSResponse;
import com.sell.ei.sms.vo.TemplateSMS;

/**
 * 短信平台接口封装业务层实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月6日]
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {
    
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(SmsServiceImpl.class);
    
    /** ip */
    private static final String IP = "api.ucpaas.com";
    
    /** version */
    private static final String VERSION = "2014-06-30";
    
    private static final String accountSid = "20857dfa396b243bf35d8a49845110e3";
                                    
    private static final String authToken = "70973baed3589527c080758b5d44d312";
    
    private static final String appId = "668cd249996a4554bf8ed61e309fb3e3";
    
    private static final String templateId = "3132";
    
    /** BASEURL */
    private static final String BASEURL;
    
    static {
        StringBuilder builder = new StringBuilder();
        builder.append("https://")
            .append(IP)
            .append("/")
            .append(VERSION)
            .append("/Accounts/")
            .append(accountSid)
            .append("/Messages/templateSMS");
        BASEURL = builder.toString();
    }
    
    @Override
    public SMSResponse sendMessage(TemplateSMS templateSMS) {
        // 构造请求URL内容
        String timestamp = DateUtil.getCurrentDate("yyyyMMddHHmmss");// 时间戳
        String signature = DigestUtils.md5Hex(accountSid + authToken + timestamp);
        String url = BASEURL + "?sig=" + signature.toUpperCase();
        
        templateSMS.setAppId(appId);
        templateSMS.setTemplateId(templateId);
        String body = JsonUtil.writeValueAsString(templateSMS);
        body = "{\"templateSMS\":" + body + "}";
        
        // 生成请求头认证信息
        String src = accountSid + ":" + timestamp;
        String auth = Coder.encryptBASE64(src);
        log.info(url + "sendMessageTo:" + "\nparam:" + body);
        String result = HttpUtils.httpPost(url, body, "application/json", new BasicHeader("Authorization", auth));
        log.info(result);
        
        return JsonUtil.readValue(result, SMSResponse.class);
        
    }
}
