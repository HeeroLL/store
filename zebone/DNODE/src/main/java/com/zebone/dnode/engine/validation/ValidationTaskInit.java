package com.zebone.dnode.engine.validation;


import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.validation.job.ValidationJob;
import com.zebone.dnode.engine.validation.service.CheckService;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;

/**
 * 启动校验引擎工具类
 * @author 陈阵 
 * @date 2013-8-16 上午10:26:43
 */
@Service("validationTaskInit")
public class ValidationTaskInit {
	
	private static final Log log = LogFactory.getLog(ValidationTaskInit.class);
	
	@Value("${validation_threads}")
	private int validationThreads;
	
	@Resource
	private CheckService checkService;
	

        
    @Value("${iswmq}")
	private String isWmq;
	
	@Value("${wmq.host}")
	private String wmqHost;
	
	@Value("${wmq.port}")
	private int wmqPort;
	
	@Value("${wmq.channel}")
	private String wmqChannel;
	
	@Value("${wmq.queuemanager}")
	private String wmqQueueManager;
	
	@Value("${wmq.transporttype}")
	private String wmqTransportType;
    
	@Value("${wmq.queue}")
	private String wmqQueue;


	public void init(){
		log.info("######校验引擎启动######"+validationThreads);
		QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
		for(int i = 0; i < validationThreads; i++){
			JobParameter jobParameter = new JobParameter();
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("checkService", checkService);
			jobDataMap.put("isWmq", isWmq);
			jobDataMap.put("wmqHost", wmqHost);
			jobDataMap.put("wmqPort", wmqPort);
			jobDataMap.put("wmqChannel", wmqChannel);
			jobDataMap.put("wmqQueueManager", wmqQueueManager);
			jobDataMap.put("wmqTransportType", wmqTransportType);
			jobDataMap.put("wmqQueue", wmqQueue);
			
			jobParameter.setJobClass(ValidationJob.class);
			
			jobParameter.setJobKey(JobKey.jobKey(String.valueOf(i), "jk_validation_task"));
			jobParameter.setTriggerKey(TriggerKey.triggerKey(String.valueOf(i),"tr_validation_task"));
			jobParameter.setJobDataMap(jobDataMap);
			
			quartzManager.addTask(jobParameter);
		} 
	}
}
