package com.zebone.dnode.engine.analyze;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import com.zebone.dnode.engine.analyze.service.DocumentAnalyzeService;

/**
 * 文档解析任务
 * 
 * @author songjunjie
 * @version 2013-8-17 上午09:52:20
 */
public class DocumentAnalyzeJob implements Job {

	private static final Log log = LogFactory.getLog(DocumentAnalyzeJob.class);

	private DocumentAnalyzeService documentAnalyzeService;

	private String docOrgCode;

    private String analyzeThreads;

    private String threadNo;

	public void setDocumentAnalyzeService(
			DocumentAnalyzeService documentAnalyzeService) {
		this.documentAnalyzeService = documentAnalyzeService;
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

    @Override
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		try {
			if (StringUtils.isNotEmpty(docOrgCode)) {
				documentAnalyzeService.analyze(docOrgCode);
			} else {
				documentAnalyzeService.analyze(analyzeThreads,threadNo);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		} finally {
			try {
				jobExecutionContext.getScheduler().rescheduleJob(
						jobExecutionContext.getTrigger().getKey(),
						jobExecutionContext.getTrigger());
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
			}
		}
	}
}
