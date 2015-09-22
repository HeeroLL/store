/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * wangpeng             New             2012-11-27     字典类型管理接口
 */
package com.zebone.btp.app.dict.service;

import java.util.List;

import com.zebone.btp.app.dict.pojo.DictionaryType;
import com.zebone.btp.core.util.Pagination;

public interface DictionaryTypeService {
	
	/**
	 * 测试
	 * @param dictionaryType
	 * @return
	 */
	public Pagination<DictionaryType> findPage(DictionaryType dictionaryType);
	
	/**
	 * 插入一条字典类型信息
	 * @date 2012-11-27
	 * @param dictionaryType
	 */
	void insertDictionaryType(DictionaryType dictionaryType);
	
	/**
	 * 更新字典类型信息
	 * @date 2012-11-27
	 * @param dictionaryType
	 */
	void updateDictionaryType(DictionaryType dictionaryType);
	
	/**
	 * 保存一条数据字典类型信息
	 * @date 2012-11-29
	 * @param dictionaryType
	 */
	boolean saveDictionaryType(DictionaryType dictionaryType);
	
	/**
	 * 根据字典类型id删除字典类型
	 * @date 2012-11-27
	 * @param typeIds
	 * @return
	 */
	boolean deleteDictionaryType(String[] typeIds);
	
	/**
	 * 根据字典类型id获取字典类型信息
	 * @date 2012-11-27
	 * @param typeId
	 * @return
	 */
	DictionaryType getDictionaryTypeById(String typeId);
	
	/**
	 * 获取所有字典类型
	 * @date 2012-11-27
	 * @return
	 */
	List<DictionaryType> getAllDictionaryType();
	
	/**
	 * 字典类型分页查询
	 * @param page
	 * @param dictionaryType
	 * @return
	 */
	Pagination<DictionaryType> getDictionaryTypePage(Pagination<DictionaryType> page,DictionaryType dictionaryType);
	
	/**
	 * 判断字典类型是否存在
	 * @date 2012-11-27
	 * @param dictionaryType
	 * @return boolean
	 */
	boolean selectDictionaryType(DictionaryType dictionaryType);
	
	/**
	 * 查询字典类型中父类型为空的字典类型
	 * @date 2012-11-27
	 * @return
	 */
	List<DictionaryType> getDictionaryTypeNoPid();

    //查询字典类型中的主数据类型
    List<DictionaryType> getDictionaryTypeForMasterData(String typeCode);
}
