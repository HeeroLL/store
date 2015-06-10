package com.zebone.dnode.engine.clean;

import com.zebone.dnode.engine.clean.exception.CleanException;


/**
 * 转化器接口
 * @author cz
 *
 */
public interface DataConvert  {
	
	 Object convert(Object value) throws CleanException;
	 
}
