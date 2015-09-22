package com.zebone.dnode.engine.preprocess.service;


/**
 * 预处理综合信息服务,如生活习惯,综合情况等
 *
 * @author 杨英
 * @version 2013-12-17 下午02:58
 */
public interface GeneralInfoService {

    /**
     * 预处理生活习惯
     */
    public void preprocessLifeStyleInfo();

    /**
     * 预处理高血压曲线
     */
    public void preprocessHbpCurveInfo();

    /**
     * 预处理糖尿病曲线
     */
    public void preprocessDmCurveInfo();

    /**
     * 预处理管理医生信息
     */
    public void preprocessManageDoctorInfo();

    /**
     * 预处理综合情况
     */
    public void preprocessCompositeInfo();
}
