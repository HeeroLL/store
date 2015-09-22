package com.zebone.empi.receive;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.empi.receive.service.WmqEmpiMsgConsumer;


/**
 * @author 杨英
 * @version 2014-7-14 下午4:08
 */
public class ProductEmpiSubRun {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("file:config/empiSubApplicationContext.xml");
        WmqEmpiMsgConsumer empiMsgConsumer = cpxa.getBean("wmqEmpiMsgConsumer", WmqEmpiMsgConsumer.class);
        empiMsgConsumer.start();
    }
}
