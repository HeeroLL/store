package com.zebone.pubsub.server.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zebone.pubsub.server.service.PollService;

public class PubSubPollJob implements Job {
	
	private static final Log log = LogFactory.getLog(PubSubPollJob.class);
	
	private PollService pollService;

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// TODO Auto-generated method stub
		try{
			log.info("执行轮询任务");
			pollService.doPoll();
			//jec.getScheduler().rescheduleJob(jec.getTrigger().getKey(), jec.getTrigger());
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(),e);
		}
	}

	public void setPollService(PollService pollService) {
		this.pollService = pollService;
	}
	
	
}
