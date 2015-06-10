package com.zebone.dnode.engine.preprocess.vo;

/**
 * 首页近期住院信息对象
 *
 * @author 杨英
 * @version 2013-12-16 下午01:48
 */
public class InHospitalInfo {
    //入院日期
    private String inHosTime;
    //入院诊断
    private String inHosDiag;
    //服务机构
    private String serviceOrg;
    //科室
    private String inHosDept;

    public String getInHosTime() {
        return inHosTime;
    }

    public void setInHosTime(String inHosTime) {
        this.inHosTime = inHosTime;
    }

    public String getInHosDiag() {
        return inHosDiag;
    }

    public void setInHosDiag(String inHosDiag) {
        this.inHosDiag = inHosDiag;
    }

    public String getServiceOrg() {
        return serviceOrg;
    }

    public void setServiceOrg(String serviceOrg) {
        this.serviceOrg = serviceOrg;
    }

    public String getInHosDept() {
        return inHosDept;
    }

    public void setInHosDept(String inHosDept) {
        this.inHosDept = inHosDept;
    }
}
