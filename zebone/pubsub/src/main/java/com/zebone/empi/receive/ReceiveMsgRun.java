package com.zebone.empi.receive;

import com.zebone.empi.receive.job.DocSubJobStarter;
import com.zebone.empi.receive.service.DocSubConsumer;
import com.zebone.empi.receive.service.DocSubJobService;
import com.zebone.empi.receive.service.EmpiMsgConsumer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 杨英
 * @version 2014-7-14 下午4:08
 */
public class ReceiveMsgRun {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("file:config/ReApplicationContext.xml");

        EmpiMsgConsumer empiMsgConsumer = cpxa.getBean("empiMsgConsumer", EmpiMsgConsumer.class);
        empiMsgConsumer.start();

        DocSubConsumer docSubConsumer = cpxa.getBean("docSubConsumer", DocSubConsumer.class);
        docSubConsumer.start();

        DocSubJobStarter docSubJobStarter = cpxa.getBean("docSubJobStarter", DocSubJobStarter.class);
        docSubJobStarter.startJob();
    }
}
