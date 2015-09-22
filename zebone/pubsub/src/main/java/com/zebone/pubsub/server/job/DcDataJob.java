package com.zebone.pubsub.server.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

import com.zebone.pubsub.server.pojo.DcDataJobParameter;
import com.zebone.pubsub.server.service.PubSubService;

public class DcDataJob implements Job {
	
	private static final Log log = LogFactory.getLog(DcDataJob.class);
	
	private DcDataJobParameter ddjp;
	
    private PubSubService<DcDataJobParameter> pubSubService;
    

	@Override
	public void execute(JobExecutionContext paramJobExecutionContext)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try{
			JobDetail jd = paramJobExecutionContext.getJobDetail();
			Trigger trigger =   paramJobExecutionContext.getTrigger();
			log.info("执行文档订阅发布任务名称：" + jd.getKey().getName() + "_" + trigger.getKey().getName());
			pubSubService.doPub(ddjp);
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(),e);
		}
	
		
	}

	

	public void setDdjp(DcDataJobParameter ddjp) {
		this.ddjp = ddjp;
	}


	public void setPubSubService(PubSubService<DcDataJobParameter> pubSubService) {
		this.pubSubService = pubSubService;
	}



	
}
