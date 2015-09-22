package com.zebone.dnode.engine.empi.job;

import com.zebone.dnode.engine.empi.service.EmpiPushService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 主索引信息推送任务
 *
 * @author 杨英
 * @version 2014-06-16 上午09:12
 */
public class EmpiInfoPushJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(EmpiInfoPushJob.class);

    private EmpiPushService empiPushService;

    public void setEmpiPushService(EmpiPushService empiPushService) {
        this.empiPushService = empiPushService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            empiPushService.pushEmpiInfo();
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
        }
    }
}
