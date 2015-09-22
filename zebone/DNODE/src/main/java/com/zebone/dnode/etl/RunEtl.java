package com.zebone.dnode.etl;


import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 
 * @author 陈阵 
 * @date 2014-2-19 上午10:17:02
 */
public class RunEtl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] xml = new String[]{"com/zebone/dnode/etl/ApplicationContext.xml",
				"/com/zebone/dnode/etl/check/config/ApplicationContext.xml",
				"/com/zebone/dnode/etl/convert/config/ApplicationContext.xml",
				"/com/zebone/dnode/etl/load/config/ApplicationContext.xml",
				"/com/zebone/dnode/etl/adapter/config/ApplicationContext.xml"};
      
		try {
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(xml);
			TaskService taskService = ctx.getBean("taskService",TaskService.class);
			taskService.taskStart();  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.exit(0);
		}
	}
}
