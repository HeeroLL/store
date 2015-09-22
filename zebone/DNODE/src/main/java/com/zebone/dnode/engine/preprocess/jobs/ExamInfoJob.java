package com.zebone.dnode.engine.preprocess.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebone.dnode.engine.preprocess.service.RecentlyInfoService;

/**
 * 预处理近期检查信息
 *
 * @author 杨英
 * @version 2013-12-11 下午03:25
 */
public class ExamInfoJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(ExamInfoJob.class);

    private RecentlyInfoService recentlyInfoService;

    public void setRecentlyInfoService(RecentlyInfoService recentlyInfoService) {
        this.recentlyInfoService = recentlyInfoService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            recentlyInfoService.preprocessExamInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
