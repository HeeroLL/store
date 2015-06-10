package com.zebone.dnode.engine.preprocess.jobs;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebone.dnode.engine.preprocess.service.GeneralInfoService;

/**
 * 预处理管理医生信息任务
 *
 * @author 杨英
 * @version 2013-12-20 上午09:08
 */
public class ManageDoctorInfoJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(ManageDoctorInfoJob.class);

    private GeneralInfoService generalInfoService;

    public void setGeneralInfoService(GeneralInfoService generalInfoService) {
        this.generalInfoService = generalInfoService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            generalInfoService.preprocessManageDoctorInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
