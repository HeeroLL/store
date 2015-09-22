package com.zebone.dip.etl.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.zebone.dip.etl.vo.NodeAndJobRule;

@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class NodeAndJobRuleMapperTest extends AbstractJUnit4SpringContextTests{

	protected ApplicationContext context;
	@Resource
	private NodeAndJobRuleMapper nodeAndJobRuleMapper;

	@Test
	public void testInsert(){
		NodeAndJobRule nodeAndJobRule = new NodeAndJobRule();
		nodeAndJobRule.setNodeId("1");
		nodeAndJobRule.setJobId(1);
		nodeAndJobRule.setCreateTime(new Date());
		nodeAndJobRuleMapper.insert(nodeAndJobRule);
	}
	
	@Test
	public void testGetTransNameByNodeId(){
		List<String> list = nodeAndJobRuleMapper.getJobNameByNodeId("1");
		for(String name : list){
			System.out.println(name);
		}
	}
	
	@Test
	public void testDelete(){
		nodeAndJobRuleMapper.delete("1", 1);
	}

}
