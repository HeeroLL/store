package com.zebone.dnode.engine.store.test;

/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * Administrator              New             Aug 14, 2013
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.dnode.engine.store.StorageTaskInit;



public class TestDocStorage {

	/**
	 * @author caixl
	 * @date Aug 14, 2013
	 * @description TODO 测试存储引擎
	 * @param args void
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("classpath:com/zebone/dnode/engine/store/config/ApplicationContext.xml");
		StorageTaskInit storageTaskInit = cx.getBean("storageTaskInit",StorageTaskInit.class);
		storageTaskInit.init();
	}

}
