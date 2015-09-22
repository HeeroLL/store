package com.zebone.dip.empi.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 主索引实名信息更新审核参数对象
 *
 * @author 杨英
 * @version 2014-8-7 下午1:18
 */
@XStreamAlias("request")
public class UpdateAuditRequest {
    //审核结果  1 允许更新  2 拒绝更新
    private String auditResult;
    //审核说明
    private String auditDesc;
    //姓名
    private String name;
    //性别
    private String sex;
    //民族
    private String nation;
    //出生日期
    @XStreamAlias("birthday")
    private String strBirthday;
    //户籍地址
    @XStreamAlias("permanent_address")
    private String permanentAddress;
    //身份证号码
    private String cardNo;
    //照片信息
    @XStreamAlias("photo")
    private String strPhoto;
    //申请机构
    private String orgCode;

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getStrBirthday() {
        return strBirthday;
    }

    public void setStrBirthday(String strBirthday) {
        this.strBirthday = strBirthday;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getStrPhoto() {
        return strPhoto;
    }

    public void setStrPhoto(String strPhoto) {
        this.strPhoto = strPhoto;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
