package com.zebone.dnode.engine.preprocess.vo;

/**
 * 首页近期检查对象
 *
 * @author 杨英
 * @version 2013-12-12 下午1:32
 */
public class ExamInfo {
    //检查日期
    private String rptTime;
    //检查名称
    private String examName;
    //检查结果
    private String imageFinding;
    //服务机构
    private String serviceOrg;

    public String getRptTime() {
        return rptTime;
    }

    public void setRptTime(String rptTime) {
        this.rptTime = rptTime;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getImageFinding() {
        return imageFinding;
    }

    public void setImageFinding(String imageFinding) {
        this.imageFinding = imageFinding;
    }

    public String getServiceOrg() {
        return serviceOrg;
    }

    public void setServiceOrg(String serviceOrg) {
        this.serviceOrg = serviceOrg;
    }
}
