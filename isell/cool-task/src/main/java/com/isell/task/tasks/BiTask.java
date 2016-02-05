package com.isell.task.tasks;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.isell.task.bi.service.BiService;

/**
 * BI Task
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-09]
 */
@Component("biTask")
public class BiTask {
	
	/**
     * log
     */
    private static final Logger log = Logger.getLogger(OrderTask.class);
    
    /**
     * BI servicre
     */
    @Resource
    private BiService biService;
    
    /**
     * 定时任务到达执行的方法
     */
    public void execute() {
        log.debug("in BiTask");
        biService.setBiDate(); //时间维表每天增加一条记录
        //biService.etlCoonShop(); //酷店统计表定时抽取
        log.debug("out BiTask");
    }

}
