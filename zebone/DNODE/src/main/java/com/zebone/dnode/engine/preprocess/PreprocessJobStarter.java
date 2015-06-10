package com.zebone.dnode.engine.preprocess;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.preprocess.jobs.CompositeInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.DiseasesInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.DmCurveInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.ExamInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.FollowUpInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.HbpCurveInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.InHospitalInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.InspectInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.LifeStyleInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.ManageDoctorInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.MedicationInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.OperationInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.OutPatientInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.RtumHisInfoJob;
import com.zebone.dnode.engine.preprocess.jobs.TransfusionInfoJob;
import com.zebone.dnode.engine.preprocess.service.GeneralInfoService;
import com.zebone.dnode.engine.preprocess.service.RecentlyInfoService;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.taskscheduling.quartz.util.QuartzUtil;

/**
 * 预处理任务启动器
 *
 * @author 杨英
 * @version 2013-9-13 下午03:18
 */
@Service("preprocessJobStarter")
public class PreprocessJobStarter {
	
	private static final Log log = LogFactory.getLog(PreprocessJobStarter.class);

    @Resource
    private GeneralInfoService generalInfoService;

    @Resource
    private RecentlyInfoService recentlyInfoService;

    //首页综合情况加工间隔时间
    @Value("${compositeInfo_interval}")
    private int compositeInfoInterval;

    //首页疾病史加工间隔时间
    @Value("${diseasesInfo_interval}")
    private int diseasesInfoInterval;

    //首页手术史加工间隔时间
    @Value("${operationInfo_interval}")
    private int operationInfoInterval;

    //首页外伤史加工间隔时间
    @Value("${rtumHisInfo_interval}")
    private int rtumHisInfoInterval;

    //首页输血史加工间隔时间
    @Value("${transfusionInfo_interval}")
    private int transfusionInfoInterval;

    //首页糖尿病曲线加工间隔时间
    @Value("${dmCurveInfo_interval}")
    private int dmCurveInfoInterval;

    //首页高血压曲线加工间隔时间
    @Value("${hbpCurveInfo_interval}")
    private int hbpCurveInfoInterval;

    //首页近期检查信息加工间隔时间
    @Value("${examInfo_interval}")
    private int examInfoInterval;

    //首页近期随访信息加工间隔时间
    @Value("${followUpInfo_interval}")
    private int followUpInfoInterval;

    //首页近期住院信息加工间隔时间
    @Value("${inHospitalInfo_interval}")
    private int inHospitalInfoInterval;

    //首页近期门诊信息加工间隔时间
    @Value("${outPatientInfo_interval}")
    private int outPatientInfoInterval;

    //首页近期检验信息加工间隔时间
    @Value("${inspectInfo_interval}")
    private int inspectInfoInterval;

    //首页生活习惯信息加工间隔时间
    @Value("${lifeStyleInfo_interval}")
    private int lifeStyleInfoInterval;

    //首页管理医生信息加工间隔时间
    @Value("${manageDoc_interval}")
    private int manageDocInterval;

    //首页近期用药信息加工间隔时间
    @Value("${medicationInfo_interval}")
    private int medicationInfoInterval;

    public void startJob() {
    	log.info("预处理健康档案首页数据");
        QuartzManager quartzManager = QuartzManager.getSchedulerFactory();

        //综合情况加工任务
        JobParameter compositeInfoJob = new JobParameter();
        JobDataMap compositeInfoMap = new JobDataMap();
        compositeInfoMap.put("generalInfoService", generalInfoService);
        compositeInfoJob.setJobClass(CompositeInfoJob.class);
        compositeInfoJob.setJobKey(JobKey.jobKey("00001", "preprocessCompositeInfo"));
        compositeInfoJob.setTriggerKey(TriggerKey.triggerKey("00001", "preprocessCompositeInfo"));
        compositeInfoJob.setJobDataMap(compositeInfoMap);
        compositeInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(compositeInfoInterval));
        quartzManager.addTask(compositeInfoJob);

        //疾病史加工任务
        JobParameter  diseasesInfoJob = new JobParameter();
        JobDataMap diseasesInfoMap = new JobDataMap();
        diseasesInfoMap.put("recentlyInfoService", recentlyInfoService);
        diseasesInfoJob.setJobClass(DiseasesInfoJob.class);
        diseasesInfoJob.setJobKey(JobKey.jobKey("00002", "preprocessDiseasesInfo"));
        diseasesInfoJob.setTriggerKey(TriggerKey.triggerKey("00002", "preprocessDiseasesInfo"));
        diseasesInfoJob.setJobDataMap(diseasesInfoMap);
        diseasesInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(diseasesInfoInterval));
        quartzManager.addTask(diseasesInfoJob);

        //手术史加工任务
        JobParameter  operationInfoJob = new JobParameter();
        JobDataMap operationInfoMap = new JobDataMap();
        operationInfoMap.put("recentlyInfoService", recentlyInfoService);
        operationInfoJob.setJobClass(OperationInfoJob.class);
        operationInfoJob.setJobKey(JobKey.jobKey("00003", "preprocessOperationInfo"));
        operationInfoJob.setTriggerKey(TriggerKey.triggerKey("00003", "preprocessOperationInfo"));
        operationInfoJob.setJobDataMap(operationInfoMap);
        operationInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(operationInfoInterval));
        quartzManager.addTask(operationInfoJob);

        //外伤史加工任务
        JobParameter  rtumHisInfoJob = new JobParameter();
        JobDataMap rtumHisInfoMap = new JobDataMap();
        rtumHisInfoMap.put("recentlyInfoService", recentlyInfoService);
        rtumHisInfoJob.setJobClass(RtumHisInfoJob.class);
        rtumHisInfoJob.setJobKey(JobKey.jobKey("00004", "preprocessRtumHisInfo"));
        rtumHisInfoJob.setTriggerKey(TriggerKey.triggerKey("00004", "preprocessRtumHisInfo"));
        rtumHisInfoJob.setJobDataMap(rtumHisInfoMap);
        rtumHisInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(rtumHisInfoInterval));
        quartzManager.addTask(rtumHisInfoJob);

        //输血史加工任务
        JobParameter  transfusionInfoJob = new JobParameter();
        JobDataMap transfusionInfoMap = new JobDataMap();
        transfusionInfoMap.put("recentlyInfoService", recentlyInfoService);
        transfusionInfoJob.setJobClass(TransfusionInfoJob.class);
        transfusionInfoJob.setJobKey(JobKey.jobKey("00005", "preprocessTransfusionInfo"));
        transfusionInfoJob.setTriggerKey(TriggerKey.triggerKey("00005", "preprocessTransfusionInfo"));
        transfusionInfoJob.setJobDataMap(transfusionInfoMap);
        transfusionInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(transfusionInfoInterval));
        quartzManager.addTask(transfusionInfoJob);

        //糖尿病曲线加工任务
        JobParameter  dmCurveInfoJob = new JobParameter();
        JobDataMap dmCurveInfoMap = new JobDataMap();
        dmCurveInfoMap.put("generalInfoService", generalInfoService);
        dmCurveInfoJob.setJobClass(DmCurveInfoJob.class);
        dmCurveInfoJob.setJobKey(JobKey.jobKey("00006", "preprocessDmCurveInfo"));
        dmCurveInfoJob.setTriggerKey(TriggerKey.triggerKey("00006", "preprocessDmCurveInfo"));
        dmCurveInfoJob.setJobDataMap(dmCurveInfoMap);
        dmCurveInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(dmCurveInfoInterval));
        quartzManager.addTask(dmCurveInfoJob);

        //高血压曲线加工任务
        JobParameter  hbpCurveInfoJob = new JobParameter();
        JobDataMap hbpCurveInfoMap = new JobDataMap();
        hbpCurveInfoMap.put("generalInfoService", generalInfoService);
        hbpCurveInfoJob.setJobClass(HbpCurveInfoJob.class);
        hbpCurveInfoJob.setJobKey(JobKey.jobKey("00007", "preprocessHbpCurveInfo"));
        hbpCurveInfoJob.setTriggerKey(TriggerKey.triggerKey("00007", "preprocessHbpCurveInfo"));
        hbpCurveInfoJob.setJobDataMap(hbpCurveInfoMap);
        hbpCurveInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(hbpCurveInfoInterval));
        quartzManager.addTask(hbpCurveInfoJob);

        //近期检查信息加工任务
        JobParameter  examInfoJob = new JobParameter();
        JobDataMap examInfoMap = new JobDataMap();
        examInfoMap.put("recentlyInfoService", recentlyInfoService);
        examInfoJob.setJobClass(ExamInfoJob.class);
        examInfoJob.setJobKey(JobKey.jobKey("00008", "preprocessExamInfo"));
        examInfoJob.setTriggerKey(TriggerKey.triggerKey("00008", "preprocessExamInfo"));
        examInfoJob.setJobDataMap(examInfoMap);
        examInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(examInfoInterval));
        quartzManager.addTask(examInfoJob);

        //近期随访信息加工任务
        JobParameter  followUpInfoJob = new JobParameter();
        JobDataMap followUpInfoMap = new JobDataMap();
        followUpInfoMap.put("recentlyInfoService", recentlyInfoService);
        followUpInfoJob.setJobClass(FollowUpInfoJob.class);
        followUpInfoJob.setJobKey(JobKey.jobKey("00009", "preprocessFollowUpInfo"));
        followUpInfoJob.setTriggerKey(TriggerKey.triggerKey("00009", "preprocessFollowUpInfo"));
        followUpInfoJob.setJobDataMap(followUpInfoMap);
        followUpInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(followUpInfoInterval));
        quartzManager.addTask(followUpInfoJob);

        //近期住院信息加工任务
        JobParameter  inHospitalInfoJob = new JobParameter();
        JobDataMap inHospitalInfoMap = new JobDataMap();
        inHospitalInfoMap.put("recentlyInfoService", recentlyInfoService);
        inHospitalInfoJob.setJobClass(InHospitalInfoJob.class);
        inHospitalInfoJob.setJobKey(JobKey.jobKey("00010", "preprocessInHospitalInfo"));
        inHospitalInfoJob.setTriggerKey(TriggerKey.triggerKey("00010", "preprocessInHospitalInfo"));
        inHospitalInfoJob.setJobDataMap(inHospitalInfoMap);
        inHospitalInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(inHospitalInfoInterval));
        quartzManager.addTask(inHospitalInfoJob);

        //近期检验加工任务
        JobParameter  inspectInfoJob = new JobParameter();
        JobDataMap inspectInfoMap = new JobDataMap();
        inspectInfoMap.put("recentlyInfoService", recentlyInfoService);
        inspectInfoJob.setJobClass(InspectInfoJob.class);
        inspectInfoJob.setJobKey(JobKey.jobKey("00011", "preprocessInspectInfo"));
        inspectInfoJob.setTriggerKey(TriggerKey.triggerKey("00011", "preprocessInspectInfo"));
        inspectInfoJob.setJobDataMap(inspectInfoMap);
        inspectInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(inspectInfoInterval));
        quartzManager.addTask(inspectInfoJob);

        //生活习惯加工任务
        JobParameter  lifeStyleInfoJob = new JobParameter();
        JobDataMap lifeStyleInfoMap = new JobDataMap();
        lifeStyleInfoMap.put("generalInfoService", generalInfoService);
        lifeStyleInfoJob.setJobClass(LifeStyleInfoJob.class);
        lifeStyleInfoJob.setJobKey(JobKey.jobKey("00012", "preprocessLifeStyleInfo"));
        lifeStyleInfoJob.setTriggerKey(TriggerKey.triggerKey("00012", "preprocessLifeStyleInfo"));
        lifeStyleInfoJob.setJobDataMap(lifeStyleInfoMap);
        lifeStyleInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(lifeStyleInfoInterval));
        quartzManager.addTask(lifeStyleInfoJob);

        //管理医生信息加工任务
        JobParameter  manageDocInfoJob = new JobParameter();
        JobDataMap manageDocInfoMap = new JobDataMap();
        manageDocInfoMap.put("generalInfoService", generalInfoService);
        manageDocInfoJob.setJobClass(ManageDoctorInfoJob.class);
        manageDocInfoJob.setJobKey(JobKey.jobKey("00013", "preprocessManageDoctorInfo"));
        manageDocInfoJob.setTriggerKey(TriggerKey.triggerKey("00013", "preprocessManageDoctorInfo"));
        manageDocInfoJob.setJobDataMap(manageDocInfoMap);
        manageDocInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(manageDocInterval));
        quartzManager.addTask(manageDocInfoJob);

        //近期用药信息加工任务
        JobParameter  medicationInfoJob = new JobParameter();
        JobDataMap medicationInfoMap = new JobDataMap();
        medicationInfoMap.put("recentlyInfoService", recentlyInfoService);
        medicationInfoJob.setJobClass(MedicationInfoJob.class);
        medicationInfoJob.setJobKey(JobKey.jobKey("00014", "preprocessMedicationInfo"));
        medicationInfoJob.setTriggerKey(TriggerKey.triggerKey("00014", "preprocessMedicationInfo"));
        medicationInfoJob.setJobDataMap(medicationInfoMap);
        medicationInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(medicationInfoInterval));
        quartzManager.addTask(medicationInfoJob);

        //近期门诊信息加工任务
        JobParameter  outPatientInfoJob = new JobParameter();
        JobDataMap outPatientInfoMap = new JobDataMap();
        outPatientInfoMap.put("recentlyInfoService", recentlyInfoService);
        outPatientInfoJob.setJobClass(OutPatientInfoJob.class);
        outPatientInfoJob.setJobKey(JobKey.jobKey("00015", "preprocessOutPatientInfo"));
        outPatientInfoJob.setTriggerKey(TriggerKey.triggerKey("00015", "preprocessOutPatientInfo"));
        outPatientInfoJob.setJobDataMap(outPatientInfoMap);
        outPatientInfoJob.setScheduleBuilder(QuartzUtil.simpleSchedule(outPatientInfoInterval));
        quartzManager.addTask(outPatientInfoJob);
    }
}
