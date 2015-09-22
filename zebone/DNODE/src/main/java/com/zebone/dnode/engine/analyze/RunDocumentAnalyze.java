package com.zebone.dnode.engine.analyze;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunDocumentAnalyze {

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2014-1-2 下午4:11:20 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("classpath:com/zebone/dnode/engine/analyze/config/ApplicationContext.xml");
		DocumentAnalyzeJobStarter documentAnalyzeJobStarter = cpxa.getBean("documentAnalyzeJobStarter",DocumentAnalyzeJobStarter.class);
		documentAnalyzeJobStarter.startJob();
	}

}
