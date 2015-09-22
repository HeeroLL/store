package com.zebone.follow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.follow.vo.Hc;

@Mapper
public interface HcMapper {

	/**
	 * @author caixl
	 * @date Aug 25, 2013
	 * @description TODO 根据empiId获取成人体检列表
	 * @param empiId
	 * @return List<Hc>
	 */
	List<Hc> getHcByEmpiId(@Param("empiNo")String empiId);
	
}