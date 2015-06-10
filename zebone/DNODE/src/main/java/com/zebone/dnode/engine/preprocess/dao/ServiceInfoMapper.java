package com.zebone.dnode.engine.preprocess.dao;

import java.util.List;
import java.util.Map;

import com.zebone.btp.core.mybatis.DcMapper;
import com.zebone.dnode.engine.preprocess.vo.CommonForMedication;
import com.zebone.dnode.engine.preprocess.vo.CompositeInfo;
import com.zebone.dnode.engine.preprocess.vo.DmCurveInfo;
import com.zebone.dnode.engine.preprocess.vo.ExamInfo;
import com.zebone.dnode.engine.preprocess.vo.FollowUpInfo;
import com.zebone.dnode.engine.preprocess.vo.HbpCurveInfo;
import com.zebone.dnode.engine.preprocess.vo.InHospitalInfo;
import com.zebone.dnode.engine.preprocess.vo.InspectInfo;
import com.zebone.dnode.engine.preprocess.vo.LifeStyle;
import com.zebone.dnode.engine.preprocess.vo.ManageDoctorInfo;
import com.zebone.dnode.engine.preprocess.vo.MedicationInfo;
import com.zebone.dnode.engine.preprocess.vo.OutPatientInfo;
import com.zebone.dnode.engine.preprocess.vo.PreviousHisInfo;

/**
 * 业务表信息Dao
 *
 * @author 杨英
 * @version 2013-9-11 下午04:30
 */
@DcMapper
public interface ServiceInfoMapper {

    /**
     * 获取过敏史信息
     * @param paramMap
     * @return
     */
    public List<String> getAllergyInfo (Map paramMap);

    /**
     * 获取暴露史信息
     * @param paramMap
     * @return
     */
    public List<String> getExposureInfo (Map paramMap);

    /**
     * 获取疾病史信息
     * @param paramMap
     * @return
     */
    public List<PreviousHisInfo> getDiseasesInfo (Map paramMap);

    /**
     * 获取手术史信息
     * @param paramMap
     * @return
     */
    public List<PreviousHisInfo> getOperationInfo (Map paramMap);

    /**
     * 获取外伤史信息
     * @param paramMap
     * @return
     */
    public List<PreviousHisInfo> getRtumHisInfo (Map paramMap);

    /**
     * 获取输血史信息
     * @param paramMap
     * @return
     */
    public List<PreviousHisInfo> getTransfusionInfo (Map paramMap);

    /**
     * 获取家族史信息
     * @param paramMap
     * @return
     */
    public List<String> getFamilyInfo (Map paramMap);

    /**
     * 获取遗传病史信息
     * @param paramMap
     * @return
     */
    public List<String> getGeneticInfo (Map paramMap);

    /**
     * 获取残疾史信息
     * @param paramMap
     * @return
     */
    public List<String> getDisabilityInfo (Map paramMap);

    /**
     * 获取生活方式信息
     * @param paramMap
     * @return
     */
    public LifeStyle getLifeStyle (Map paramMap);

    /**
     * 获取饮酒种类信息
     * @param paramMap
     * @return
     */
    public List<String> getDrinkType (Map paramMap);

    /**
     * 获取首页近期检查信息
     * @param paramMap
     * @return
     */
    public List<ExamInfo> getExamInfo(Map paramMap);

    /**
     * 获取首页近期门诊信息
     * @param paramMap
     * @return
     */
    public List<OutPatientInfo> getOutPatientInfo(Map paramMap);

    /**
     * 获取首页近期检验信息
     * @param paramMap
     * @return
     */
    public List<InspectInfo> getInspectInfo(Map paramMap);

    /**
     * 获取首页近期住院信息
     * @param paramMap
     * @return
     */
    public List<InHospitalInfo> getInHospitalInfo(Map paramMap);

    /**
     * 获取首页近期随访信息
     * @param paramMap
     * @return
     */
    public List<FollowUpInfo> getFollowUpInfo(Map paramMap);

    /**
     * 获取随访血压信息
     * @param paramMap
     * @return
     */
    public List<HbpCurveInfo> getHbpCurveInfo(Map paramMap);

    /**
     * 获取随访血糖信息
     * @param paramMap
     * @return
     */
    public List<DmCurveInfo> getDmCurveInfo (Map paramMap);

    /**
     * 获取最近的门诊时间
     * @param empiNo
     * @return
     */
    public CommonForMedication getLatestOutPatientInfo (String empiNo);

    /**
     * 获取最近的住院时间
     * @param empiNo
     * @return
     */
    public CommonForMedication getLatestInHospitalInfo (String empiNo);

    /**
     * 获取门诊用药信息
     * @param paramMap
     * @return
     */
    public List<MedicationInfo> getOutPatientMedication (Map paramMap);

    /**
     * 获取住院用药信息
     * @param paramMap
     * @return
     */
    public List<MedicationInfo> getInHospitalMedication (Map paramMap);

    /**
     * 获取高血压随访用药信息
     * @param empiNo
     * @return
     */
    public List<MedicationInfo> getHbpMedication (String empiNo);

    /**
     * 获取糖尿病随访用药信息
     * @param empiNo
     * @return
     */
    public List<MedicationInfo> getDmMedication (String empiNo);

    /**
     * 获取老年人保健随访用药信息
     * @param empiNo
     * @return
     */
    public List<MedicationInfo> getElderMedication (String empiNo);

    /**
     * 获取管理医生信息
     * @param paramMap
     * @return
     */
    public ManageDoctorInfo getManageDoctorInfo (Map paramMap);

    /**
     * 获取综合情况信息
     * @param paramMap
     * @return
     */
    public CompositeInfo getCompositeInfo (Map paramMap);

    /**
     * 获取BMI指数
     * @param empiNo
     * @return
     */
    public String getBMIValue (String empiNo);
}
