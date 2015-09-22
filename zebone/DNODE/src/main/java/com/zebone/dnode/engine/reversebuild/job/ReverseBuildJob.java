package com.zebone.dnode.engine.reversebuild.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Value;

import com.zebone.dnode.engine.reversebuild.service.ReverseBuild;
import com.zebone.dnode.task.util.ActiveMqManager;

/**
 * 校验job
 * 
 * @author 陈阵
 * @date 2013-8-16 上午10:21:17
 */

public class ReverseBuildJob implements Job {
	
	private static final Log logger = LogFactory.getLog(ReverseBuildJob.class);
	
	@Value("${brokerURL}")
	private String brokerURL;

	@Value("${reverseBuildQueue}")
	private String reverseBuildQueue;
	
	private ReverseBuild reverseBuild;


	@Override
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		String jsonData = null;
		try {
			ActiveMqManager mqManager = new ActiveMqManager(brokerURL);
			jsonData = mqManager.getMessage(reverseBuildQueue);
			reverseBuild.build(jsonData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		} finally {
			try {
				jobExecutionContext.getScheduler().rescheduleJob(
						jobExecutionContext.getTrigger().getKey(),
						jobExecutionContext.getTrigger());
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
			}
		}

	}


	public void setReverseBuild(ReverseBuild reverseBuild) {
		this.reverseBuild = reverseBuild;
	}

 
}
