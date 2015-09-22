package com.zebone.notice.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 通知人信息vo
 * 
 * @author lilin
 * @version [版本号, 2015年5月8日]
 */
public class NoticePerson {
    /**
     * 通知ID
     */
    private String nId;
    
    /**
     * 收件人ID
     */
    private String recipientPersonId;
    
    /**
     * 重写toString方法
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
    /**
     * 收件人ID
     */
    public String getRecipientPersonId() {
        return this.recipientPersonId;
    }
    
    /**
     * 收件人ID
     */
    public void setRecipientPersonId(String recipientPersonId) {
        this.recipientPersonId = recipientPersonId;
    }
    
    public String getnId() {
        return nId;
    }
    
    public void setnId(String nId) {
        this.nId = nId;
    }
}