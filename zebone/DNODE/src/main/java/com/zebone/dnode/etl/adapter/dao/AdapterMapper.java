package com.zebone.dnode.etl.adapter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.dnode.etl.adapter.vo.DictInfo;
import com.zebone.dnode.etl.adapter.vo.FieldConf;
import com.zebone.dnode.etl.adapter.vo.NodeColumnInfo;
import com.zebone.dnode.etl.adapter.vo.TableInfo;

/**
 * 表转换文档
 * @author: caixl
 * @date： 日期：Feb 20, 2014
 * @version 1.0
 */
@DipMapper
public interface AdapterMapper {

	/**
	 * 获取文档中 所有节点的相关信息
	 * @param id
	 * @return 
	 * List<NodeColumnInfo>
	 */
	List<NodeColumnInfo> getListById(@Param("id")String id);

	/**
	 * 获取主从表 和 关联字段 相关信息
	 * @param id
	 * @return 
	 * List<TableInfo>
	 */
	List<TableInfo> getTableInfosById(@Param("id")String id);

	/**
	 * 根据标识获取表名
	 * @param ids
	 * @return 
	 * List<TableInfo>
	 */
	List<TableInfo> getTableNameByTId(@Param("id")String ids);

	/**
	 * 获取元数据标识内容
	 * @param fieldId
	 * @return 
	 * FieldConf
	 */
	FieldConf getFieldConfById(@Param("fieldId")String fieldId);

	/**
	 * 获取标准数据字典名称
	 * @param fieldValue
	 * @param code
	 * @return 
	 * DictInfo
	 */
	DictInfo getDictByTypeIdAndCode(@Param("fieldValue")String fieldValue, @Param("code")String code);

	/**
	 * 或去主数据表名
	 * @param fieldValue
	 * @return 
	 * DictInfo
	 */
	DictInfo getTableInfoById(@Param("fieldValue")String fieldValue);

	/**
	 * 获取某个主数据名称
	 * @param tableName
	 * @param code
	 * @return 
	 * DictInfo
	 */
	DictInfo getMainNameByCode(@Param("tableName")String tableName, @Param("code")String code);

	/**
	 * 获取表名信息
	 * @param tableId
	 * @return 
	 * DictInfo
	 */
	DictInfo getMainTableInfo(@Param("tableId")String tableId);

	/**
	 * 获取主键或主键值
	 * @param tableId
	 * @return 
	 * DictInfo
	 */
	DictInfo getInfoBytId(@Param("tableId")String tableId);
	
}
