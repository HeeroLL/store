package com.sell.task.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 定时任务初始化类
 * 
 * @author lilin
 * @version [版本号, 2015年7月30日]
 */
public class TaskMain {
    
    /**
     * main
     * 
     * @param args args
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // 加载spring
        new ClassPathXmlApplicationContext("SpringContext-start.xml");
    }
}
