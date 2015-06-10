package com.zebone.pubsub.server.service;

import javax.annotation.Resource;

import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;

import com.zebone.pubsub.server.job.PubSubPollJob;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.taskscheduling.quartz.util.QuartzUtil;

@Service("pollServiceInit")
public class PollServiceInitImpl implements PollServiceInit {
	
	@Resource
	private PollService pollService;

	@Override
	public void init() {
		// TODO Auto-generated method stub
	    QuartzManager qm = QuartzManager.getSchedulerFactory();
	    JobParameter jp = new JobParameter();
		/** 设置对应的执行任务类 **/
		jp.setJobClass(PubSubPollJob.class);
		jp.setJobKey(JobKey.jobKey("轮询任务"));
		jp.setTriggerKey(TriggerKey.triggerKey("001"));
	    jp.setScheduleBuilder(QuartzUtil.simpleSchedule(720));
		JobDataMap jdm = new JobDataMap();
		jdm.put("pollService", pollService);
		jp.setJobDataMap(jdm);
		
		qm.addTask(jp);
	}

}
