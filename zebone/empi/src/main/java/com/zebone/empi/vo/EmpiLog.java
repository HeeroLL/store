package com.zebone.empi.vo;

import java.sql.Timestamp;
import java.util.Date;

public class EmpiLog {
    
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
    
    /**
     * 查询开始时间
     */
    private String startSearchTime;
    
    /**
     * 查询结束时间
     */
    private String endSearchTime;
    
	public String getStartSearchTime() {
		return startSearchTime;
	}

	public void setStartSearchTime(String startSearchTime) {
		this.startSearchTime = startSearchTime;
	}

	public String getEndSearchTime() {
		return endSearchTime;
	}

	public void setEndSearchTime(String endSearchTime) {
		this.endSearchTime = endSearchTime;
	}

	/**
     * 
     * @return
     */
    public String getInfo_XMLDoc() {
		return info_XMLDoc;
	}

	public void setInfo_XMLDoc(String info_XMLDoc) {
		this.info_XMLDoc = info_XMLDoc;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSource() {
        return source;
    }

    
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getEventNameCode() {
        return eventNameCode;
    }

    public void setEventNameCode(String eventNameCode) {
        this.eventNameCode = eventNameCode == null ? null : eventNameCode.trim();
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode == null ? null : resultCode.trim();
    }

    public String getErrorCategory() {
        return errorCategory;
    }

    public void setErrorCategory(String errorCategory) {
        this.errorCategory = errorCategory == null ? null : errorCategory.trim();
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType == null ? null : errorType.trim();
    }

    public String getErrorPosition() {
        return errorPosition;
    }

    public void setErrorPosition(String errorPosition) {
        this.errorPosition = errorPosition == null ? null : errorPosition.trim();
    }

    public String getEmpi() {
        return empi;
    }

    public void setEmpi(String empi) {
        this.empi = empi == null ? null : empi.trim();
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
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName == null ? null : residentName.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }
}