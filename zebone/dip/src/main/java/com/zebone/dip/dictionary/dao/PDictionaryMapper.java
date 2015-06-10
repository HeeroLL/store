package com.zebone.dip.dictionary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.DcMapper;
import com.zebone.dip.dictionary.vo.PDictionary;
@DcMapper
public interface PDictionaryMapper {

	PDictionary findById(String dictId);
	
	int insert(PDictionary dictionary);
	
	int updateById(PDictionary dictionary);
	/**
	 * @author caixl
	 * @date Mar 6, 2013
	 * @description TODO 获取标准数据字典的列表
	 * @param dictionary
	 * @return List<PDictionary>
	 */
	List<PDictionary> searchDictStandardList(PDictionary dictionary);

	/**
	 * @author caixl
	 * @date Mar 6, 2013
	 * @description TODO 获取标准数据字典的总数
	 * @param dictionary
	 * @return int
	 */
	int searchDictStandardTotalCount(PDictionary dictionary);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 获取该数据字典类型的数据字典的最大编号
	 * @param dicttypeId
	 * @return int
	 */
	int findMaxDictNo(String dicttypeId);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO
	 * @param dicttypeId
	 * @return List<PDictionary>
	 */
	List<PDictionary> findDictStandardByTypeId(String dicttypeId);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 
	 * @param dicttypeId
	 * @param orderNo
	 * @return List<PDictionary>
	 */
	List<PDictionary> findDictStandardByOrderNo(@Param("dicttypeId")String dicttypeId, @Param("orderNo")Short orderNo);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO
	 * @param dicttypeId
	 * @param orderNo
	 * @param upOrdown
	 * @return PDictionary
	 */
	PDictionary findDictStandardForOrder(@Param("dicttypeId")String dicttypeId, @Param("orderNo")Short orderNo,@Param("upOrdowm")int upOrdown);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 删除数据字典相关信息
	 * @param list
	 * @return int
	 */
	int removeDictStandardByIds(List<String> list);

	/**
	 * @author caixl
	 * @date Mar 11, 2013
	 * @description TODO 获取字典相关信息
	 * @param dictId
	 * @return List<PDictionary>
	 */
	List<PDictionary> getDictStandardById(String dictId);

	/**
	 * @author caixl 
	 * @date Mar 11, 2013
	 * @description TODO 根据数据类型获取数据字典信息
	 * @param dictId
	 * @return List<PDictionary>
	 */
	List<PDictionary> getDictStandardByDictId(String dictId);
}