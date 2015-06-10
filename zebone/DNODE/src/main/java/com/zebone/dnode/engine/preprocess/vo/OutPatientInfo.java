package com.zebone.dnode.engine.preprocess.vo;

/**
 * 首页近期门诊对象
 *
 * @author 杨英
 * @version 2013-12-12 下午2:24
 */
public class OutPatientInfo {
    //门诊日期
    private String outPatientTime;
    //诊断
    private String diseaseDiag;
    //服务机构
    private String serviceOrg;
    //门诊科室
    private String clinicDept;

    public String getOutPatientTime() {
        return outPatientTime;
    }

    public void setOutPatientTime(String outPatientTime) {
        this.outPatientTime = outPatientTime;
    }

    public String getDiseaseDiag() {
        return diseaseDiag;
    }

    public void setDiseaseDiag(String diseaseDiag) {
        this.diseaseDiag = diseaseDiag;
    }

    public String getServiceOrg() {
        return serviceOrg;
    }

    public void setServiceOrg(String serviceOrg) {
        this.serviceOrg = serviceOrg;
    }

    public String getClinicDept() {
        return clinicDept;
    }

    public void setClinicDept(String clinicDept) {
        this.clinicDept = clinicDept;
    }
}
