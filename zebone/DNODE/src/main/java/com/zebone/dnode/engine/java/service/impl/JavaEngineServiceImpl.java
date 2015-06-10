package com.zebone.dnode.engine.java.service.impl;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Service;


import com.zebone.dnode.engine.java.execute.JavaEngineExecute;
import com.zebone.dnode.engine.java.service.JavaEngineService;

@Service("javaEngineService")
public class JavaEngineServiceImpl implements JavaEngineService {
	
    private static final Log logger = LogFactory.getLog(JavaEngineServiceImpl.class);

	@Override
	public void execute(String className) {
		// TODO Auto-generated method stub
		try {
			JavaEngineExecute clz = (JavaEngineExecute) ClassUtils.getClass(className).newInstance();
			clz.execute();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
	}
}
