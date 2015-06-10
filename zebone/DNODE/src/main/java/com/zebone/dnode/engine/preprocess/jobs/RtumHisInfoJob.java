package com.zebone.dnode.engine.preprocess.jobs;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebone.dnode.engine.preprocess.service.RecentlyInfoService;

/**
 * 预处理外伤史信息任务
 *
 * @author 杨英
 * @version 2013-12-17 下午01:45
 */
public class RtumHisInfoJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(RtumHisInfoJob.class);

    private RecentlyInfoService recentlyInfoService;

    public void setRecentlyInfoService(RecentlyInfoService recentlyInfoService) {
        this.recentlyInfoService = recentlyInfoService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            recentlyInfoService.preprocessRtumHisInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
