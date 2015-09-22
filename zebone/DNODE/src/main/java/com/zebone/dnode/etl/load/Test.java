package com.zebone.dnode.etl.load;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.zebone.dnode.etl.load.pojo.LoadConfig;
import com.zebone.dnode.etl.load.service.LoadService;

public class Test {

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2014-2-24 上午9:20:27 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("classpath:com/zebone/dnode/etl/load/config/ApplicationContext.xml");
			LoadService ls = cx.getBean("loadService",LoadService.class);
			
			ClassPathResource loadConfCpr = new ClassPathResource("com/zebone/dnode/etl/load/config/loadConf.xml");
			XStream xs = new XStream(new StaxDriver());
			xs.processAnnotations(LoadConfig.class);
			LoadConfig loadData = (LoadConfig) xs.fromXML(loadConfCpr.getInputStream());
			ls.loadData(loadData);
			cx.close();
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
