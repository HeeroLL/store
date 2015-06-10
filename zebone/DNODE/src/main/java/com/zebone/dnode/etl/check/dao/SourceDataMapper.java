package com.zebone.dnode.etl.check.dao;

import java.util.List;
import java.util.Map;

import com.zebone.btp.core.mybatis.EtlMapper;
import com.zebone.dnode.etl.check.pojo.SourceDataObj;

/**
 * 数据源表Dao
 *
 * @author 杨英
 * @version 2014-02-15 上午08:17
 */
@EtlMapper
public interface SourceDataMapper {

    /**
     * 获取源表中需要进行检查的数据信息
     * @param paramMap
     * @return
     */
    public List<SourceDataObj> getSourceDataLic (Map paramMap);

    /**
     * 更新数据的检查状态
     * @param paramMap
     * @return
     */
    public void updateCheckFlag (Map paramMap);
}
