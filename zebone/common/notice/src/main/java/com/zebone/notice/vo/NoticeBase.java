package com.zebone.notice.vo;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NoticeBase {
    /**
     * 公告、通知ID
     */
    private String nId;
    
    /**
     * 发布机构
     */
    private String publishOrg;
    
    /**
     * 发布人ID
     */
    private String publishPersonId;
    
    /**
     * 发布人名称
     */
    private String publishPersonName;
    
    /**
     * 发布对象
     */
    private String publishObject;
    
    /**
     * 公告、通知主题
     */
    private String noticeTitle;
    
    /**
     * 公告、通知类型
     */
    private String noticeType;
    
    /**
     * 公告、通知内容
     */
    private String noticeContent;
    
    /**
     * 发布时间
     */
    private Date publishTime;
    
    /**
     * 有效时长（1：一个月/3：三个月/6：半年/12：一年）
     */
    private Integer effectiveTime;
    
    /**
     * 失效日期
     */
    private String expiryDate;
    
    /**
     * 状态（0：未发布/1：已发布）
     */
    private String status;
    
    /**
     * 删除标识（0：未删除/1：已删除）
     */
    private String delFlag;
    
    /**
     * 重写toString方法
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
    public String getnId() {
        return nId;
    }
    
    public void setnId(String nId) {
        this.nId = nId;
    }
    
    public String getNId() {
        return nId;
    }
    
    public void setNId(String nId) {
        this.nId = nId;
    }
    
    public String getPublishOrg() {
        return publishOrg;
    }
    
    public void setPublishOrg(String publishOrg) {
        this.publishOrg = publishOrg;
    }
    
    public String getPublishPersonId() {
        return publishPersonId;
    }
    
    public void setPublishPersonId(String publishPersonId) {
        this.publishPersonId = publishPersonId;
    }
    
    public String getPublishPersonName() {
        return publishPersonName;
    }
    
    public void setPublishPersonName(String publishPersonName) {
        this.publishPersonName = publishPersonName;
    }
    
    public String getPublishObject() {
        return publishObject;
    }
    
    public void setPublishObject(String publishObject) {
        this.publishObject = publishObject;
    }
    
    public String getNoticeTitle() {
        return noticeTitle;
    }
    
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }
    
    public String getNoticeType() {
        return noticeType;
    }
    
    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }
    
    public String getNoticeContent() {
        return noticeContent;
    }
    
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }
    
    public Date getPublishTime() {
        return publishTime;
    }
    
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    
    public Integer getEffectiveTime() {
        return effectiveTime;
    }
    
    public void setEffectiveTime(Integer effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
    
    public String getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getDelFlag() {
        return delFlag;
    }
    
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
