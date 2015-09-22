package com.zebone.follow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.follow.vo.DataPreprocess;

@Mapper
public interface EhrMainMapper {

	/**
	 * @author caixl 
	 * @date Aug 25, 2013
	 * @description TODO 调阅档案浏览器首页数据块信息
	 * @param empiId
	 * @param code
	 * @return List<DataPreprocess>
	 */
	List<DataPreprocess> getDataBlock(@Param("empiNo")String empiId, @Param("dataCode")String code);

}
