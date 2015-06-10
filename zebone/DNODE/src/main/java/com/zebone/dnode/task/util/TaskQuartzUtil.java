package com.zebone.dnode.task.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;

import com.zebone.dnode.task.dto.Task;

/**
 * 任务工具类
 * 
 * @author cz
 * 
 */
public class TaskQuartzUtil {

	/**
	 * 判断任务是否存在
	 * 
	 * @param task
	 * @param oldTasks
	 * @return
	 */
	public static boolean containsTask(Task task, List<Task> oldTasks) {
		if (oldTasks.contains(task)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断任务是否更新
	 * 
	 * @param task
	 * @param oldTasks
	 * @return
	 */
	public static int isTaskChange(Task task, List<Task> oldTasks) {
		for (int i = 0; i < oldTasks.size(); i++) {
			Task tTask = oldTasks.get(i);
			int change = changeValue(task, tTask);
			if (change != 0) {
				return change;
			}
		}
		return 0;
	}
	
	/**
	 * 判断任务更新类型
	 * @param o1
	 * @return
	 */
	private static int changeValue(Task o1, Task o2) {
		// TODO Auto-generated method stub
		if(o1.getTaskCode().equals(o2.getTaskCode())){
			if (o1.getTaskType() != o2.getTaskType()){
				return 2;
			}else if(!o1.getTaskFreq().equals(o2.getTaskFreq())){
				return 1;
			}
		}
		return 0;
	}

	/**
	 * 返回任务的差集
	 * 
	 * @param oTasks
	 * @param tTasks
	 * @return
	 */
	public static List<Task> getNotContainsTasks(List<Task> oTasks,
			List<Task> tTasks) {
		List<Task> rTask = new ArrayList<Task>();
		for (int i = 0; i < oTasks.size(); i++) {
			Task oTask = oTasks.get(i);
		    if(!tTasks.contains(oTask)){
		    	rTask.add(oTask);
		    }
		}
		return rTask;
	}

	/**
	 * 根据task获取jobkey
	 * 
	 * @param tasks
	 * @return
	 */
	public static List<JobKey> genJobKey(List<Task> tasks) {
		List<JobKey> jobKeyList = new ArrayList<JobKey>();
		for (Task task : tasks) {
			JobKey jk = new JobKey(TaskKey.TASK + task.getTaskCode(),
					TaskKey.TASK_GROUP);
			jobKeyList.add(jk);
		}
		return jobKeyList;
	}

	/**
	 * 根据task获取jobkey
	 * 
	 * @param task
	 * @return
	 */
	public static JobKey genJobKey(Task task) {
		JobKey jk = new JobKey(TaskKey.TASK + task.getTaskCode(),
				TaskKey.TASK_GROUP);
		return jk;
	}

	/**
	 * 获取所有任务的jobkey
	 * 
	 * @param sch
	 */
	public static List<JobKey> getTaskJob(Scheduler sch, String group) {
		List<JobKey> jkList = new ArrayList<JobKey>();
		GroupMatcher<JobKey> gm = GroupMatcher.jobGroupEquals(group);
		if (gm != null) {
			try {
				for (JobKey jobKey : sch.getJobKeys(gm)) {
					jkList.add(jobKey);
				}
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jkList;
	}
    
	/**
	 * 删除指定任务组任务
	 * @param sch
	 * @param group
	 * @throws SchedulerException 
	 */
	public static void delJob(Scheduler sch, String group) throws SchedulerException {
		List<JobKey> jkList = getTaskJob(sch, group);
	    sch.deleteJobs(jkList);
	}
	
	
	/** 
	 * 判断是否为cron表达式
	 * @param cronRegx
	 */
	public static boolean isCronRegx(String cronRegx){
	    if(StringUtils.isEmpty(cronRegx)){
	    	return false;
	    }
	    return true;
	}

}
