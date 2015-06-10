package com.zebone.dnode.task.service.impl;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zebone.dnode.task.dto.Task;
import com.zebone.dnode.task.service.CenterTaskService;
import com.zebone.dnode.task.util.TaskQuartzUtil;
import com.zebone.dnode.task.util.TaskKey;

public abstract class BaseTaskService {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseTaskService.class);
	
	@Autowired
	protected Scheduler sch;
	
	@Autowired
	protected CenterTaskService centerTaskService;

	
	/**
	 * 批量删除任务
	 * @param tasks
	 */
	protected void delTasks(List<Task> tasks) {
		if(tasks != null && tasks.size() > 0){
			List<JobKey> jobKeyList = TaskQuartzUtil.genJobKey(tasks);
			try {
				sch.deleteJobs(jobKeyList);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	/**
	 * 删除任务
	 * @param task
	 */
	protected void delTask(Task task) {
		if(task != null){
			JobKey jobKey = TaskQuartzUtil.genJobKey(task);
			try {
				sch.deleteJob(jobKey);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	
	protected void addTask(Task task, JobDataMap jdm,
			Class<? extends Job> jobClass) {
		String executeTime = task.getTaskFreq();
		String taskCode = task.getTaskCode();
		JobDetail jd = JobBuilder.newJob(jobClass)
				.withIdentity(TaskKey.TASK + taskCode, TaskKey.TASK_GROUP)
				.build();
		jd.getJobDataMap().putAll(jdm);
		Trigger jdt = null;
		TriggerBuilder<Trigger> tb = TriggerBuilder.newTrigger().withIdentity(
				TaskKey.TASK_TRIGGER + taskCode, TaskKey.TASK_TRIGGER_GROUP).startNow();
		/** 判断是否cron表达式 **/
		if (TaskQuartzUtil.isCronRegx(executeTime)) {
			tb.withSchedule(CronScheduleBuilder.cronSchedule(executeTime));
		}
		jdt = tb.build();
		try {
			sch.scheduleJob(jd, jdt);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}

	}

	protected void updateTask(Task task) {
		String executeTime = task.getTaskFreq();
		String taskCode = task.getTaskCode();
		TriggerKey tk = TriggerKey.triggerKey(TaskKey.TASK_TRIGGER + taskCode,
				TaskKey.TASK_TRIGGER_GROUP);
		Trigger trigger = null;
		try {
			TriggerBuilder<Trigger> tb = TriggerBuilder.newTrigger()
					.withIdentity(TaskKey.TASK_TRIGGER + taskCode,
							TaskKey.TASK_TRIGGER_GROUP).startNow();
			/** 判断是否cron表达式 **/
			if (TaskQuartzUtil.isCronRegx(executeTime)) {
				tb.withSchedule(CronScheduleBuilder.cronSchedule(executeTime));
			}
			sch.rescheduleJob(tk, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}

	}
}
