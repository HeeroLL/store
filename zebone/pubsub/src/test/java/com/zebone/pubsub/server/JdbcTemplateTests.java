package com.zebone.pubsub.server;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:ApplicationContext.xml")
public class JdbcTemplateTests {
	
	@Resource(name="jdbcTemplateWRC")
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void testWrc(){
		jdbcTemplate.execute("select * from dual");
		System.out.println("do ok");
	}
}
