package com.zebone.dnode.etl.extract.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestExtract {

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2013-11-20 上午9:18:40 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("classpath:com/zebone/dnode/etl/extract/config/ApplicationContext.xml");
		cx.close();
	}

}
