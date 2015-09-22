package com.zebone.dip.etl.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.zebone.dip.etl.dao.NodeAndTransRuleMapper;
import com.zebone.dip.etl.vo.NodeAndTransRule;

@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class NodeAndTransRuleMapperTest extends AbstractJUnit4SpringContextTests{

	protected ApplicationContext context;
	@Resource
	private NodeAndTransRuleMapper nodeAndTransRuleMapper;

	@Test
	public void testInsert(){
		NodeAndTransRule nodeAndTransRule = new NodeAndTransRule();
		nodeAndTransRule.setNodeId("1");
		nodeAndTransRule.setTransId(1);
		nodeAndTransRule.setCreateTime(new Date());
		nodeAndTransRuleMapper.insert(nodeAndTransRule);
	}
	
	@Test
	public void testGetTransNameByNodeId(){
		List<String> list = nodeAndTransRuleMapper.getTransNameByNodeId("1");
		for(String name : list){
			System.out.println(name);
		}
	}
	
	@Test
	public void testDelete(){
		nodeAndTransRuleMapper.delete("1", 1);
	}
	
	@Test
	public void getTansIdByName(){
		List<Integer> list = nodeAndTransRuleMapper.getTansIdByName("test1");
		System.out.println(list.size());
	}

}
