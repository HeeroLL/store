package com.zebone.dnode.engine.preprocess.vo;

/**
 * 首页近期随访信息对象
 *
 * @author 杨英
 * @version 2013-12-16 下午03:22
 */
public class FollowUpInfo {
    //随访日期
    private String fuVisitDate;

    //机构
    private String serviceOrg;

    //随访医生
    private String fuVisitDoctor;

    public String getFuVisitDate() {
        return fuVisitDate;
    }

    public void setFuVisitDate(String fuVisitDate) {
        this.fuVisitDate = fuVisitDate;
    }

    public String getServiceOrg() {
        return serviceOrg;
    }

    public void setServiceOrg(String serviceOrg) {
        this.serviceOrg = serviceOrg;
    }

    public String getFuVisitDoctor() {
        return fuVisitDoctor;
    }

    public void setFuVisitDoctor(String fuVisitDoctor) {
        this.fuVisitDoctor = fuVisitDoctor;
    }
}
