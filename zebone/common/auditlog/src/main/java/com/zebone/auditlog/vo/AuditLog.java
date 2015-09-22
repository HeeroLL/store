package com.zebone.auditlog.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 审计日志bean
 * 
 * @author lilin
 * @version [版本号, 2015年4月29日]
 */
public class AuditLog implements Serializable {
    /**
     * 缓存队列名
     */
    public static final String QUEUE_KEY = "audit.logqueue";
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -6457540846857801172L;
    
    /**
     * 主键ID
     */
    private String logId;
    
    /**
     * 操作源IP
     */
    private String sourceIp;
    
    /**
     * 操作机构ID
     */
    private String orgCode;
    
    /**
     * 操作机构名称
     */
    private String orgName;
    
    /**
     * 操作人员姓名
     */
    private String personName;
    
    /**
     * 操作人员账号
     */
    private String personAccount;
    
    /**
     * 操作对象
     */
    private String optObject;
    
    /**
     * 操作时间
     */
    private Date createTime;
    
    /**
     * 操作日期
     */
    private String createDate;
    
    /**
     * 事件类型ID
     */
    private String eventTypeId;
    
    /**
     * 事件类型(模块名称)
     */
    private String eventType;
    
    /**
     * 操作类型ID
     */
    private String optTypeId;
    
    /**
     * 操作类型(操作名称)
     */
    private String optType;
    
    /**
     * 结果
     */
    private String result;
    
    /**
     * 具体描述
     */
    private String description;
    
    /**
     * 开始日期
     */
    private String beginDate;
    
    /**
     * 结束日期
     */
    private String endDate;
    
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
     * 主键ID
     */
    public String getLogId() {
        return this.logId;
    }
    
    /**
     * 主键ID
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }
    
    /**
     * 操作源IP
     */
    public String getSourceIp() {
        return this.sourceIp;
    }
    
    /**
     * 操作源IP
     */
    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }
    
    /**
     * 操作机构ID
     */
    public String getOrgCode() {
        return this.orgCode;
    }
    
    /**
     * 操作机构ID
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    
    /**
     * 操作机构名称
     */
    public String getOrgName() {
        return this.orgName;
    }
    
    /**
     * 操作机构名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    /**
     * 操作人员姓名
     */
    public String getPersonName() {
        return this.personName;
    }
    
    /**
     * 操作人员姓名
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    
    /**
     * 操作人员账号
     */
    public String getPersonAccount() {
        return this.personAccount;
    }
    
    /**
     * 操作人员账号
     */
    public void setPersonAccount(String personAccount) {
        this.personAccount = personAccount;
    }
    
    /**
     * 操作对象
     */
    public String getOptObject() {
        return this.optObject;
    }
    
    /**
     * 操作对象
     */
    public void setOptObject(String optObject) {
        this.optObject = optObject;
    }
    
    /**
     * 操作时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }
    
    /**
     * 操作时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    /**
     * 操作日期
     */
    public String getCreateDate() {
        return this.createDate;
    }
    
    /**
     * 操作日期
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    /**
     * 事件类型ID
     */
    public String getEventTypeId() {
        return this.eventTypeId;
    }
    
    /**
     * 事件类型ID
     */
    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }
    
    /**
     * 事件类型(模块名称)
     */
    public String getEventType() {
        return this.eventType;
    }
    
    /**
     * 事件类型(模块名称)
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    /**
     * 操作类型ID
     */
    public String getOptTypeId() {
        return this.optTypeId;
    }
    
    /**
     * 操作类型ID
     */
    public void setOptTypeId(String optTypeId) {
        this.optTypeId = optTypeId;
    }
    
    /**
     * 操作类型(操作名称)
     */
    public String getOptType() {
        return this.optType;
    }
    
    /**
     * 操作类型(操作名称)
     */
    public void setOptType(String optType) {
        this.optType = optType;
    }
    
    /**
     * 结果
     */
    public String getResult() {
        return this.result;
    }
    
    /**
     * 结果
     */
    public void setResult(String result) {
        this.result = result;
    }
    
    /**
     * 具体描述
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * 具体描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}