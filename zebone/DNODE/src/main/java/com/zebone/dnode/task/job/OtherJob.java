package com.zebone.dnode.task.job;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebone.dnode.task.dto.Task;
import com.zebone.dnode.task.service.CenterTaskService;

public class OtherJob implements Job {
     
    private static final Logger logger = LoggerFactory.getLogger(OtherJob.class);

	private CenterTaskService centerTaskService;
	
	private Task task;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		centerTaskService.updateCenterTaskExecuteStatus("1", task.getId());
		logger.info("###### do other job sucess");
	}

	public void setCenterTaskService(CenterTaskService centerTaskService) {
		this.centerTaskService = centerTaskService;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	

}
