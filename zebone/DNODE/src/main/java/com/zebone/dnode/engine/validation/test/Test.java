package com.zebone.dnode.engine.validation.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.dnode.engine.validation.ValidationTaskInit;
import com.zebone.dnode.engine.validation.domain.CheckConfig;
import com.zebone.dnode.engine.validation.service.CheckService;


public class Test {
	
	
	public void auto(){
		ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("classpath:/com/zebone/dnode/engine/validation/config/ApplicationContext.xml");
		ValidationTaskInit validationTaskInit = cpxa.getBean("validationTaskInit",ValidationTaskInit.class);
		validationTaskInit.init();
	}
	
	public void man(){
		try {
			String xmlData = FileUtils.readFileToString(new File("C:\\Users\\cz\\Desktop\\就诊信息.xml"));
			ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("classpath:/com/zebone/dnode/engine/validation/config/ApplicationContext.xml");
			CheckService checkService = cpxa.getBean("checkService",CheckService.class);
			checkService.check(new CheckConfig(), xmlData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2013-7-29 下午4:02:09 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    new Test().man();
	}

}
