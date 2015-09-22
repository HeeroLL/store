package com.zebone.dnode.engine.store.job;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import com.zebone.dnode.engine.store.service.DocumentStoreService;

/**
 * 存储job
 * 
 * @author 陈阵
 * @date 2013-8-16 上午10:21:17
 */
public class StorageJob implements Job {

	private static final Log logger = LogFactory.getLog(StorageJob.class);

	private DocumentStoreService documentStoreService;
	
	private String analyzeThreads;

	private String threadNo;

	private String docOrgCode;

	@Override
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			if (StringUtils.isNotEmpty(docOrgCode)) {
				documentStoreService.store(docOrgCode);
			} else {
				documentStoreService.store(analyzeThreads,threadNo);

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

	public void setDocumentStoreService(
			DocumentStoreService documentStoreService) {
		this.documentStoreService = documentStoreService;
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
