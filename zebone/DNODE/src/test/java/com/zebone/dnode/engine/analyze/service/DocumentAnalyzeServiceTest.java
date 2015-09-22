package com.zebone.dnode.engine.analyze.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/zebone/dnode/engine/analyze/config/ApplicationContext.xml"})
public class DocumentAnalyzeServiceTest {
	private final static String DOC_FILE = "e:\\健康档案.xml"; 
	@Resource
	private DocumentAnalyzeService documentAnalyzeService;

	@Test
	public void analyzeTest() throws IOException{
		documentAnalyzeService.analyze("1","1");
	}
	  
	@Test
	public void analyzeTest2() throws IOException{
		File docFile = new File(DOC_FILE);
		String docId = "1";
		String parseState = "0";
		String docxml = FileUtils.readFileToString(docFile, "UTF-8");
		documentAnalyzeService.analyze(docId,"123456" , parseState, docxml);
	}
	
}
