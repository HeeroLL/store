package com.zebone.pubsub.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.pubsub.client.service.PubSubMsgConsumer;

public class RunClient {

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2014-5-12 下午2:35:25 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("classpath:cApplicationContext.xml");
		PubSubMsgConsumer psmc = cpxa.getBean("pubSubMsgConsumer",PubSubMsgConsumer.class);
		psmc.start();
	}

}
