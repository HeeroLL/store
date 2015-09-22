package com.zebone.dnode.task.service.impl;

import java.util.List;

import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.clean.service.CleanService;
import com.zebone.dnode.task.dto.Task;
import com.zebone.dnode.task.enums.TaskOp;
import com.zebone.dnode.task.job.CleanJob;
import com.zebone.dnode.task.service.TaskService;
import com.zebone.dnode.task.util.DNJobKey;

/**
 * 清洗任务
 * 
 * @author cz
 * 
 */
@Service("cleanTaskService")
public class CleanTaskServiceImpl extends BaseTaskService implements TaskService {

	private static final Logger logger = LoggerFactory.getLogger(CleanTaskServiceImpl.class);
    
	/** 清洗service **/
	@Autowired
	private CleanService cleanService;
	
	
	@Override
	public void deleteTask(Task task) {
		// TODO Auto-generated method stub
		super.delTask(task);
	}

	@Override
	public void deleteTasks(List<Task> tasks) {
		// TODO Auto-generated method stub
		super.delTasks(tasks);
	}

	@Override
	public void executeTask(Task task, TaskOp op) {
		// TODO Auto-generated method stub
		Class<CleanJob> jobClass = CleanJob.class;
		JobDataMap jdm = getJobMapPara(task);
		switch (op) {
		case DELETE:
			deleteTask(task);
			break;
		case ADD:
			addTask(task, jdm, jobClass);
			break;
		case UPDATE:
			updateTask(task);
			break;
		}
	}

	private JobDataMap getJobMapPara(Task task) {
		JobDataMap jdm = new JobDataMap();
		jdm.put(DNJobKey.TASK, task);
		jdm.put(DNJobKey.CENTER_TASK_SERVICE, centerTaskService);
		jdm.put(DNJobKey.CLEAN_TASK_SERVICE, cleanService);
		return jdm;
	}

}
