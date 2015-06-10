package com.zebone.dnode.engine.auditlog;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zebone.auditlog.service.AuditlogDbService;
import com.zebone.dnode.engine.auditlog.job.AuditlogJob;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.taskscheduling.quartz.util.QuartzUtil;

/**
 * 
 * 通知公告状态更新任务启动器
 * 
 * @author lilin
 * @version [版本号, 2015年5月26日]
 */
@Service("auditlogJobStarter")
public class AuditlogJobStarter {
    private static final Log log = LogFactory.getLog(AuditlogJobStarter.class);

    /**
     * 审计日志接口接口
     */
    @Resource
    private AuditlogDbService auditlogDbService;
    
    @Value("${auditlog_interval}")
    private int auditlogInterval;

    public void startJob() {
        log.info("日志信息入库任务开始----");
        QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
        JobParameter jobParameter = new JobParameter();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("auditlogDbService", auditlogDbService);
        jobParameter.setJobClass(AuditlogJob.class);
        jobParameter.setJobKey(JobKey.jobKey("100002", "auditlog"));
        jobParameter.setTriggerKey(TriggerKey.triggerKey("100002", "auditlog"));
        jobParameter.setJobDataMap(jobDataMap);
        jobParameter.setScheduleBuilder(QuartzUtil.simpleSchedule(auditlogInterval));
        quartzManager.addTask(jobParameter);
    }
}
