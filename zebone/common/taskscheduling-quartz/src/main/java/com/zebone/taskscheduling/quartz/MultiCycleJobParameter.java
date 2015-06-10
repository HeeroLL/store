package com.zebone.taskscheduling.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerKey;


/**
 * 一个job对应多个执行周期
 * @author cz
 *
 */
public class MultiCycleJobParameter {
     
	/** 任务的名称  **/
	private JobKey jobKey;
	
	/** 传入ｊｏｂ的参数 **/
	private JobDataMap jobDataMap;
	
	/** 对应的执行类 **/
	private Class<? extends Job> jobClass;
	
	/** 任务开始时间 **/
	private Date startTime;
	
	/** 执行周期参数  **/
	private List<TriggerParameter> triggerParameterList = new ArrayList<TriggerParameter>();
 	


	public JobKey getJobKey() {
		return jobKey;
	}



	public void setJobKey(JobKey jobKey) {
		this.jobKey = jobKey;
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



	public Date getStartTime() {
		return startTime;
	}



	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	public List<TriggerParameter> getTriggerParameterList() {
		return triggerParameterList;
	}



	public void setTriggerParameterList(List<TriggerParameter> triggerParameterList) {
		this.triggerParameterList = triggerParameterList;
	}



	/** 触发器参数类 **/
	public static class TriggerParameter{
		/** 任务对应的触发器 **/
		private TriggerKey triggerKey;
		
		/** 任务build **/
		private ScheduleBuilder<? extends Trigger> scheduleBuilder;
		
		/** 传入ｊｏｂ的参数 **/
		private JobDataMap triggerJobDataMap;
		
		public TriggerKey getTriggerKey() {
			return triggerKey;
		}

		public void setTriggerKey(TriggerKey triggerKey) {
			this.triggerKey = triggerKey;
		}

		public ScheduleBuilder<? extends Trigger> getScheduleBuilder() {
			return scheduleBuilder;
		}

		public void setScheduleBuilder(
				ScheduleBuilder<? extends Trigger> scheduleBuilder) {
			this.scheduleBuilder = scheduleBuilder;
		}

		public JobDataMap getTriggerJobDataMap() {
			return triggerJobDataMap;
		}

		public void setTriggerJobDataMap(JobDataMap triggerJobDataMap) {
			this.triggerJobDataMap = triggerJobDataMap;
		}
		
	}

}
