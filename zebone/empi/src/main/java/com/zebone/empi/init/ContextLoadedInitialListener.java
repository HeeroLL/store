package com.zebone.empi.init;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.zebone.empi.dao.ResidentCardMapper;
import com.zebone.empi.vo.ResidentCard;

/**
 * 
 * @author charmyin
 * @since 2013-7-8
 *
 */
public class ContextLoadedInitialListener implements ServletContextListener {
	@Resource
	private ResidentCardMapper residentCardMapper;
	Logger logger = Logger.getLogger(ContextLoadedInitialListener.class);
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		List<ResidentCard> list = residentCardMapper.selectByEmpi("aaaaa");
		System.out.println(list.size());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	public ResidentCardMapper getResidentCardMapper() {
		return residentCardMapper;
	}

	public void setResidentCardMapper(ResidentCardMapper residentCardMapper) {
		this.residentCardMapper = residentCardMapper;
	}

}
