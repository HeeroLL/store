package com.zebone.empi.receive;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.empi.receive.job.DocSubJobStarter;
import com.zebone.empi.receive.service.WmqDocSubConsumer;


/**
 * @author 杨英
 * @version 2014-7-14 下午4:08
 */
public class ProductDocPubSubRun {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("file:config/docPubSubApplicationContext.xml");

        
        DocSubJobStarter docSubJobStarter = cpxa.getBean("docSubJobStarter", DocSubJobStarter.class);       
        docSubJobStarter.startJob();
        

        WmqDocSubConsumer docSubConsumer = cpxa.getBean("wmqDocSubConsumer", WmqDocSubConsumer.class);
        docSubConsumer.start();
     
        
        
      

    }
}
