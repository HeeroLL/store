package com.zebone.dip.log.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.log.vo.LogDetail;
@Mapper
public interface LogDetailMapper {

	int deleteByPrimaryKey(String id);

    int insert(LogDetail record);

    int insertSelective(LogDetail record);

    LogDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LogDetail record);

    int updateByPrimaryKey(LogDetail record);

	/**
	 * 方法描述: 获取错误日志详情
	 * @author caixl
	 * @date Sep 11, 2013
	 * @param logDetail
	 * @return 
	 * LogDetail
	 */
	List<LogDetail> getLogDetailInfo(LogDetail logDetail);
}