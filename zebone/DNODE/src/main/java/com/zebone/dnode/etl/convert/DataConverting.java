package com.zebone.dnode.etl.convert;

import com.zebone.dnode.etl.convert.dao.DataCompareMapper;

/**
 * 数据清洗转换接口
 *
 * @author 杨英
 * @version 2014-02-19 上午08:45
 */
public interface DataConverting {

    /**
     * 根据参数获取转换数据
     * @param convertPar
     * @param value
     * @param orgCode 机构编码
     * @return
     */
    String getConvertData(String convertPar, Object value,String orgCode,DataCompareMapper dataCompareMapper) throws Exception;
}
