package com.zebone.dnode.engine.preprocess.vo;

/**
 * 首页综合情况对象
 *
 * @author 杨英
 * @version 2013-12-20 上午10:07
 */
public class CompositeInfo {
    //ABO血型
    private String ABOType;
    //RH血型
    private String RHType;
    //过敏史
    private String allergen;
    //家族史
    private String familyDisease;
    //遗传病史
    private String geneticHis;
    //残疾类型
    private String disabilityType;
    //BMI指数
    private String BMI;

    public String getABOType() {
        return ABOType;
    }

    public void setABOType(String ABOType) {
        this.ABOType = ABOType;
    }

    public String getRHType() {
        return RHType;
    }

    public void setRHType(String RHType) {
        this.RHType = RHType;
    }

    public String getAllergen() {
        return allergen;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }

    public String getFamilyDisease() {
        return familyDisease;
    }

    public void setFamilyDisease(String familyDisease) {
        this.familyDisease = familyDisease;
    }

    public String getGeneticHis() {
        return geneticHis;
    }

    public void setGeneticHis(String geneticHis) {
        this.geneticHis = geneticHis;
    }

    public String getDisabilityType() {
        return disabilityType;
    }

    public void setDisabilityType(String disabilityType) {
        this.disabilityType = disabilityType;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }
}
