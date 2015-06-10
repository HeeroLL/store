package com.zebone.dnode.engine.analyze.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zebone.dnode.engine.analyze.vo.DocumentInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/zebone/dnode/engine/analyze/config/ApplicationContext.xml"})
public class DocStorageMapperTest {
	@Resource
	private DocAnalyzedMapper docAnalyzedMapper;
	
	@Test
	public void getNoAnalyzedDocTest(){
		List<DocumentInfo> list = docAnalyzedMapper.getNoAnalyzedDoc(10,1,1);
		for(DocumentInfo doc : list){
			String xml = doc.getDocxml();
			System.out.println(xml);
		}
	}
	
	@Test
	public void updateParseStateTest(){
		docAnalyzedMapper.updateParseState("b2331facc1b449ee87e8ee1a61bcc79e", "0");
	}
}
