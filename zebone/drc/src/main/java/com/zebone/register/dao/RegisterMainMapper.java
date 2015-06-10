package com.zebone.register.dao;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.register.vo.RegisterMain;

@Mapper
public interface RegisterMainMapper {

	/**
	 * @author caixl
	 * @date Aug 9, 2013
	 * @description TODO 获取该文档编号在数据库中是否存在
	 * @param docNo 
	 * @return int
	 */
	int getRegisterCountByDocNo(@Param("docNo")String docNo);

	/**
	 * @author caixl
	 * @date Aug 9, 2013
	 * @description TODO 保存注册信息
	 * @param registerMain
	 * @return int
	 */
	int insert(RegisterMain registerMain);

	/**
	 * @author caixl
	 * @date Aug 9, 2013
	 * @description TODO 根据文档编号获取注册标识
	 * @param docNo
	 * @return String
	 */
	String getMainIdByDocNo(@Param("docNo")String docNo);

	/**
	 * @author caixl
	 * @date Aug 9, 2013
	 * @description TODO 更新文档注册
	 * @param registerMain
	 * @return int
	 */
	int update(RegisterMain registerMain);
	
}