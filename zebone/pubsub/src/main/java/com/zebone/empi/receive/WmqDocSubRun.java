package com.zebone.empi.receive;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.empi.receive.job.DocSubJobStarter;
import com.zebone.empi.receive.service.WmqDocSubConsumer;

public class WmqDocSubRun {
	public static void main(String[] args) {
        ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("classpath:ReApplicationContext.xml");
        
        /**
        WmqEmpiMsgConsumer empiMsgConsumer = cpxa.getBean("wmqEmpiMsgConsumer", WmqEmpiMsgConsumer.class);
        empiMsgConsumer.start();
        **/
        
        DocSubJobStarter docSubJobStarter = cpxa.getBean("docSubJobStarter", DocSubJobStarter.class);
        
        docSubJobStarter.startJob();
        

        WmqDocSubConsumer docSubConsumer = cpxa.getBean("wmqDocSubConsumer", WmqDocSubConsumer.class);
        docSubConsumer.start();
     
        
        
      

    }
}
