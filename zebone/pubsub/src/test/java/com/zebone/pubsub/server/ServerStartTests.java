package com.zebone.pubsub.server;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zebone.pubsub.server.service.PubSubInitService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:sApplicationContext.xml")
public class ServerStartTests {
	
	@Resource
	private PubSubInitService pubSubInitService;
	
	@Test
	public void  testPara(){
		System.out.println("server start");
		pubSubInitService.init();
	}
}
