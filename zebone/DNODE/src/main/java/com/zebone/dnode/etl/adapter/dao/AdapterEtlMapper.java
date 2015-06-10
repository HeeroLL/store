package com.zebone.dnode.etl.adapter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.EtlMapper;
import com.zebone.dnode.etl.adapter.vo.MainTableInfo;

/**
 * etl数据持久
 * @author: caixl
 * @date： 日期：Feb 20, 2014
 * @version 1.0
 */
@EtlMapper
public interface AdapterEtlMapper {

	/**
	 * 获取主表数据信息
	 * @param columns
	 * @param tname
	 * @return 
	 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> getListByInfo(@Param("columns")String columns, @Param("tName")String tname);

	/**
	 * 获取子表数据信息
	 * @param columns
	 * @param tableName
	 * @param foreignKey
	 * @param keyCode
	 * @return 
	 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> getSubListByInfo(@Param("columns")String columns, @Param("tableName")String tableName,@Param("foreignKey")String foreignKey,@Param("keyCode")String keyCode);

	/**
	 * 更新转换后业务表的状态
	 * @param mainTableInfo
	 * @return 
	 * int
	 */
	int updateTransformStatus(MainTableInfo mainTableInfo);

}
