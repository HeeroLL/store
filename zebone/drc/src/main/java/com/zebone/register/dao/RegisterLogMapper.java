package com.zebone.register.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.register.vo.RegisterLog;

@Mapper
public interface RegisterLogMapper {

	/**
	 * @author caixl
	 * @date Aug 9, 2013
	 * @description TODO 记录文档注册历史信息
	 * @param registerLog
	 * @return int
	 */
	int insert(RegisterLog registerLog);
    
}