package com.zebone.dnode.engine.empi.service;



import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/zebone/dnode/engine/empi/config/ApplicationContext.xml"})
public class EmpiCardAggregateServiceTests {
	 
	@Resource
	private EmpiCardAggregateService empiCardAggregateService;
	
	
	@Test
	public void testCardAggre(){
		empiCardAggregateService.doMedicalCardAggregate();
	}
	
}
