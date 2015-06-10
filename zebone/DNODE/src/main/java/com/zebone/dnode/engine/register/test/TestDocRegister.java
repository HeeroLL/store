package com.zebone.dnode.engine.register.test;

/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * Administrator              New             Aug 18, 2013
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.dnode.engine.register.RegisterTaskInit;
import com.zebone.dnode.engine.register.service.RegisterService;


public class TestDocRegister {

	/**
	 * @author Administrator
	 * @date Aug 18, 2013
	 * @description TODO
	 * @param args void
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("classpath:com/zebone/dnode/engine/register/config/ApplicationContext.xml");
		RegisterTaskInit registerTaskInit = cx.getBean("registerTaskInit",RegisterTaskInit.class);
		registerTaskInit.init();
	}

}
