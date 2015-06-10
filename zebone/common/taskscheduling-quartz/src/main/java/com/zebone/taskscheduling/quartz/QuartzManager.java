package com.zebone.taskscheduling.quartz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.zebone.taskscheduling.quartz.MultiCycleJobParameter.TriggerParameter;
import com.zebone.taskscheduling.quartz.util.QuartzUtil;

/**
 * quartz manager类
 * 
 * @author cz
 * 
 */
public class QuartzManager {

	private static final Log logger = LogFactory.getLog(QuartzManager.class);

	/** quartz配置文件 * */
	private static final String QUARTZ_CFG = "com/zebone/taskscheduling/quartz/config/quartz.properties";

	private static SchedulerFactory schedulerFactory;

	private static Scheduler scheduler;

	private QuartzManager() {

	}

	public static synchronized QuartzManager getSchedulerFactory(
			String quartzConfig) {
		try {
			if (schedulerFactory != null) {
				schedulerFactory = new StdSchedulerFactory(quartzConfig);
				scheduler = schedulerFactory.getScheduler();
				startScheduler();
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		return new QuartzManager();
	}

	public static synchronized QuartzManager getSchedulerFactory() {
		try {
			if (schedulerFactory == null) {
				schedulerFactory = new StdSchedulerFactory(QUARTZ_CFG);
				scheduler = schedulerFactory.getScheduler();
				startScheduler();
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		return new QuartzManager();
	}
	
	public Scheduler getScheduler() {
		getSchedulerFactory();
		return scheduler;
	}

	private static void startScheduler() throws SchedulerException {
		if (scheduler != null) {
			scheduler.start();
		}
	}

	/**
	 * 新增任务
	 * 
	 * @param jobp
	 */
	public void addTask(JobParameter jobp) {
		if (QuartzUtil.checkJobParameter(jobp)) {
			JobDetail jd = JobBuilder.newJob(jobp.getJobClass())
					.withIdentity(jobp.getJobKey()).build();
			if (jobp.getJobDataMap() != null) {
				jd.getJobDataMap().putAll(jobp.getJobDataMap());
			}
			Trigger jdt = null;
			TriggerBuilder<Trigger> tb = TriggerBuilder.newTrigger()
					.withIdentity(jobp.getTriggerKey());
			if (jobp.getScheduleBuilder() != null) {
				tb.withSchedule(jobp.getScheduleBuilder());
			}
			if (jobp.getStartTime() == null) {
				tb.startNow();
			} else {
				tb.startAt(jobp.getStartTime());
			}
			jdt = tb.build();
			try {
				scheduler.scheduleJob(jd, jdt);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 新增多个执行周期的任务
	 * 
	 * @author 陈阵
	 * @date 2014-4-29 下午2:15:17
	 */
	public void addMultiCycleTask(MultiCycleJobParameter multiCycleJobParameter) {
		if (QuartzUtil.checkMultiCycleJobParameter(multiCycleJobParameter)) {
			Map<JobDetail, List<Trigger>> eParamMap = new HashMap<JobDetail, List<Trigger>>();

			JobDetail jd = JobBuilder
					.newJob(multiCycleJobParameter.getJobClass())
					.withIdentity(multiCycleJobParameter.getJobKey()).build();
			if (multiCycleJobParameter.getJobDataMap() != null) {
				jd.getJobDataMap().putAll(
						multiCycleJobParameter.getJobDataMap());
			}

			List<Trigger> triggerList = new ArrayList<Trigger>();
			List<TriggerParameter> triggerParameterList = multiCycleJobParameter
					.getTriggerParameterList();
			for (TriggerParameter tp : triggerParameterList) {
				TriggerBuilder<Trigger> tb = TriggerBuilder.newTrigger()
						.withIdentity(tp.getTriggerKey());
				if (tp.getScheduleBuilder() != null) {
					tb.withSchedule(tp.getScheduleBuilder());
				}
				if (tp.getTriggerJobDataMap() != null) {
					tb.usingJobData(tp.getTriggerJobDataMap());
				}
				tb.forJob(jd);
				tb.startNow();
				triggerList.add(tb.build());
			}
			eParamMap.put(jd, triggerList);

			try {
				scheduler.scheduleJobs(eParamMap, true);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
			}
		}
	}

	
}
