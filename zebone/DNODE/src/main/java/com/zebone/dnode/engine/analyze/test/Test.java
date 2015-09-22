package com.zebone.dnode.engine.analyze.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.dnode.engine.analyze.service.DocumentAnalyzeServiceImpl;


public class Test {
	
	

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2013-7-29 下午4:02:09 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("classpath:com/zebone/dnode/engine/analyze/config/ApplicationContext.xml");
			DocumentAnalyzeServiceImpl dasi = cpxa.getBean("documentAnalyzeServiceImpl",DocumentAnalyzeServiceImpl.class);
			String file = FileUtils.readFileToString(new File("c:\\test_健康档案.xml"));
			dasi.analyze("12122", "sdfkl22323", "1", file);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
