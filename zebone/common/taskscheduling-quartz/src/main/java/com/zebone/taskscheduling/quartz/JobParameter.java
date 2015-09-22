package com.zebone.taskscheduling.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

/**
 * quartz相关参数
 * @author cz
 *
 */
public class JobParameter {
     
	/** 任务的名称  **/
	private JobKey jobKey;
	
	/** 任务对应的触发器 **/
	private TriggerKey triggerKey;
	
	/** 传入ｊｏｂ的参数 **/
	private JobDataMap jobDataMap;
	
	/** 对应的执行类 **/
	private Class<? extends Job> jobClass;
	
	/** 任务build **/
	private ScheduleBuilder<? extends Trigger> scheduleBuilder;
	
	/** 任务开始时间 **/
	private Date startTime;
	
	

	public JobKey getJobKey() {
		return jobKey;
	}


	public void setJobKey(JobKey jobKey) {
		this.jobKey = jobKey;
	}


	public TriggerKey getTriggerKey() {
		return triggerKey;
	}


	public void setTriggerKey(TriggerKey triggerKey) {
		this.triggerKey = triggerKey;
	}


	public JobDataMap getJobDataMap() {
		return jobDataMap;
	}


	public void setJobDataMap(JobDataMap jobDataMap) {
		this.jobDataMap = jobDataMap;
	}


	public Class<? extends Job> getJobClass() {
		return jobClass;
	}


	public void setJobClass(Class<? extends Job> jobClass) {
		this.jobClass = jobClass;
	}


	public ScheduleBuilder<? extends Trigger> getScheduleBuilder() {
		return scheduleBuilder;
	}


	public void setScheduleBuilder(
			ScheduleBuilder<? extends Trigger> scheduleBuilder) {
		this.scheduleBuilder = scheduleBuilder;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
