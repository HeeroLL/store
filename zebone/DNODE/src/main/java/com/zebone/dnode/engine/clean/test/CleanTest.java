package com.zebone.dnode.engine.clean.test;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.dnode.engine.clean.service.CleanService;

public class CleanTest {
    

	
	public void testClean(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:com/zebone/datacenter/engine/clean/config/Clean_ApplicationContext.xml");
		CleanService cs = (CleanService)ctx.getBean("cleanService");
	    cs.exeucte("39e18541e93f416085af919150240903");
		System.out.println(cs);
	}
	

    public void testClass(){
    	Class<?> t = int.class;
    	System.out.println(ClassUtils.getShortClassName(t));
    }
    
    
    public static void main(String[] args){
    	new CleanTest().testClean();
    }

}
