package com.isell.task.tasks;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.isell.task.shop.service.ShopRankingService;

/**
 * 酷店排名统计Task
 * 
 * @author lilin
 * @version [版本号, 2015年7月30日]
 */
@Component("coolshopRankingTask")
public class CoolshopRankingTask {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(CoolshopRankingTask.class);
    
    /**
     * 酷店排名服务接口
     */
    @Resource
    private ShopRankingService shopRankingService;
    /**
     * 定时任务到达执行的方法
     */
    public void execute() {
        log.debug("in CoolshopRankingTask");
        shopRankingService.batchSave();
        log.debug("out CoolshopRankingTask");
    }
}
