package com.zebone.dnode.engine.empi;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.dnode.engine.empi.service.DispatchService;


/**
 * 程序主入口
 * @author YinCM
 *
 */
public class Entrance {
	
	 
	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:com/zebone/dnode/engine/empi/config/ApplicationContext.xml");
		
		DispatchService dispatchService = (DispatchService)ctx.getBean("dispatchService");
		
		dispatchService.doDispatch();
	}

	 
	
}
