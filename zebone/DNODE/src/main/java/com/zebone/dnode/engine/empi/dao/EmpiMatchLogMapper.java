package com.zebone.dnode.engine.empi.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.EmpiMapper;
import com.zebone.dnode.engine.empi.vo.EmpiMatchLog;

/**
 * EmpiMatchLog Mapper
 * @author YinCM
 * @date 2013-10-23 8:50:50
 */

@EmpiMapper
public interface EmpiMatchLogMapper {
    
    int insertEmpiMatchLog(EmpiMatchLog record);

    int updateEmpiMatchLog(@Param("record") EmpiMatchLog record);
    
    /**
     * 获取drc录入时候未注册，但是现在已经注册的empi的Log信息
     * @return
     */
    List<EmpiMatchLog> getUnmatchedEmpiMatchLog();
    
    /**
     * 获取empi log卡号，开类型匹配empi card表中记录的条数
     * @param empiMatchLog
     * @return
     */
    int countCardByNoAndType(EmpiMatchLog empiMatchLog);
}