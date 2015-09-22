package com.zebone.dnode.engine.notice.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebone.notice.service.NoticeService;

/**
 * 
 * 公告通知状态更新任务
 * 
 * @author lilin
 * @version [版本号, 2015年5月26日]
 */
public class NoticeJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(NoticeJob.class);

    /**
     * 通知服务接口
     */
    private NoticeService noticeService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            noticeService.updateStatus();
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
        }
    }

    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
}
