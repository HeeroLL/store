package com.zebone.dip.log.dao;


import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.EmpiMapper;
import com.zebone.dip.log.vo.EmpiResult;
import com.zebone.dip.log.vo.EmpiSearchObj;

/**
 * EmpiMatchLog Mapper
 * @author YinCM
 * @date 2013-10-23 8:50:50
 */
@EmpiMapper
public interface EmpiMatchLogMapper {
    
    /**
     * 按条件搜索empi匹配日志
     * @param record
     * @return
     */
    List<EmpiResult> searchEmpiMatchLog(EmpiSearchObj empiSearchObj, RowBounds rowBounds);
    
    /**
     * 查找empi查询日志总条数
     * @param empiSearchObj
     * @return
     */
    int searchEmpiMatchLogCount(EmpiSearchObj empiSearchObj);
}