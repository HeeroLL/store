package com.zebone.dip.dictionary.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.dictionary.vo.PDictType;
@Mapper
public interface PDictTypeMapper {
	int deleteById(String typeId);

	int insert(PDictType record);

	PDictType findById(String typeId);

	int updateById(PDictType record);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 获取各数据源的数据字典类型
	 * @return List<PDictType>
	 */
	List<PDictType> findDictTypeDs();

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 获取某数据源的字典类型列表
	 * @param rowBounds
	 * @param dictType
	 * @return List<PDictType>
	 */
	List<PDictType> searchDictTypeDsList(RowBounds rowBounds, PDictType dictType);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 某数据源的字典总数
	 * @param dictType
	 * @return int
	 */
	int searchDictTypeDsCount(PDictType dictType);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 更新某个数据源的数据字典类型信息
	 * @param dictType
	 * @return int
	 */
	int updateDictTypeDsInfo(PDictType dictType);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 获取该数据字典类型的字典数
	 * @param str
	 * @return int
	 */
	int getCountDictCodeByTypeId(String str);

	/**
	 * @author Administrator
	 * @date Mar 8, 2013
	 * @description TODO
	 * @param list 
	 * @return int
	 */
	int removeDictTypeStandardByIds(List<String> list);

	/**
	 * @author caixl
	 * @date Apr 19, 2013
	 * @description TODO 根据数据源获取字典类型
	 * @param dsId
	 * @return List<PDictType>
	 */
	List<PDictType> getTypeByDsId(String dsId);
}