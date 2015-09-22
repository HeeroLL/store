package com.zebone.register.validation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.register.validation.domain.DicType;
import com.zebone.register.validation.domain.DicValue;
import com.zebone.register.validation.domain.MasterData;

/**
 * 主数据mapper
 * @author 陈阵 
 * @date 2013-7-31 下午2:47:52
 */
@DipMapper
public interface MasterDataMapper {
	
	/**
	 * 根据字典类型id获取字典信息  
	 * @param dicTypeId
	 * @return
	 * @author 陈阵 
	 * @date 2013-7-31 下午3:48:08
	 */
	public List<DicValue> getDicByDicTypeId(@Param("dicTypeId") String dicTypeId);
	
	/**
	 * 根据字典类型id获取字典类型信息
	 * @param typeId
	 * @return
	 * @author 陈阵 
	 * @date 2013-7-31 下午3:48:15
	 */
	public DicType getDicTypeByTypeId(@Param("typeId") String typeId);
	 
	
	
	/**
	 * 根据主键获取主数据所在的表
	 * @param id
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-1 上午10:18:37
	 */
	public MasterData getTableFieldById(@Param("id") String id);
	
	
	/**
	 *  检查主数据中是有该值
	 * @param tableName
	 * @param tableField
	 * @param fieldValue
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-1 上午10:35:19
	 */
	public int getMasterCheck(@Param("tableName") String tableName,
			@Param("tableField") String tableField,
			@Param("fieldValue") String fieldValue);


}
