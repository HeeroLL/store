package com.zebone.dnode.etl.check.dao;

import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.dnode.etl.check.pojo.CheckLogObj;

/**
 * 检查日志Dao
 *
 * @author 杨英
 * @version 2014-02-17 下午01:07
 */
@DipMapper
public interface CheckLogMapper {

    /**
     * 保存日志信息
     * @param checkLogObj
     * @return
     */
    void saveCheckLog (CheckLogObj checkLogObj);
}
