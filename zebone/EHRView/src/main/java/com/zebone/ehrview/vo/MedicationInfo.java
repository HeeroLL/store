package com.zebone.ehrview.vo;

/**
 * 首页近期用药信息列表对象
 *
 * @author 杨英
 * @version 2013-12-21 上午08:51
 */
public class MedicationInfo {
    //分类(门诊，住院，长期)
    private String category;
    //日期
    private String date;
    //药物名称
    private String drugName;
    //用法
    private String drugUsage;
    //用药途径
    private String drugOpPath;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugUsage() {
        return drugUsage;
    }

    public void setDrugUsage(String drugUsage) {
        this.drugUsage = drugUsage;
    }

    public String getDrugOpPath() {
        return drugOpPath;
    }

    public void setDrugOpPath(String drugOpPath) {
        this.drugOpPath = drugOpPath;
    }
}
