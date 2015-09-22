package com.zebone.dnode.etl.check;

import org.apache.commons.lang3.ClassUtils;

/**
 * 数据校验解析
 *
 * @author 杨英
 * @version 2014-02-13 下午09:48
 */
public class DataParse {

    private static final String DATA_CHECK_PACKAGE = "com.zebone.dnode.etl.check.";

    public boolean isDataValid(String checkClass,String checkReg, Object value){
        boolean isDataValid = false;
        try {
            DataChecking dataChecking = (DataChecking) ClassUtils.getClass(DATA_CHECK_PACKAGE + checkClass).newInstance();
            isDataValid = dataChecking.check(checkReg,value);
        }catch(Exception e){
            e.printStackTrace();
        }
        return isDataValid;
    }
}
