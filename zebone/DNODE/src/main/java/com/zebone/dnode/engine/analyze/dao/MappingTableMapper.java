package com.zebone.dnode.engine.analyze.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.dnode.engine.analyze.vo.MappingInfo;

/**
 * 文档映射表相关的数据库操作
 * @author songjunjie
 * @version 2013-8-9 下午04:50:17
 */
@DipMapper
public interface MappingTableMapper {
	/**
	 * 根据文档的类型代码查询出此文档在解析的时候需要把数据解析到那些表中。
	 * 
	 * @param docTypeCode
	 *            文档类型
	 * @return List中的map保存的是表ID及表名。{tableId:数据库中的ID,tableName:表名}
	 */
	public List<Map<String, String>> getMappingTableByDocTypeCode(
			String docTypeCode);

	/**
	 * 根据文档的类型代码查询出此文档在解析的时候需要把数据解析到那些子表中。
	 * 如果某个表中的一个字段是“外键”，那么此表叫做子表。
	 * 
	 * @param docTypeCode
	 *            docTypeCode 文档类型
	 * @return List中的map保存的是表ID及表名.{tableId:数据库中的ID,tableName:表名}
	 */
	public List<Map<String, String>> getMappingSubTableByDocTypeCode(
			String docTypeCode);

	/**
	 * 根据表ID查询出映射信息及字段与文档节点的对应关系。
	 * 
	 * @param tableId
	 * @return
	 */
	public List<MappingInfo> getMappingInfo( @Param("tableId")String tableId ,  @Param("docType")String docType  , @Param("docVersion") String docVersion);
}
