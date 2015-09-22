/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Mar 6, 2013		数据字典业务接口层
 */
package com.zebone.dip.dictionary.service;

import java.util.List;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.vo.PDictType;
import com.zebone.dip.dictionary.vo.PDictValue;
import com.zebone.dip.dictionary.vo.PDictionary;
import com.zebone.dip.dictionary.vo.PDictionaryType;

public interface DictService {

	/**
	 * @author caixl
	 * @date Mar 6, 2013
	 * @description TODO 获取标准数据字典类型列表
	 * @param dictionary
	 * @param page
	 * @return Pagination<PDictionary>
	 */
	Pagination<PDictionaryType> getDictTypeStandardList(PDictionaryType dictionary,Pagination<PDictionaryType> page);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 获取标准数据字典类型数据
	 * @return List<PDictionaryType>
	 */
	List<PDictionaryType> getPDictStandardInfo();

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 加载数据字典类型信息
	 * @param id
	 * @return PDictionaryType
	 */
	PDictionaryType getDictTypeStandardById(String id);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 保存标准数据字典类型信息
	 * @param pdt
	 * @return int
	 */
	int saveDictTypeStardInfo(PDictionaryType pdt);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 更新标准数据字典类型信息
	 * @param pdt
	 * @return int
	 */
	int updateDictTypeStardInfo(PDictionaryType pdt);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 删除相关标准数据字典类型信息
	 * @param id
	 * @return int
	 */
	int removeDictTypeStandardByIds(String id);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 获取标准数据字典列表信息
	 * @param dictionary
	 * @param page
	 * @return Pagination<PDictionary>
	 */
	Pagination<PDictionary> getDictStandardList(PDictionary dictionary,
			Pagination<PDictionary> page);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 保存数据字典相关信息
	 * @param dictionary
	 * @return int
	 */
	int saveDictStardInfo(PDictionary dictionary);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 更新数据字典相关信息
	 * @param dictionary
	 * @return int
	 */
	int updateDictStardInfo(PDictionary dictionary);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 判断字典类型编码是否存在
	 * @param pdt
	 * @return boolean
	 */
	boolean selectDictTypeStandard(PDictionaryType pdt);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 根据类型id查询字典并重新排序
	 * @param dicttypeId int
	 */
	int orderDictionaryByTypeId(String dicttypeId);

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 交换排序号
	 * @param dictionary
	 * @return boolean
	 */
	boolean dictionaryOrder(PDictionary dictionary);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 根据数据字典标识获取数据字典信息
	 * @param id
	 * @return PDictionary
	 */
	PDictionary getDictStandardById(String id);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 删除数据字典相关信息
	 * @param id
	 * @return int
	 */
	int removeDictStandardByIds(String id);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 获取各数据源字典类型信息
	 * @return List<PDictType>
	 */
	List<PDictType> getDictTypeDsInfo();

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 获取某数据源的数据字典类型列表
	 * @param dictType
	 * @param page
	 * @return Pagination<PDictType>
	 */
	Pagination<PDictType> getDictTypeDsList(PDictType dictType,Pagination<PDictType> page);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 保存某个数据源的数据字典类型信息
	 * @param dictType
	 * @return int
	 */
	int saveDictTypeDsInfo(PDictType dictType);

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
	 * @description TODO 获取某数据源的数据字典类型的信息
	 * @param id
	 * @return PDictType
	 */
	PDictType getDictTypeDsById(String id);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 删除数据字典类型信息
	 * @param id
	 * @return int
	 */
	int removeDictTypeDsByIds(String id);

	/**
	 * @author 蔡祥龙
	 * @date Mar 9, 2013
	 * @description TODO 各数据源字典列表信息查询
	 * @param dictValue
	 * @param page
	 * @return Pagination<PDictValue>
	 */
	Pagination<PDictValue> getDictDsList(PDictValue dictValue,
			Pagination<PDictValue> page);

	/**
	 * @author caixl
	 * @date Mar 9, 2013
	 * @description TODO 加载数据源字典的信息
	 * @param id 字典标识
	 * @return PDictValue
	 */
	PDictValue getDictDsById(String id);

	/**
	 * @author caixl
	 * @date Mar 9, 2013
	 * @description TODO 保存数据字典信息
	 * @param dictValue
	 * @return int
	 */
	int saveDictDsInfo(PDictValue dictValue);

	/**
	 * @author 蔡祥龙
	 * @date Mar 9, 2013
	 * @description TODO 更新数据字典信息
	 * @param dictValue
	 * @return int
	 */
	int updateDictDsInfo(PDictValue dictValue);

	/**
	 * @author caixl
	 * @date Mar 9, 2013
	 * @description TODO 删除数据源数据字典相关信息
	 * @param id
	 * @return int
	 */
	int removeDictDsByIds(String id);

	/**
	 * @author caixl
	 * @date Mar 11, 2013
	 * @description TODO 根据字典类型获取相应的字典信息
	 * @param typeId
	 * @return List<PDictValue>
	 */
	List<PDictValue> getDictDsByDictTypeId(String typeId);

	/**
	 * @author caixl
	 * @date Mar 11, 2013
	 * @description TODO 获取字典相关信息
	 * @param dictId
	 * @return List<PDictionary>
	 */
	List<PDictionary> getDictStandardByIds(String dictId);

	/**
	 * @author 根据字典类型获取字典
	 * @date Mar 11, 2013
	 * @description TODO
	 * @param dictId
	 * @return List<PDictionary>
	 */
	List<PDictionary> getDictStandardByDictId(String dictId);

	/**
	 * @author caixl
	 * @date Mar 11, 2013
	 * @description TODO 保存匹配的数据字典
	 * @param dictData
	 * @return int
	 */
	int saveDictMatchInfo(String dictData);

	/**
	 * @author 蔡祥龙
	 * @date Apr 19, 2013
	 * @description TODO 获取某个数据源数据字典类型的信息
	 * @param dsId
	 * @return List<PDictType>
	 */
	List<PDictType> getAlldicttypeBydsId(String dsId);

}
