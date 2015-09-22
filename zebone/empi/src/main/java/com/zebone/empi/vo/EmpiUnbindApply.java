package com.zebone.empi.vo;

import java.util.Date;

/**
 * 主索引解绑申请对象
 *
 * @author 杨英
 * @version 2014-8-5 上午10:07
 */
public class EmpiUnbindApply {
    private String id;
    //主索引号
    private String empi;
    //姓名
    private String name;
    //性别
    private String sex;
    //民族
    private String nation;
    //出生日期
    private Date birthday;
    //户籍地址
    private String permanentAddress;
    //照片信息
    private byte[] photo;
    //申请时间
    private Date applyTime;
    //申请机构
    private String orgCode;
    //审核状态
    private String auditStatus;
    //审核说明
    private String auditDesc;
    //需要绑定的新的一级标识类型
    private String firstCardType;
    //一级标识号
    private String firstCardNo;
    //需要解绑的二级标识类型
    private String secCardType;
    //二级标识号
    private String secCardNo;
    //二级标识发卡机构
    private String secCardOrg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpi() {
        return empi;
    }

    public void setEmpi(String empi) {
        this.empi = empi;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc;
    }

    public String getFirstCardType() {
        return firstCardType;
    }

    public void setFirstCardType(String firstCardType) {
        this.firstCardType = firstCardType;
    }

    public String getFirstCardNo() {
        return firstCardNo;
    }

    public void setFirstCardNo(String firstCardNo) {
        this.firstCardNo = firstCardNo;
    }

    public String getSecCardType() {
        return secCardType;
    }

    public void setSecCardType(String secCardType) {
        this.secCardType = secCardType;
    }

    public String getSecCardNo() {
        return secCardNo;
    }

    public void setSecCardNo(String secCardNo) {
        this.secCardNo = secCardNo;
    }

    public String getSecCardOrg() {
        return secCardOrg;
    }

    public void setSecCardOrg(String secCardOrg) {
        this.secCardOrg = secCardOrg;
    }
}
