package com.zebone.dnode.engine.preprocess.vo;

/**
 * 首页近期检验对象
 *
 * @author 杨英
 * @version 2013-12-16 上午10:00
 */
public class InspectInfo {
    //检验日期
    private String inspectDate;
    //检验名称
    private String inspectName;
    //检验结果
    private String inspectResult;
    //服务机构
    private String serviceOrg;

    public String getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(String inspectDate) {
        this.inspectDate = inspectDate;
    }

    public String getInspectName() {
        return inspectName;
    }

    public void setInspectName(String inspectName) {
        this.inspectName = inspectName;
    }

    public String getInspectResult() {
        return inspectResult;
    }

    public void setInspectResult(String inspectResult) {
        this.inspectResult = inspectResult;
    }

    public String getServiceOrg() {
        return serviceOrg;
    }

    public void setServiceOrg(String serviceOrg) {
        this.serviceOrg = serviceOrg;
    }
}
