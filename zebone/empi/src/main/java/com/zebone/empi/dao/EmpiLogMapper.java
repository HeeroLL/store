package com.zebone.empi.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.empi.vo.EmpiLog;

/**
 * 日志操作数据库映射接口
 * @author YinCm
 * @version 2013-7-31 下午10:15:20
 */
@Mapper
public interface EmpiLogMapper {

	/**
	 * 按主键删除该条记录
	 * @param id
	 * @return
	 */
    int deleteEmpiLogByPrimaryKey(String id);

    /**
     * 插入一条empi日志
     * @param record
     * @return
     */
    int insertEmpiLog(EmpiLog record);

    /**
     * 按日志主键，查询一条日志记录
     * @param id 日志主键
     * @return
     */
    EmpiLog selectEmpiLogByPrimaryKey(String id);

    /**
     * 通过主键更新empi日志记录
     * @param record
     * @return
     */
    int updateEmpiLogByPrimaryKey(EmpiLog record);

    /**
     * 通过empiLog信息查询
     * @param empiLog
     * @return
     */
    List<EmpiLog> searchEmpiLog(EmpiLog empiLog);
}