package com.zebone.pubsub.server;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.pubsub.server.service.PollServiceInit;
import com.zebone.pubsub.server.service.PubSubInitService;

public class RunServer {

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2014-5-13 上午9:20:21 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("file:config/sApplicationContext.xml");
		PubSubInitService psis = cpxa.getBean("basePubSubServiceInit",PubSubInitService.class);
		psis.init();
		PollServiceInit psi =  cpxa.getBean("pollServiceInit",PollServiceInit.class);
		psi.init();
		
	}

}
