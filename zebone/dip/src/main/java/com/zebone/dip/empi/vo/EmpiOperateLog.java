package com.zebone.dip.empi.vo;

import java.util.Date;

/**
 * 主索引操作日志对象
 *
 * @author 杨英
 * @version 2014-7-31 下午2:33
 */
public class EmpiOperateLog {
    private String id;

    /**
     * 事件源
     */
    private String source;

    /**
     * 时间目标
     */
    private String target;

    /**
     * 事件名称代码
     */
    private String eventNameCode;

    /**
     * 结果
     */
    private String resultCode;

    /**
     * 错误类型
     */
    private String errorCategory;

    /**
     * 错误分类
     */
    private String errorType;

    /**
     * 错误位置
     */
    private String errorPosition;

    /**
     * EMPI号
     */
    private String empi;

    /**
     * 发生时间
     */
    private Date eventTime;

    /**
     * 一级标识类型
     */
    private String cardType;

    /**
     * 一级标识编码
     */
    private String cardNo;

    /**
     * 患者姓名
     */
    private String residentName;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 文档体
     */
    private String info_XMLDoc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getEventNameCode() {
        return eventNameCode;
    }

    public void setEventNameCode(String eventNameCode) {
        this.eventNameCode = eventNameCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorCategory() {
        return errorCategory;
    }

    public void setErrorCategory(String errorCategory) {
        this.errorCategory = errorCategory;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorPosition() {
        return errorPosition;
    }

    public void setErrorPosition(String errorPosition) {
        this.errorPosition = errorPosition;
    }

    public String getEmpi() {
        return empi;
    }

    public void setEmpi(String empi) {
        this.empi = empi;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getInfo_XMLDoc() {
        return info_XMLDoc;
    }

    public void setInfo_XMLDoc(String info_XMLDoc) {
        this.info_XMLDoc = info_XMLDoc;
    }
}
