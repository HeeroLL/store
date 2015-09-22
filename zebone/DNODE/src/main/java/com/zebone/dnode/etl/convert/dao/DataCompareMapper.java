package com.zebone.dnode.etl.convert.dao;

import java.util.Map;

import com.zebone.btp.core.mybatis.DipMapper;

/**
 * 数据对照Dao
 *
 * @author 杨英
 * @version 2014-02-19 上午09:21
 */
@DipMapper
public interface DataCompareMapper {
    /**
     * 获取字典对照编码
     * @param paramMap
     * @return
     */
    public String getDictCompareCode(Map paramMap);


    /**
     * 根据主数据类型获取主数据表
     * @param  code
     * @return
     */
    public String getMDTable(String code);

    /**
     * 获取主数据对照编码
     * @param paramMap
     * @return
     */
    public String getMDCompareCode(Map paramMap);
}
