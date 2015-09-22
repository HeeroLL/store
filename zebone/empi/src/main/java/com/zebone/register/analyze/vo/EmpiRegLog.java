package com.zebone.register.analyze.vo;

import java.util.Date;

/**
 *
 * @author 
 */
public class EmpiRegLog{
    /**
     * ID
     */
    private String id;
    /**
     * 业务系统编码
     */
    private String businessSysCode;
    /**
     * 上传时间
     */
    private Date inputTime;
    /**
     * 上传机构
     */
    private String inputOrgCode;
    /**
     * 上传用户
     */
    private String inputUserCode;
    /**
     * 注册文档
     */
    private String regDocument;

    /**
     * ID
     */
    public String getId(){
        return this.id;
    }

    /**
     * ID
     */
    public void setId(String id){
        this.id = id;
    }    
    /**
     * 业务系统编码
     */
    public String getBusinessSysCode(){
        return this.businessSysCode;
    }

    /**
     * 业务系统编码
     */
    public void setBusinessSysCode(String businessSysCode){
        this.businessSysCode = businessSysCode;
    }    
    /**
     * 上传时间
     */
    public Date getInputTime(){
        return this.inputTime;
    }

    /**
     * 上传时间
     */
    public void setInputTime(Date inputTime){
        this.inputTime = inputTime;
    }    
    /**
     * 上传机构
     */
    public String getInputOrgCode(){
        return this.inputOrgCode;
    }

    /**
     * 上传机构
     */
    public void setInputOrgCode(String inputOrgCode){
        this.inputOrgCode = inputOrgCode;
    }    
    /**
     * 上传用户
     */
    public String getInputUserCode(){
        return this.inputUserCode;
    }

    /**
     * 上传用户
     */
    public void setInputUserCode(String inputUserCode){
        this.inputUserCode = inputUserCode;
    }    
    /**
     * 注册文档
     */
    public String getRegDocument(){
        return this.regDocument;
    }

    /**
     * 注册文档
     */
    public void setRegDocument(String regDocument){
        this.regDocument = regDocument;
    }    
}