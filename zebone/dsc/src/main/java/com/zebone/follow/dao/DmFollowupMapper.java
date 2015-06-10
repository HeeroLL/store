package com.zebone.follow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.follow.vo.DmFollowup;
@Mapper
public interface DmFollowupMapper {

	/**
	 * @author caixl
	 * @date Aug 25, 2013
	 * @description TODO 根据 empiId 获取糖尿病随访记录
	 * @param empiId
	 * @return List<DmFollowup>
	 */
	List<DmFollowup> getDmByEmpiId(@Param("empiNo")String empiId);
	
}