package com.zebone.dnode.engine.notice;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.notice.job.NoticeJob;
import com.zebone.notice.service.NoticeService;
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
@Service("noticeJobStarter")
public class NoticeJobStarter {
    private static final Log log = LogFactory.getLog(NoticeJobStarter.class);

    /**
     * 通知服务
     */
    @Resource
    private NoticeService noticeService;
    
    @Value("${notice_interval}")
    private int noticeInterval;

    public void startJob() {
        log.info("通知公告状态更新任务开始----");
        QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
        JobParameter jobParameter = new JobParameter();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("noticeService", noticeService);
        jobParameter.setJobClass(NoticeJob.class);
        jobParameter.setJobKey(JobKey.jobKey("100001", "notice"));
        jobParameter.setTriggerKey(TriggerKey.triggerKey("100001", "notice"));
        jobParameter.setJobDataMap(jobDataMap);
        jobParameter.setScheduleBuilder(QuartzUtil.simpleSchedule(noticeInterval));
        quartzManager.addTask(jobParameter);
    }
}
