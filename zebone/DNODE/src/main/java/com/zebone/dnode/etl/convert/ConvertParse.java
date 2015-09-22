package com.zebone.dnode.etl.convert;

import com.zebone.dnode.etl.convert.dao.DataCompareMapper;
import org.apache.commons.lang3.ClassUtils;

/**
 * 数据清洗转换解析
 *
 * @author 杨英
 * @version 2014-02-19 上午08:46
 */
public class ConvertParse{

    private static final String DATA_CONVERT_PACKAGE = "com.zebone.dnode.etl.convert.";

    public String getConvertData(String convertClass,String convertPar, Object value, String orgCode,DataCompareMapper dataCompareMapper) throws Exception{
        String convertData = value.toString();
        try {
            DataConverting dataConverting = (DataConverting) ClassUtils.getClass(DATA_CONVERT_PACKAGE + convertClass).newInstance();
            convertData = dataConverting.getConvertData(convertPar,value,orgCode,dataCompareMapper);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return convertData;
    }
}
