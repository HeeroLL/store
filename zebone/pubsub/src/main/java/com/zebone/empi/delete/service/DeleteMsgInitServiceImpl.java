package com.zebone.empi.delete.service;

import com.zebone.empi.delete.job.DeleteMsgJob;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.taskscheduling.quartz.util.QuartzUtil;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 服务初始化
 * @author 杨英
 * @version 2014-7-15 上午8:31
 */
@Service("deleteMsgInitService")
public class DeleteMsgInitServiceImpl implements DeleteMsgInitService{

    @Resource
    private DeleteMsgService deleteMsgService;

    @Override
    public void init() {
        QuartzManager qm = QuartzManager.getSchedulerFactory();
        JobParameter jp = new JobParameter();
        /** 设置对应的执行任务类 **/
        jp.setJobClass(DeleteMsgJob.class);
        jp.setJobKey(JobKey.jobKey("删除推送信息任务"));
        jp.setTriggerKey(TriggerKey.triggerKey("001"));
        jp.setScheduleBuilder(QuartzUtil.simpleSchedule(300));
        JobDataMap jdm = new JobDataMap();
        jdm.put("deleteMsgService", deleteMsgService);
        jp.setJobDataMap(jdm);
        qm.addTask(jp);
    }
}
