package com.zebone.dnode.etl.convert.dao;

import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.dnode.etl.convert.pojo.ConvertLogObj;

/**
 * 清洗转换日志Dao
 *
 * @author 杨英
 * @version 2014-02-18 下午02:15
 */
@DipMapper
public interface ConvertLogMapper {

    /**
     * 保存清洗转换日志信息
     * @param convertLogObj
     * @return
     */
    void saveConvertLog (ConvertLogObj convertLogObj);
}
