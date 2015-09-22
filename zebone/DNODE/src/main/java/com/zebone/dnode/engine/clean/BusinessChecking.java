package com.zebone.dnode.engine.clean;

import com.zebone.dnode.engine.clean.exception.CleanException;


/**
 * 业务校验接口
 * @author cz
 *
 */
public interface BusinessChecking {
	
	  void checking(Object value) throws CleanException;
}
