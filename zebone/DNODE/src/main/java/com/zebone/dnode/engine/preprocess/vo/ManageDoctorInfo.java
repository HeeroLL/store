package com.zebone.dnode.engine.preprocess.vo;

/**
 * 首页管理医生信息对象
 *
 * @author 杨英
 * @version 2013-12-20 上午09:12
 */
public class ManageDoctorInfo {
    //医生姓名
    private String doctorName;
    //联系电话
    private String contactTel;
    //所属机构
    private String org;
    //上次服务日期
    private String preServiceDate;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getPreServiceDate() {
        return preServiceDate;
    }

    public void setPreServiceDate(String preServiceDate) {
        this.preServiceDate = preServiceDate;
    }
}
