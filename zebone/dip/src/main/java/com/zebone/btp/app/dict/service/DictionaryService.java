/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * wangpeng             New             2012-11-27     字典管理接口
 */
package com.zebone.btp.app.dict.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.app.dict.pojo.Dictionary;
import com.zebone.btp.core.util.Pagination;


public interface DictionaryService {
	
	/**
	 * 测试
	 * @param dictionary
	 * @return
	 */
	public Pagination<Dictionary> findPage(Dictionary dictionary);
	
	/**
	 * 查询字典信息（不分页）
	 * @date 2012-11-27
	 * @param page
	 * @param dictionary
	 */
	Pagination<Dictionary> getDictionaryPage(Pagination<Dictionary> page,Dictionary dictionary);
	
	/**
	 * 插入一条数据字典信息
	 * @date 2012-11-27
	 * @param dictionary
	 */
	void insertDictionary(Dictionary dictionary);
	
	/**
	 * 更新一条数据字典信息
	 * @date 2012-11-27
	 * @param dictionary
	 */
	void updateDictionary(Dictionary dictionary);
	
	/**
	 * 保存一条数据字典信息
	 * @date 2012-11-27
	 * @param dictionary
	 */
	boolean saveDictionary(Dictionary dictionary);
	
	/**
	 * 根据字典id删除字典信息
	 * @date 2012-11-27
	 * @param dictIds
	 * @return
	 */
	boolean deleteDictionary(String[] dictIds);
	
	/**
	 * 根据字典id查询字典信息
	 * @date 2012-11-27
	 * @param dictId
	 * @return
	 */
	Dictionary getDictionaryById(String dictId);
	
	/**
	 * 根据字典编码查询字典信息
	 * @date 2012-11-27
	 * @param dictCode
	 * @return
	 */
	Dictionary getDictionaryByCode(String dictCode);
	
	/**
	 * 根据字典类型编码 查询字典类型下所有的字典信息
	 * @date 2012-11-27
	 * @param typeCode
	 * @return
	 */
	List<Dictionary> getDictionaryByTypeCode(String typeCode);
	
	/**
	 * 交换排序号
	 * @date 2012-11-29
	 * @param dictionary
	 * @return
	 */
	Boolean dictionaryOrder(Dictionary dictionary);
	
	/**
	 * 根据类型编码和字典编码获取字典值
	 * @date 2012-12-07
	 * @param typeCode
	 * @param dictCode
	 * @return
	 */
	Dictionary findDictionaryByTypeCodeAndDictCode(@Param("typeCode")String typeCode, @Param("dictCode")String dictCode);
	
	/**
	 * 根据类型id查询字典并重新排序
	 * @date 2012-12-08
	 * @param dicttypeId
	 */
	void orderDictionaryByTypeId(String dicttypeId);

}
