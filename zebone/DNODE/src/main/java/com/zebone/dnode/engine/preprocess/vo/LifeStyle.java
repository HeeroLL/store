package com.zebone.dnode.engine.preprocess.vo;

/**
 * 首页生活方式对象
 *
 * @author 杨英
 * @version 2013-9-25 上午10:20
 */
public class LifeStyle {
    //运动频率
    private String sportFreq;
    //饮食习惯
    private String dietCustom;
    //吸烟状况
    private String smokeStatus;
    //有危害因素职业描述
    private String occHazardousDesc;
    //从事有危害因素职业时长
    private String occHazardousSt;

    public String getSportFreq() {
        return sportFreq;
    }

    public void setSportFreq(String sportFreq) {
        this.sportFreq = sportFreq;
    }

    public String getDietCustom() {
        return dietCustom;
    }

    public void setDietCustom(String dietCustom) {
        this.dietCustom = dietCustom;
    }

    public String getSmokeStatus() {
        return smokeStatus;
    }

    public void setSmokeStatus(String smokeStatus) {
        this.smokeStatus = smokeStatus;
    }

    public String getOccHazardousDesc() {
        return occHazardousDesc;
    }

    public void setOccHazardousDesc(String occHazardousDesc) {
        this.occHazardousDesc = occHazardousDesc;
    }

    public String getOccHazardousSt() {
        return occHazardousSt;
    }

    public void setOccHazardousSt(String occHazardousSt) {
        this.occHazardousSt = occHazardousSt;
    }
}
