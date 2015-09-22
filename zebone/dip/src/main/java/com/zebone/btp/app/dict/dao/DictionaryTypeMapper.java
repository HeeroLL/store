/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * wangpeng             New             2012-11-23     字典类型实现类
 */
package com.zebone.btp.app.dict.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.app.dict.pojo.DictionaryType;
import com.zebone.btp.core.mybatis.Mapper;

@Mapper
public interface DictionaryTypeMapper {
	
	/**
	 * 根据字典类型id删除字典类型
	 * @date 2012-11-23
	 * @param typeId
	 * @return
	 */
	int deleteById(String typeId);

	/**
	 * 插入一条字典类型信息
	 * @date 2012-11-23
	 * @param record
	 * @return
	 */
	int insert(DictionaryType record);

	/**
	 * 根据字典类型id查询字典类型信息
	 * @date 2012-11-23
	 * @param typeId
	 * @return
	 */
	DictionaryType findById(String typeId);

	/**
	 * 更新字典类型
	 * @date 2012-11-23
	 * @param record
	 * @return
	 */
	int updateById(DictionaryType record);
	
	/**
	 * 获取所有字典类型
	 * @date 2012-11-24
	 * @return
	 */
	List<DictionaryType> findAllDictionaryType();
	
	/**
	 * 查询父类型为空的字典类型
	 * @date 2012-11-24
	 * @return
	 */
	List<DictionaryType> findDictionaryTypeNoPid();
	
	/**
	 * 根据字典父类型获取字典类型
	 * @date 2012-11-27
	 * @param parentId
	 * @return
	 */
	List<DictionaryType> findDictionaryTypeByParentId(String parentId);
	
	/**
	 * 验证是否有相同的字典类型
	 * @date 2012-11-28
	 * @param dictionaryType
	 * @return
	 */
	int findDictionaryTypeForCheck(DictionaryType dictionaryType);
	
	/**
	 * 查询字典类型信息（分页）
	 * @date 2012-11-29
	 * @param dictionaryType
	 * @return
	 */
	List<DictionaryType> findDictionaryTypePage(RowBounds rowBounds,DictionaryType dictionaryType);
	
	/**
	 * 查询字典类型信息总数
	 * @date 2012-11-29
	 * @param dictionaryType
	 * @return
	 */
	int findDictionaryTypeTotalPage(DictionaryType dictionaryType);


    List<DictionaryType> getDictionaryTypeForMasterData(String typeCode);
}