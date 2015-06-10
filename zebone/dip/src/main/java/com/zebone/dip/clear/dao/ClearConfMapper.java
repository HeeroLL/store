package com.zebone.dip.clear.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.clear.vo.ClearConf;
@Mapper
public interface ClearConfMapper {
	int deleteById(String id);

	int insert(ClearConf record);
	
	ClearConf findById(String id);

	int updateById(ClearConf record);

	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 根据表配置标识看是否有表清洗配置信息
	 * @param confId
	 * @return ClearConf
	 */
	ClearConf getClearConfById(String confId);
}