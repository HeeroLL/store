package com.zebone.dnode.task.job;

import com.zebone.dnode.task.util.ActiveMqManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 解析xml文件类
 * @author cz
 *
 */
public class ParseQueueJob implements Job {
	
	private static final Logger logger = LoggerFactory.getLogger(ParseQueueJob.class);

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// TODO Auto-generated method stub
		String local = "tcp://localhost:16116";
		String qcu = "qcu1";
		String queName = "lq";
		String msg = new ActiveMqManager(local).getMessage(queName);
	    System.out.println(msg);
	    rescheduleJob(jec);
	}
	
	
	private void rescheduleJob(JobExecutionContext jec){
		try {
			jec.getScheduler().rescheduleJob(jec.getTrigger().getKey(), jec.getTrigger());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}

	}

}
