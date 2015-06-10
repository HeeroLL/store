package com.zebone.follow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.follow.vo.Elderfu;

@Mapper
public interface ElderfuMapper {

	/**
	 * @author caixl
	 * @date Aug 25, 2013
	 * @description TODO 根据empiId 获取老年人随访记录
	 * @param empiId
	 * @return List<Elderfu>
	 */
	List<Elderfu> getElderByEmpiId(@Param("empiNo")String empiId);
	
}