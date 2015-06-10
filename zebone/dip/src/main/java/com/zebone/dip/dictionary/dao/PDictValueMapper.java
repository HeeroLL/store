package com.zebone.dip.dictionary.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.dictionary.vo.PDictValue;
@Mapper
public interface PDictValueMapper {
	int deleteById(String valueId);

	int insert(PDictValue record);

	PDictValue findById(String valueId);

	int updateById(PDictValue record);

	/**
	 * @author caixl
	 * @date Mar 9, 2013
	 * @description TODO 获取某数据类型字典列表信息
	 * @param rowBounds
	 * @param dictValue
	 * @return List<PDictValue>
	 */
	List<PDictValue> searchDictDsList(RowBounds rowBounds, PDictValue dictValue);

	/**
	 * @author caixl
	 * @date Mar 9, 2013
	 * @description TODO 获取某数据类型字典数
	 * @param dictValue
	 * @return int
	 */
	int searchDictDsCount(PDictValue dictValue);

	/**
	 * @author 蔡祥龙
	 * @date Mar 9, 2013
	 * @description TODO 删除数据字典相关信息
	 * @param list void
	 */
	int removeDictDsByIds(List<String> list);

	/**
	 * @author caixl
	 * @date Mar 11, 2013
	 * @description TODO 根据字典类型获取相应的字典信息
	 * @param typeId
	 * @return List<PDictValue>
	 */
	List<PDictValue> getDictDsByDictTypeId(String typeId);
}