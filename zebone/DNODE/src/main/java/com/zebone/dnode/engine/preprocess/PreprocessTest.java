package com.zebone.dnode.engine.preprocess;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.dnode.engine.preprocess.service.GeneralInfoService;
import com.zebone.dnode.engine.preprocess.service.RecentlyInfoService;

public class PreprocessTest {

    public static void main(String[] args){
        System.out.println("预处理开始------");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:com/zebone/dnode/engine/preprocess/config/ApplicationContext.xml");
        GeneralInfoService dss = ctx.getBean("generalInfoService", GeneralInfoService.class);
        RecentlyInfoService rss = ctx.getBean("recentlyInfoService", RecentlyInfoService.class);
        dss.preprocessDmCurveInfo();
    }
}
