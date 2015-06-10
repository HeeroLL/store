package com.zebone.dnode.engine.clean;

import com.zebone.dnode.engine.clean.exception.CleanException;
import com.zebone.dnode.engine.clean.exception.ErrorUtil;

public class EmailCheck implements BusinessChecking {
    
	private static final String EMAIL_CHECK_ERROR_MESSAGE = "邮件格式不正确";
	
	
	@Override
	public void checking(Object value) throws CleanException {
		// TODO Auto-generated method stub
		String val = value.toString();
		if(!val.matches("\\w+(\\.\\w+)*@\\w+(\\.\\w+)+")){
			CleanException ce = new CleanException(ErrorUtil.BUSINESS_CHECKING_ERROR, EMAIL_CHECK_ERROR_MESSAGE);
			throw ce;
		}
	}

}
