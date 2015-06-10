package com.zebone.dnode.etl.extract.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zebone.dnode.etl.extract.pojo.ExtractConfig;
import com.zebone.dnode.etl.extract.service.ExtractService;




public class ExtractJob implements Job {

	private static final Log log = LogFactory.getLog(ExtractJob.class);

    private ExtractService extractService;
    
    private ExtractConfig extractConfig;
    
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		// TODO Auto-generated method stub
		log.info("######抽取数据任务开始######");
		try{
			extractService.extractDataInDB(extractConfig);
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(),e);
		}
		log.info("######抽取数据任务结束######");
	}

	public void setExtractService(ExtractService extractService) {
		this.extractService = extractService;
	}

	public void setExtractConfig(ExtractConfig extractConfig) {
		this.extractConfig = extractConfig;
	}



    
}
