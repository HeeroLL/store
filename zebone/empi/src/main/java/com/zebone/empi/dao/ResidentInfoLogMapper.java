package com.zebone.empi.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.empi.vo.ResidentInfo;

/**
 * 居民信息数据库操作映射接口
 * @author YinCm
 * @version 2013-7-31 下午10:15:20
 */
@Mapper
public interface ResidentInfoLogMapper {

	 

    /**
     * 插入居民信息记录
     * @param record
     * @return
     */
    int insertResidentInfoLog(ResidentInfo record);

    
    
}