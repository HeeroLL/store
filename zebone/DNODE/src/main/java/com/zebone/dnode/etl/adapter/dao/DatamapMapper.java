package com.zebone.dnode.etl.adapter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.dnode.etl.adapter.vo.DictInfo;
import com.zebone.dnode.etl.adapter.vo.DictMap;
import com.zebone.dnode.etl.adapter.vo.FieldConf;

/**
 * 类描述：
 * @author: caixl
 * @date： 日期：Dec 26, 2013
 * @version 1.0
 */
@DipMapper
public interface DatamapMapper {

	/**
	 * 根据元数据标识获取元数据信息
	 * @param fieldCode 元数据标识
	 * @return 
	 * FieldConf
	 */
	FieldConf getByFieldCode(@Param("fieldCode")String fieldCode);

	/**
	 * 根据机构和标准字典类型获取数据字典对照基本信息
	 * @param orgCode
	 * @param fieldValue
	 * @return 
	 * List<DictMap>
	 */
	List<DictMap> getDictList(@Param("orgCode")String orgCode, @Param("dictTypeId")String fieldValue);

	/**
	 * 根据标准字典标识获取标准字典信息
	 * @param dictId 字典标识
	 * @return 
	 * DictInfo
	 */
	DictInfo getByDictId(@Param("dictId")String dictId);

	/**
	 * 根据主数据标识获取该主数据所在的表的表名
	 * @param fieldValue
	 * @return 
	 * String
	 */
	String getByMdId(@Param("mdId")String fieldValue);

	/**
	 * 根据机构主数据信息获取标准主数据标识
	 * @param orgTableName 机构主数据表名
	 * @param orgCode 机构编码
	 * @param code 主数据编码
	 * @return 
	 * String
	 */
	String getMdIdByMd(@Param("orgTableName")String orgTableName, @Param("orgCode")String orgCode, @Param("code")String code);

	/**
	 * 根据主数据标识获取相关主数据编码名称
	 * @param tableName 主数据表名
	 * @param mdId 主数据标识
	 * @return 
	 * DictInfo
	 */
	DictInfo getDictByMdId(@Param("tableName")String tableName,@Param("mdId")String mdId);
	
}
