package com.zebone.dnode.engine.clean;

import org.apache.commons.lang3.ClassUtils;

import com.zebone.dnode.engine.clean.exception.CleanException;
import com.zebone.dnode.engine.clean.exception.ErrorUtil;

/**
 * 业务校验解析
 * @author cz
 *
 */
public class BusinessParse {
	
	private static final String BUSINESS_PACKAGE = "com.zebone.dnode.engine.clean.";
    
	public void parse(String checkClass, Object value) throws CleanException{
		try {
			BusinessChecking bc = (BusinessChecking)ClassUtils.getClass(BUSINESS_PACKAGE + checkClass).newInstance();
			bc.checking(value);
		}catch(CleanException e){
		    throw e;	
		}catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new CleanException(ErrorUtil.SYSTEM_ERROR, e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new CleanException(ErrorUtil.SYSTEM_ERROR, e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new CleanException(ErrorUtil.SYSTEM_ERROR, e);
		} 
	}

}
