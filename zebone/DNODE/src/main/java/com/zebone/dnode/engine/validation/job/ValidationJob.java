package com.zebone.dnode.engine.validation.job;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Value;

import com.zebone.dnode.engine.validation.domain.CheckConfig;
import com.zebone.dnode.engine.validation.service.CheckService;
import com.zebone.dnode.task.util.ActiveMqManager;

/**
 * 校验job
 * 
 * @author 陈阵
 * @date 2013-8-16 上午10:21:17
 */

public class ValidationJob implements Job {

	private static final Log logger = LogFactory.getLog(ValidationJob.class);

	/** 校验服务 **/
	private CheckService checkService;
	
	@Value("${brokerURL}")
	private String brokerURL;
	
	@Value("${checkQueue}")
	private String checkQueue;


	@Override
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		String xmlData = null;
		try {
			ActiveMqManager mqManager = new ActiveMqManager(brokerURL);
			xmlData = mqManager.getMessage(checkQueue);
			if (StringUtils.isNotEmpty(xmlData)) {
				CheckConfig checkConfig = new CheckConfig();
				checkConfig.setIsRepeat("0");
				checkConfig.setIsSelect("0");
				checkConfig.setIsBusinessFormat("0");
				checkConfig.setIsValue("0");
				checkConfig.setIsDataFormat("0");
				checkConfig.setIsOnly("0");
				checkService.check(checkConfig, xmlData);
			} 
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

	public void setCheckService(CheckService checkService) {
		this.checkService = checkService;
	}



}
