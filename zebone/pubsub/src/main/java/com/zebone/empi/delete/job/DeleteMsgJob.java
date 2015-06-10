package com.zebone.empi.delete.job;

import com.zebone.empi.delete.service.DeleteMsgService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 删除推送信息的定时任务
 * @author 杨英
 * @version 2014-7-15 上午8:40
 */
public class DeleteMsgJob implements Job {

    private static final Log log = LogFactory.getLog(DeleteMsgJob.class);

    private DeleteMsgService deleteMsgService;

    public void setDeleteMsgService(DeleteMsgService deleteMsgService) {
        this.deleteMsgService = deleteMsgService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            log.info("开始删除推送信息");
            deleteMsgService.deleteMsg();
        }catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
