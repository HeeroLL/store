package com.zebone.dnode.etl.convert.dao;

import java.util.List;
import java.util.Map;

import com.zebone.btp.core.mybatis.EtlMapper;
import com.zebone.dnode.etl.convert.pojo.ConvertDataObj;

/**
 * 清洗转换数据Dao
 *
 * @author 杨英
 * @version 2014-02-18 下午01:27
 */
@EtlMapper
public interface ConvertDataMapper {

    /**
     * 获取源表字段列表信息
     * @param paramMap
     * @return
     */
    public List<String> getColumnNameLic (Map paramMap);

    /**
     * 获取源表中需要进行清洗转换的数据信息
     * @param paramMap
     * @return
     */
    public List<ConvertDataObj> getConvertDataLic (Map paramMap);

    /**
     * 更新数据的清洗状态
     * @param paramMap
     * @return
     */
    public void updateCleanFlag (Map paramMap);

    /**
     * 把清洗后的数据保存到目标清洗表中
     * @param paramMap
     * @return
     */
    public void saveConvertData (Map paramMap);
}
