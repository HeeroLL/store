package com.zebone.dnode.engine.store.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/zebone/dnode/engine/store/config/ApplicationContext.xml"})
public class DocumentStoreServiceTest {

	@Resource
	private DocumentStoreService documentStoreService;
	
	@Test
	public void storeTest(){
		documentStoreService.store("1", "1");
	}

}
