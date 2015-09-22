package com.zebone.dnode.engine.preprocess.vo;

/**
 * 首页高血压曲线对象
 *
 * @author 杨英
 * @version 2013-12-18 上午09:15
 */
public class HbpCurveInfo {

    //随访时间
    private String visitDate;
    //收缩压
    private String sbp;
    //舒张压
    private String dbp;

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getSbp() {
        return sbp;
    }

    public void setSbp(String sbp) {
        this.sbp = sbp;
    }

    public String getDbp() {
        return dbp;
    }

    public void setDbp(String dbp) {
        this.dbp = dbp;
    }
}
