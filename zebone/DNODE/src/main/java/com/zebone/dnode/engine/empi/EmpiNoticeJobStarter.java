package com.zebone.dnode.engine.empi;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.empi.service.DispatchService;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.taskscheduling.quartz.util.QuartzUtil;

/**
 * 主索引注册通知任务启动器
 *
 * @author 杨英
 * @version 2013-10-30 上午10:40
 */
@Service("empiNoticeJobStarter")
public class EmpiNoticeJobStarter {
    private static final Log log = LogFactory.getLog(EmpiNoticeJobStarter.class);

    @Resource
    private DispatchService dispatchService;
    @Value("${empiNotice_interval}")
    private int empiNoticeInterval;

    public void startJob() {
        log.info("主索引通知任务开始----");
        QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
        JobParameter jobParameter = new JobParameter();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("dispatchService", dispatchService);
        jobParameter.setJobClass(EmpiNoticeJob.class);
        jobParameter.setJobKey(JobKey.jobKey("000011", "empiNotice"));
        jobParameter.setTriggerKey(TriggerKey.triggerKey("000011", "empiNotice"));
        jobParameter.setJobDataMap(jobDataMap);
        jobParameter.setScheduleBuilder(QuartzUtil.simpleSchedule(empiNoticeInterval));
        quartzManager.addTask(jobParameter);
    }
}
