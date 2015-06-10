package com.zebone.dnode.engine.validation.domain;

import org.apache.ibatis.type.Alias;


/**
 * 校验日志明细
 * @author 陈阵 
 * @date 2013-7-30 上午11:09:29
 */
@Alias("checkLog")
public class CheckLog {
    
	/** 主键 **/
    private String id;

    /** 对应的校验主表id **/
    private String mainId;

    /** 校验开始时间 **/
    private String startTime;

    /** 校验结束时间 **/
    private String endTime;

    /** 文档标签XPATH **/
    private String docXpath;

    /** 错误类型（可选，重复，数据格式，业务格式，唯一性错误，值) **/
    private String errorType;

    /** 错误子类型（邮件，手机，等） **/
    private String errorSubType;

    /** 错误描述 **/
    private String errorDesc;

    /** 异常值  **/
    private String errorException;


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public String getMainId() {
        return mainId;
    }


    public void setMainId(String mainId) {
        this.mainId = mainId == null ? null : mainId.trim();
    }


    public String getStartTime() {
        return startTime;
    }


    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }


    public String getEndTime() {
        return endTime;
    }


    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }


    public String getDocXpath() {
        return docXpath;
    }


    public void setDocXpath(String docXpath) {
        this.docXpath = docXpath == null ? null : docXpath.trim();
    }


    public String getErrorType() {
        return errorType;
    }


    public void setErrorType(String errorType) {
        this.errorType = errorType == null ? null : errorType.trim();
    }


    public String getErrorSubType() {
        return errorSubType;
    }


    public void setErrorSubType(String errorSubType) {
        this.errorSubType = errorSubType == null ? null : errorSubType.trim();
    }


    public String getErrorDesc() {
        return errorDesc;
    }


    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc == null ? null : errorDesc.trim();
    }


    public String getErrorException() {
        return errorException;
    }


    public void setErrorException(String errorException) {
        this.errorException = errorException == null ? null : errorException.trim();
    }
}