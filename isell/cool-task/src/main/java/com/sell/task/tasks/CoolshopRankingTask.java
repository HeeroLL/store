package com.sell.task.tasks;

import org.springframework.stereotype.Component;

/**
 * 酷店排名统计Task
 * 
 * @author lilin
 * @version [版本号, 2015年7月30日]
 */
@Component("coolshopRankingTask")
public class CoolshopRankingTask {
    
    /**
     * 定时任务到达执行的方法
     */
    public void execute() {
        System.out.println("in CoolshopRankingTask");
    }
}
