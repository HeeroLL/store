package com.zebone.taskscheduling.quartz.util;


import org.quartz.CronScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;

import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.MultiCycleJobParameter;



/**
 * quartz工具类
 * @author cz
 *
 */
public class QuartzUtil {
    	
	/**
	 * 判断JobParameter的相关参数
	 * @param jobp
	 * @return
	 */
	public static boolean checkJobParameter(JobParameter jobp){
		if(jobp == null){
			throw new NullPointerException("JobParameter is null");
		}else if(jobp.getJobClass() == null){
			throw new NullPointerException("JobParameter job class is null");
		}else if(jobp.getJobKey() == null){
			throw new NullPointerException("JobParameter jobkey is null");
		}else if(jobp.getTriggerKey() == null){
			throw new NullPointerException("JobParameter triggerkey is null");
		}
		return true;
	}
     
	/**
	 * 判断MultiCycleJobParameter参数
	 * @param mJobp
	 * @return
	 * @author 陈阵 
	 * @date 2014-4-29 下午2:43:36
	 */
	public static boolean checkMultiCycleJobParameter(MultiCycleJobParameter mJobp){
		if(mJobp == null){
			throw new NullPointerException("MultiCycleJobParameter is null");
		}else if(mJobp.getJobClass() == null){
			throw new NullPointerException("MultiCycleJobParameter job class is null");
		}else if(mJobp.getJobKey() == null){
			throw new NullPointerException("MultiCycleJobParameter jobkey is null");
		}else if(mJobp.getTriggerParameterList() == null &&mJobp.getTriggerParameterList().size() == 0){
			throw new NullPointerException("MultiCycleJobParameter triggerParameterList is null");
		}
		return true;
	}
	/**
	 * cron任务
	 * @param cronExpression
	 * @return
	 */
	public static CronScheduleBuilder cronSchedule(String cronExpression){
		return CronScheduleBuilder.cronSchedule(cronExpression);
	}
	
	
	/**
	 * 简单任务
	 * @param intervalInSeconds 每隔多少秒
	 * @param repeatCount  重复次数
	 * @return
	 */
	public static SimpleScheduleBuilder simpleSchedule(int intervalInSeconds,int repeatCount) {
		return SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(
				intervalInSeconds).withRepeatCount(repeatCount);
	}
	
	
    /**
     * 简单任务
     * @param intervalInSeconds 每隔多少秒
     * @return
     */
	public static SimpleScheduleBuilder simpleSchedule(int intervalInSeconds) {
		return SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(
				intervalInSeconds).repeatForever();
	}
	



}
