package com.zebone.dnode.engine.preprocess.jobs;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebone.dnode.engine.preprocess.service.RecentlyInfoService;

/**
 * 预处理输血史信息任务
 *
 * @author 杨英
 * @version 2013-12-17 下午01:47
 */
public class TransfusionInfoJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(TransfusionInfoJob.class);

    private RecentlyInfoService recentlyInfoService;

    public void setRecentlyInfoService(RecentlyInfoService recentlyInfoService) {
        this.recentlyInfoService = recentlyInfoService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            recentlyInfoService.preprocessTransfusionInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
