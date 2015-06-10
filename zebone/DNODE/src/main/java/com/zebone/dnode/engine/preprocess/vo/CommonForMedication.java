package com.zebone.dnode.engine.preprocess.vo;

/**
 * 公用对象，用于近期用药
 *
 * @author 杨英
 * @version 2013-12-19 上午10:55
 */
public class CommonForMedication {
    //最近的日期
    private String latestDt;
    //住院号,门诊号或其他
    private String singleNo;

    public String getLatestDt() {
        return latestDt;
    }

    public void setLatestDt(String latestDt) {
        this.latestDt = latestDt;
    }

    public String getSingleNo() {
        return singleNo;
    }

    public void setSingleNo(String singleNo) {
        this.singleNo = singleNo;
    }
}
