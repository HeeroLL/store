package com.zebone.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 在系统启动是将spring ApplicationContext保存起来，以便不被spring管理
 * 的类使用ApplicationContext。 
 * @author songjunjie
 * @version 2013-8-24 上午10:09:42
 */

public class SpringAppContext implements ApplicationContextAware {
	private static ApplicationContext context = null;
	
	public SpringAppContext(){
	}
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringAppContext.context = context;
	}
	
	/**
	 * 得到系统中的 ApplicationContext
	 * @return
	 */
	public static ApplicationContext getApplicationContext(){
		return context;
	}
}