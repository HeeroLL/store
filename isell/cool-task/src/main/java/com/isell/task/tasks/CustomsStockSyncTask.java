package com.isell.task.tasks;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.isell.task.product.service.TaskProductService;

/**
 * 保税仓库存同步 Task
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-24]
 */
@Component("customsStockSyncTask")
public class CustomsStockSyncTask {
	
	/**
     * log
     */
    private static final Logger log = Logger.getLogger(CustomsStockSyncTask.class);
    
    /**
     * 商品接口
     */
    @Resource
    private TaskProductService taskProductService;
    
    /**
     * 定时任务到达执行的方法
     */
    public void execute() {
        log.debug("in CustomsStockSyncTask");
        //宁波优贝库存同步
        taskProductService.getNbybStock();
        log.debug("out CustomsStockSyncTask");
    }

}
