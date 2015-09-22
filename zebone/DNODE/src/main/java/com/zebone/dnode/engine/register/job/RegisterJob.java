package com.zebone.dnode.engine.register.job;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import com.zebone.dnode.engine.register.service.RegisterService;



/**
 * 注册job
 * @author 陈阵 
 * @date 2013-8-18 下午1:26:16
 */
public class RegisterJob implements Job {
	
	private static final Log logger  =  LogFactory.getLog(RegisterJob.class);
	
	
	private RegisterService registerService;
	
	private String docOrgCode;
	
	private String analyzeThreads;

    private String threadNo;
	

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			if(StringUtils.isNotEmpty(docOrgCode)){
				registerService.register(docOrgCode);
			}else{
				registerService.register(analyzeThreads,threadNo);

			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}finally {
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


	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}


	public void setDocOrgCode(String docOrgCode) {
		this.docOrgCode = docOrgCode;
	}
	
	public void setAnalyzeThreads(String analyzeThreads) {
        this.analyzeThreads = analyzeThreads;
    }

    public void setThreadNo(String threadNo) {
        this.threadNo = threadNo;
    }


}
