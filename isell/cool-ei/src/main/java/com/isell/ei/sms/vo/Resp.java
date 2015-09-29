package com.isell.ei.sms.vo;

/**
 * Resp
 * 
 * @author lilin
 * @version [版本号, 2015年7月6日]
 */
public class Resp {
    /** 请求状态码，取值000000（成功） */
    private String respCode;
    
    /** 表示短信验证码发送失败的条数。注：批量发送时，才会返回该字段 */
    private String failure;
    
    /** 消息模板 */
    private TemplateSMS templateSMS;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public TemplateSMS getTemplateSMS() {
        return templateSMS;
    }

    public void setTemplateSMS(TemplateSMS templateSMS) {
        this.templateSMS = templateSMS;
    }
}
