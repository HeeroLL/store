package com.isell.task.tasks;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.isell.task.logistic.service.LogisticTaskService;

/**
 * 物流任务Task
 * 
 * @author lilin
 * @version [版本号, 2015年11月3日]
 */
@Component("logisticTask")
public class LogisticTask {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(LogisticTask.class);
    
    /**
     * 物流服务接口
     */
    @Resource
    private LogisticTaskService logisticTaskService;
    
    /**
     * 定时任务到达执行的方法
     */
    public void execute() {
        log.debug("in LogisticTask");
        logisticTaskService.updateLogistic();
        log.debug("out LogisticTask");
    }
}
