package com.zebone.dnode.engine.preprocess.service;



/**
 * 预处理近期信息服务,如近期检查,近期检验等
 *
 * @author 杨英
 * @version 2013-12-16 上午08:55
 */
public interface RecentlyInfoService {

    /**
     * 预处理近期检查信息
     */
    public void preprocessExamInfo();

    /**
     * 预处理近期检验信息
     */
    public void preprocessInspectInfo();

    /**
     * 预处理近期门诊信息
     */
    public void preprocessOutPatientInfo();

    /**
     * 预处理近期用药信息
     */
    public void preprocessMedicationInfo();

    /**
     * 预处理近期住院信息
     */
    public void preprocessInHospitalInfo();

    /**
     * 预处理近期随访信息
     */
    public void preprocessFollowUpInfo();

    /**
     * 预处理疾病史信息
     */
    public void preprocessDiseasesInfo();

    /**
     * 预处理手术史信息
     */
    public void preprocessOperationInfo();

    /**
     * 预处理外伤史信息
     */
    public void preprocessRtumHisInfo();

    /**
     * 预处理输血史信息
     */
    public void preprocessTransfusionInfo();
}
