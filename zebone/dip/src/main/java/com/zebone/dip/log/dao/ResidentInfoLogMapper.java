package com.zebone.dip.log.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.EmpiMapper;
import com.zebone.dip.log.vo.EmpiResult;
import com.zebone.dip.log.vo.EmpiSearchObj;
import com.zebone.dip.log.vo.ResidentInfoLog;


/**
 * 居民信息数据库操作映射接口
 * @author YinCm
 * @version 2013-7-31 下午10:15:20
 */
@EmpiMapper
public interface ResidentInfoLogMapper {

    /**
     * 查询获取日志列表
     * @param record
     * @return
     */
    List<EmpiResult> searchResidentInfoLog(EmpiSearchObj empiSearchObj, RowBounds rowBounds);
 
    /**
     * 查询获取日志列表数
     * @param record
     * @return
     */
    int searchResidentInfoLogCount(EmpiSearchObj empiSearchObj);
    
    
    
    int insertResidentInfoLog(ResidentInfoLog residentInfoLog);
    
}