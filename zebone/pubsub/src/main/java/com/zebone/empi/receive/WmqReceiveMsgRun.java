package com.zebone.empi.receive;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.empi.receive.job.DocSubJobStarter;
import com.zebone.empi.receive.service.WmqDocSubConsumer;
import com.zebone.empi.receive.service.WmqEmpiMsgConsumer;

/**
 * @author 杨英
 * @version 2014-7-14 下午4:08
 */
public class WmqReceiveMsgRun {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("classpath:ReApplicationContext.xml");

        WmqEmpiMsgConsumer empiMsgConsumer = cpxa.getBean("wmqEmpiMsgConsumer", WmqEmpiMsgConsumer.class);
        empiMsgConsumer.start();
        
       /**
        WmqDocSubConsumer docSubConsumer = cpxa.getBean("wmqDocSubConsumer", WmqDocSubConsumer.class);
        docSubConsumer.start();
        
        DocSubJobStarter docSubJobStarter = cpxa.getBean("docSubJobStarter", DocSubJobStarter.class);
        docSubJobStarter.startJob();
        **/

    }
}
