package com.zebone.dnode.engine.preprocess.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebone.dnode.engine.preprocess.service.GeneralInfoService;


/**
 * 预处理综合情况任务
 *
 * @author 杨英
 * @version 2013-12-20 上午09:57
 */
public class CompositeInfoJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(CompositeInfoJob.class);

    private GeneralInfoService generalInfoService;

    public void setGeneralInfoService(GeneralInfoService generalInfoService) {
        this.generalInfoService = generalInfoService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            generalInfoService.preprocessCompositeInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
