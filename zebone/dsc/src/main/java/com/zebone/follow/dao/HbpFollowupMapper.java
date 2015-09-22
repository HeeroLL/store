package com.zebone.follow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.follow.vo.HbpFollowup;
@Mapper
public interface HbpFollowupMapper {

	/**
	 * @author ciaxl
	 * @date Aug 25, 2013
	 * @description TODO 获取高血压列表
	 * @param empiId
	 * @return List<HbpFollowup>
	 */
	List<HbpFollowup> getHbpByEmpiId(@Param("empiNo")String empiId);
	
}