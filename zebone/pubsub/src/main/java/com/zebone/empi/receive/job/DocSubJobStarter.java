package com.zebone.empi.receive.job;

import com.zebone.empi.receive.service.DocSubJobService;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.taskscheduling.quartz.util.QuartzUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 文档订阅任务启动器
 *
 * @author 杨英
 * @version 2014-8-13 下午2:58
 */
@Service("docSubJobStarter")
public class DocSubJobStarter {

    private static final Log log = LogFactory.getLog(DocSubJobStarter.class);

    @Resource
    private DocSubJobService docSubJobService;

    //文档订阅任务的执行时间
    @Value("${executeTime}")
    private String executeTime;

    public void startJob() {
        log.info("----文档订阅任务开始----");
        QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
        JobParameter jobParameter = new JobParameter();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("docSubJobService", docSubJobService);
        jobParameter.setJobClass(DocSubJob.class);
        jobParameter.setJobKey(JobKey.jobKey("000111", "job_docSub"));
        jobParameter.setTriggerKey(TriggerKey.triggerKey("000111", "trigger_docSub"));
        jobParameter.setJobDataMap(jobDataMap);
        if (StringUtils.isNotEmpty(executeTime)) {
            String[] executeTimeLic = executeTime.split(",");
            for (int i = 0; i < executeTimeLic.length; i++) {
                jobParameter.setScheduleBuilder(QuartzUtil.cronSchedule(executeTimeLic[i]));
            }
        }
        quartzManager.addTask(jobParameter);
        
        
        JobParameter jobParameter1 = new JobParameter();

        log.info("----文档订阅增量任务开始----");
        JobDataMap jobDataMap1 = new JobDataMap();
        jobDataMap1.put("docSubJobService", docSubJobService);
        jobParameter1.setJobClass(IncDocSubJob.class);
        jobParameter1.setJobKey(JobKey.jobKey("000111", "job_incDocSub"));
        jobParameter1.setTriggerKey(TriggerKey.triggerKey("000111", "trigger_incDocSub"));
        jobParameter1.setJobDataMap(jobDataMap1);
        jobParameter1.setScheduleBuilder(QuartzUtil.cronSchedule("0 0 3,15 * * ?"));
        

        quartzManager.addTask(jobParameter1);
    }
}
