package com.zebone.empi.receive.job;

import com.zebone.empi.receive.service.DocSubJobService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;

/**
 * 文档订阅任务
 *
 * @author 杨英
 * @version 2014-8-13 下午1:59
 */
@DisallowConcurrentExecution
public class DocSubJob implements Job {

    private static final Log log = LogFactory.getLog(DocSubJob.class);

    private DocSubJobService docSubJobService;

    public void setDocSubJobService(DocSubJobService docSubJobService) {
        this.docSubJobService = docSubJobService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            log.info("执行文档订阅任务");
            docSubJobService.docSub();
        }catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage(),e);
        }
    }
    
    
}
