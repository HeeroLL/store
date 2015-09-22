package com.zebone.register.validation.dao;


import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.DcMapper;


/**
 * 数据中心mapper
 * @author 陈阵 
 * @date 2013-8-28 上午8:16:59
 */
@DcMapper
public interface DataCenterMapper {
	

	
	/**
	 * 唯一性检查
	 * @param tableName
	 * @param tableField
	 * @param fieldValue
     * @param sourceOrg
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-1 上午10:35:19
	 */
	public int getOnlyCheck(@Param("tableName") String tableName,
			@Param("tableField") String tableField,
			@Param("fieldValue") String fieldValue,
            @Param("sourceOrg") String sourceOrg);
	
	
	
	
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
