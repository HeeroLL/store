package com.zebone.dip.log.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.mybatis.EmpiMapper;
import com.zebone.dip.log.vo.EmpiLog;
import com.zebone.dip.log.vo.EmpiResult;
import com.zebone.dip.log.vo.EmpiSearchObj;
import com.zebone.dip.log.vo.ParmInfo;

/**
 * 日志操作数据库映射接口
 * @author YinCm
 * @version 2013-7-31 下午10:15:20
 */
@EmpiMapper
public interface EmpiLogMapper {

    /**
     * 查询注册更新信息列表
     * @param empiLog
     * @return
     */
    List<EmpiResult> searchEmpiLog(EmpiSearchObj empiSearchObj, RowBounds rowBounds);
    
    /**
     * 查询失败注册更新信息总数
     * @param empiLog
     * @return
     */
    int searchEmpiLogCount(EmpiSearchObj empiSearchObj);
    
    
    /**
     * 插入一条empi日志
     * @param record
     * @return
     */
    int insertEmpiLog(EmpiLog record);

    
}