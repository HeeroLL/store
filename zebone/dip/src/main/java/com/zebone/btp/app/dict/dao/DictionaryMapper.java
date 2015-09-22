/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * wangpeng             New             2012-11-23     字典实现类
 */
package com.zebone.btp.app.dict.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.app.dict.pojo.Dictionary;
import com.zebone.btp.core.mybatis.Mapper;

@Mapper
public interface DictionaryMapper {
	
	/**
	 * 根据字典id删除字典
	 * @date 2012-11-23
	 * @param dictId
	 * @return
	 */
	int deleteById(String dictId);

	/**
	 * 插入字典信息
	 * @date 2012-11-23
	 * @param record
	 * @return
	 */
	int insert(Dictionary record);

	/**
	 * 根据字典id查询字典信息
	 * @date 2012-11-23
	 * @param dictId
	 * @return
	 */
	Dictionary findById(String dictId);

	/**
	 * 根据字典编码查询字典信息
	 * @date 2012-11-23
	 * @param dictCode
	 * @return
	 */
	Dictionary findByCode(String dictCode);
	
	/**
	 * 更新字典信息
	 * @date 2012-11-23
	 * @param record
	 * @return
	 */
	int updateById(Dictionary record);
	
	/**
	 * 查询字典信息（不分页）
	 * @date 2012-11-23
	 * @param dictionary
	 * @return
	 */
	List<Dictionary> findDictionaryPage(Dictionary dictionary);
	
	/**
	 * 查询字典信息总数
	 * @date 2012-11-23
	 * @param dictionary
	 * @return
	 */
	int findDictionaryTotalPage(Dictionary dictionary);
	
	/**
	 * 根据字典类型编查询字典信息,查询结果按照排序号正序
	 * @date 2012-11-23
	 * @param typeCode
	 * @return
	 */
	List<Dictionary> findDictionaryByTypeCode(String typeCode);
	
	/**
	 * 根据字典类型id获取类型下最大的排序号，包括已经删除的
	 * @date 2012-11-27 
	 * @param dicttypeId
	 * @return
	 */
	int findDictionaryMaxNo(String dicttypeId);
	
	/**
	 * 获取需要交换排序号的字典
	 * @date 2012-11-29
	 * @param dicttypeId
	 * @param orderNo
	 * @param upOrdowm
	 * @return
	 */
	Dictionary findDictionaryForOrder(@Param("dicttypeId")String dicttypeId,@Param("orderNo")Integer orderNo, @Param("upOrdowm")Integer upOrdowm);
	
	/**
	 * 根据排序号和字典类型id获取字典
	 * @date 2012-11-30
	 * @param dicttypeId
	 * @param orderNo
	 * @return
	 */
	List<Dictionary> findDictionaryByOrderNo(@Param("dicttypeId")String dicttypeId,@Param("orderNo")Integer orderNo);
	
	/**
	 * 根据字典类型id重新排序
	 * @date 2012-12-03
	 * @param dicttypeId
	 */
	void updateAllOrderNo(String dicttypeId);
	
	/**
	 * 根据字典类型id获取类型下的字典
	 * @date 2012-12-03
	 * @param dicttypeId
	 * @return
	 */
	List<Dictionary> findDictionaryByTypeId(@Param("dicttypeId")String dicttypeId);
	
	/**
	 * 根据类型编码和字典编码获取字典值
	 * @date 2012-12-07
	 * @param typeCode
	 * @param dictCode
	 * @return
	 */
	Dictionary findDictionaryByTypeCodeAndDictCode(@Param("typeCode")String typeCode, @Param("dictCode")String dictCode);
}