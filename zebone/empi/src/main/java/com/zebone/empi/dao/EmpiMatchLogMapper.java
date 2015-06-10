package com.zebone.empi.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.empi.vo.EmpiMatchLog;

/**
 * EmpiMatchLog Mapper
 * @author YinCM
 * @date 2013-10-23 8:50:50
 */
@Mapper
public interface EmpiMatchLogMapper {
    
    int insertEmpiMatchLog(EmpiMatchLog record);

    int updateEmpiMatchLog(@Param("record") EmpiMatchLog record);
    
    /**
     * 按条件搜索empi匹配日志
     * @param record
     * @return
     */
    List<EmpiMatchLog> searchEmpiMatchLog(EmpiMatchLog record);
}