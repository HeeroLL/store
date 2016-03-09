package com.isell.task.tasks;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.isell.task.order.service.TaskOrderService;

/**
 * Pinduoduo Task
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-09]
 */
@Component("pinduoduoTask")
public class PinduoduoTask {
	
	/**
     * log
     */
    private static final Logger log = Logger.getLogger(PinduoduoTask.class);
    
    /**
     * 订单服务接口
     */
    @Resource
    private TaskOrderService taskOrderService;
    
    /**
     * 定时任务到达执行的方法
     */
    public void execute() {
        log.debug("in PinduoduoTask");
        try {
            taskOrderService.downloadPinduoduoOrder();
        } catch (Exception e) {
            log.error(e);
        }
        log.debug("out OrderTask");
    }
}
