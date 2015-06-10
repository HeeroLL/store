package com.zebone.dnode.engine.empi;

import com.zebone.dnode.engine.empi.job.EmpiInfoPushJob;
import com.zebone.dnode.engine.empi.service.EmpiPushService;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.taskscheduling.quartz.util.QuartzUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 主索引信息推送任务启动器
 *
 * @author 杨英
 * @version 2014-6-17 下午3:07
 */
@Service("empiPushJobStarter")
public class EmpiPushJobStarter {
    private static final Log log = LogFactory.getLog(EmpiPushJobStarter.class);

    @Resource
    private EmpiPushService empiPushService;

    @Value("${empiPush_interval}")
    private int empiPushInterval;

    public void startJob() {
        log.info("推送信息任务开始----");
        QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
        JobParameter pushJobParameter = new JobParameter();
        JobDataMap pushJobDataMap = new JobDataMap();
        pushJobDataMap.put("empiPushService", empiPushService);
        pushJobParameter.setJobClass(EmpiInfoPushJob.class);
        pushJobParameter.setJobKey(JobKey.jobKey("000012", "empiPush"));
        pushJobParameter.setTriggerKey(TriggerKey.triggerKey("000012", "empiPush"));
        pushJobParameter.setJobDataMap(pushJobDataMap);
        pushJobParameter.setScheduleBuilder(QuartzUtil.simpleSchedule(empiPushInterval));
        quartzManager.addTask(pushJobParameter);
    }
}
