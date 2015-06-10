package com.zebone.dnode.engine.auditlog.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebone.auditlog.service.AuditlogDbService;

/**
 * 
 * 公告通知状态更新任务
 * 
 * @author lilin
 * @version [版本号, 2015年5月26日]
 */
public class AuditlogJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(AuditlogJob.class);

    /**
     * 审计日志接口接口
     */
    private AuditlogDbService auditlogDbService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            auditlogDbService.saveLogToDb();
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
        }
    }

    public void setAuditlogDbService(AuditlogDbService auditlogDbService) {
        this.auditlogDbService = auditlogDbService;
    }
}
