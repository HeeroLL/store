package com.zebone.dip.dictionary.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.DcMapper;
import com.zebone.dip.dictionary.vo.PDictionaryType;
@DcMapper
public interface PDictionaryTypeMapper {
	/**
	 * @author caixl
	 * @date Mar 6, 2013
	 * @description TODO 标准数据字典类型列表查询
	 * @param rowBounds
	 * @param dictionary
	 * @return List<PDictionaryType>
	 */
	List<PDictionaryType> searchDictTypeStandardList(RowBounds rowBounds,
			PDictionaryType dictionary);

	/**
	 * @author caixl
	 * @date Mar 6, 2013
	 * @description TODO 标准数据字典类型数
	 * @param dictionary 
	 * @return int
	 */
	int searchDictTypeStandardTotalCount(PDictionaryType dictionary);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 获取标准数据字典类型信息
	 * @return List<PDictionaryType>
	 */
	List<PDictionaryType> getPdictStandardInfo();

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 跟标识加载数据字典类型信息
	 * @param id
	 * @return PDictionaryType
	 */
	PDictionaryType getDictTypeStandardById(String id);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 保存数据字典类型信息
	 * @param pdt
	 * @return int
	 */
	int saveDictTypeStardInfo(PDictionaryType pdt);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 更新数据字典类型信息
	 * @param pdt
	 * @return int
	 */
	int updateDictTypeStardInfo(PDictionaryType pdt);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 该字典类型有的字典数
	 * @param str
	 * @return int
	 */
	int getCountDictCodeByTypeId(String str);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 删除标准数据字典类型信息
	 * @param list
	 * @return int
	 */
	int removeDictTypeStandardByIds(List<String> list);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 判断字典类型编码是否存在
	 * @param pdt
	 * @return int
	 */
	int findDictTypeStandardForCheck(PDictionaryType pdt);
}