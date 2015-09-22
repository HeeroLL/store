package com.zebone.dnode.engine.preprocess.vo;

/**
 * 首页糖尿病曲线对象
 *
 * @author 杨英
 * @version 2013-12-18 下午01:38
 */
public class DmCurveInfo {
    //随访日期
    private String visitDate;
    //空腹血糖值
    private String fbg;

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getFbg() {
        return fbg;
    }

    public void setFbg(String fbg) {
        this.fbg = fbg;
    }
}
