package com.zebone.dnode.task.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebone.dnode.engine.clean.service.CleanService;
import com.zebone.dnode.task.dto.Task;
import com.zebone.dnode.task.service.CenterTaskService;

public class CleanJob implements Job {

	private static final Logger logger = LoggerFactory.getLogger(EtlJob.class);

	private CenterTaskService centerTaskService;
	
	/** 清洗服务 **/
	private CleanService cleanTaskService;

	private Task task;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		cleanTaskService.exeucte(task.getId());
		centerTaskService.updateCenterTaskExecuteStatus("1", task.getId());
		logger.info("###### do clean job finish");

	}

	public void setCenterTaskService(CenterTaskService centerTaskService) {
		this.centerTaskService = centerTaskService;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public void setCleanTaskService(CleanService cleanTaskService) {
		this.cleanTaskService = cleanTaskService;
	}

	
	
}
