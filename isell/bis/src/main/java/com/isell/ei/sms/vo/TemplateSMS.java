package com.isell.ei.sms.vo;

/**
 * 消息模板
 * 
 * @author lilin
 * @version [版本号, 2015年7月6日]
 */
public class TemplateSMS {
    /***** 请求部分 *****/
    /** 应用Id */
    private String appId;
    
    /** 模板Id */
    private String templateId;
    
    /** 短信接收端手机号码（国内短信不要加前缀，国际短信号码前须带相应的国家区号，如日本：0081） */
    private String to;
    
    /** 内容数据，用于替换模板中{数字}，若有多个替换内容，用英文逗号隔开即可 */
    private String param;
    
    /***** 响应部分 *****/
    /** 短信的创建时间 */
    private String createDate;
    
    /** 短信标识符。一个由32个字符组成的短信唯一标识符 */
    private String smsId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }
}
