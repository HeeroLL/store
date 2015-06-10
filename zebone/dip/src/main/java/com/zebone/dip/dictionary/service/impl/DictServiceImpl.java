/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Mar 6, 2013		数据字典业务实现层
 */
package com.zebone.dip.dictionary.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.dao.PDictTypeMapper;
import com.zebone.dip.dictionary.dao.PDictValueMapper;
import com.zebone.dip.dictionary.dao.PDictionaryMapper;
import com.zebone.dip.dictionary.dao.PDictionaryTypeMapper;
import com.zebone.dip.dictionary.service.DictService;
import com.zebone.dip.dictionary.vo.PDictType;
import com.zebone.dip.dictionary.vo.PDictValue;
import com.zebone.dip.dictionary.vo.PDictionary;
import com.zebone.dip.dictionary.vo.PDictionaryType;
import com.zebone.dip.ds.dao.DsObjMapper;
import com.zebone.dip.ds.vo.DsObj;
@Service("dictService")
public class DictServiceImpl implements DictService {

	@Resource
	private PDictionaryMapper pDictionaryMapper;
	@Resource
	private PDictionaryTypeMapper pDictionaryTypeMapper;
	@Resource
	private DsObjMapper dsObjMapper;
	@Resource
	private PDictTypeMapper pDictTypeMapper;
	@Resource
	private PDictValueMapper pDictValueMapper;
	
	/**
	 * @author caixl
	 * @date Mar 6, 2013
	 * @description TODO 获取标准数据字典类型列表
	 * @param dictionary
	 * @param page
	 * @return Pagination<PDictionary>
	 */
	@Override
	public Pagination<PDictionaryType> getDictTypeStandardList(
			PDictionaryType dictionary, Pagination<PDictionaryType> page) {
		List<PDictionaryType> list = pDictionaryTypeMapper.searchDictTypeStandardList(page.getRowBounds(),dictionary);
		int count = pDictionaryTypeMapper.searchDictTypeStandardTotalCount(dictionary);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 获取标准数据字典类型数据
	 * @return List<PDictionaryType>
	 */
	@Override
	public List<PDictionaryType> getPDictStandardInfo() {
		return pDictionaryTypeMapper.getPdictStandardInfo();
	}

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 加载数据字典类型信息
	 * @param id
	 * @return PDictionaryType
	 */
	@Override
	public PDictionaryType getDictTypeStandardById(String id) {
		return pDictionaryTypeMapper.getDictTypeStandardById(id);
	}

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 保存标准数据字典类型信息
	 * @param pdt
	 * @return int
	 */
	@Override
	public int saveDictTypeStardInfo(PDictionaryType pdt) {
		return pDictionaryTypeMapper.saveDictTypeStardInfo(pdt);
	}

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 更新标准数据字典类型信息
	 * @param pdt
	 * @return int
	 */
	@Override
	public int updateDictTypeStardInfo(PDictionaryType pdt) {
		return pDictionaryTypeMapper.updateDictTypeStardInfo(pdt);
	}

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 删除相关标准数据字典类型信息
	 * @param id
	 * @return int
	 */
	@Override
	public int removeDictTypeStandardByIds(String id) {
		String[] ids = id.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : ids){
			int result = pDictionaryTypeMapper.getCountDictCodeByTypeId(str);
			if(result >0){
			}else{
				list.add(str);
			}
		}
		if(list!=null && list.size()>0){
			pDictionaryTypeMapper.removeDictTypeStandardByIds(list);
		}else{
			return 2;
		}
		return 1;
	}

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 获取标准数据字典列表信息
	 * @param dictionary
	 * @param page
	 * @return Pagination<PDictionary>
	 */
	@Override
	public Pagination<PDictionary> getDictStandardList(PDictionary dictionary,
			Pagination<PDictionary> page) {
		List<PDictionary> list = pDictionaryMapper.searchDictStandardList(dictionary);
		int count = pDictionaryMapper.searchDictStandardTotalCount(dictionary);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 保存数据字典相关信息
	 * @param dictionary
	 * @return int
	 */
	@Override
	public int saveDictStardInfo(PDictionary dictionary) {
		int No = pDictionaryMapper.findMaxDictNo(dictionary.getDicttypeId());
		dictionary.setOrderNo((short)(No+1));
		pDictionaryMapper.insert(dictionary);
		return 1;
	}

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 更新数据字典相关信息
	 * @param dictionary
	 * @return int
	 */
	@Override
	public int updateDictStardInfo(PDictionary dictionary) {
		pDictionaryMapper.updateById(dictionary);
		return 1;
	}

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 判断字典类型编码是否存在
	 * @param pdt
	 * @return boolean
	 */
	@Override
	public boolean selectDictTypeStandard(PDictionaryType pdt) {
		int result = pDictionaryTypeMapper.findDictTypeStandardForCheck(pdt);
		if(result != 0){ //有相同的字典类型存在
			return false;
		}
		return true;
	}

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 根据类型id查询字典并重新排序
	 * @param dicttypeId int
	 */
	@Override
	public int orderDictionaryByTypeId(String dicttypeId) {
		List<PDictionary> list = pDictionaryMapper.findDictStandardByTypeId(dicttypeId);
		int i = 1;
		for(PDictionary pd : list){
			pd.setOrderNo((short)i);
			i++;
			pDictionaryMapper.updateById(pd);
		}
		return 1;
	}

	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 交换排序号
	 * @param dictionary
	 * @return boolean
	 */
	@Override
	public boolean dictionaryOrder(PDictionary dictionary) {
		Boolean bool = false;
		//获取选中的字典,可能会出现重复
		PDictionary dict1 = null;
		List<PDictionary> list = pDictionaryMapper.findDictStandardByOrderNo(dictionary.getDicttypeId(),dictionary.getOrderNo());
		/*
		 * 一个字典类型下面的排序号是唯一的，如果根据一个排序号查询出多个结果，说明排序号重复。此时需要
		 *更新排序号，让排序号唯一。
		 */
		if(list.size()>1){//需要重新排序
			List<PDictionary> list1 = pDictionaryMapper.findDictStandardByTypeId(dictionary.getDicttypeId());
			int i=1;
			for(PDictionary pd : list1){
				pd.setOrderNo((short)i);
				i++;
				pDictionaryMapper.updateById(pd);
			}
			dict1 = pDictionaryMapper.findById(dictionary.getDictId());
			
		}else{
			dict1 = list.get(0);
		}
		//获取需要交换排序号的字典信息,isDeleted 临时作为向上或向下移动的标志，1：向上；2：向下
		int upOrdown = Integer.valueOf(dictionary.getIsDeleted());
		PDictionary dict2 = pDictionaryMapper.findDictStandardForOrder(dictionary.getDicttypeId(),dictionary.getOrderNo(),upOrdown);
		if(dict1 != null && dict2 != null){
			short temp;
			temp = dict1.getOrderNo();
			dict1.setOrderNo(dict2.getOrderNo());
			dict2.setOrderNo(temp);
			pDictionaryMapper.updateById(dict1);
			pDictionaryMapper.updateById(dict2);
			bool = true;
		}
		return bool;
	}

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 根据数据字典标识获取数据字典信息
	 * @param id
	 * @return PDictionary
	 */
	@Override
	public PDictionary getDictStandardById(String id) {
		return pDictionaryMapper.findById(id);
	}

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 删除数据字典相关信息
	 * @param id
	 * @return int
	 */
	@Override
	public int removeDictStandardByIds(String id) {
		String[] ids = id.split(",");
		List<String> list = new ArrayList<String>(ids.length);
		for(String str : ids){
			list.add(str);
		}
		if(list != null  && list.size()>0){
			pDictionaryMapper.removeDictStandardByIds(list);
			return 1;
		}
		return 0;
	}

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 获取各数据源字典类型信息
	 * @return List<PDictType>
	 */
	@Override
	public List<PDictType> getDictTypeDsInfo() {
		List<PDictType> list = pDictTypeMapper.findDictTypeDs();
		//获取各数据源信息
		List<DsObj> listDsObj = dsObjMapper.findAllInfo();
		if(listDsObj!=null && listDsObj.size()>0){
			for(DsObj dsObj : listDsObj){
				PDictType pdt = new PDictType();
				pdt.setTypeId(dsObj.getId());
				pdt.setTypeName(dsObj.getpName());
				list.add(pdt);
			}
		}
		return list;
	}

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 获取某数据源的数据字典类型列表
	 * @param dictType
	 * @param page
	 * @return Pagination<PDictType>
	 */
	@Override
	public Pagination<PDictType> getDictTypeDsList(PDictType dictType,
			Pagination<PDictType> page) {
		List<PDictType> list = pDictTypeMapper.searchDictTypeDsList(page.getRowBounds(),dictType);
		int count = pDictTypeMapper.searchDictTypeDsCount(dictType);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 保存某个数据源的数据字典类型信息
	 * @param dictType
	 * @return int
	 */
	@Override
	public int saveDictTypeDsInfo(PDictType dictType) {
		pDictTypeMapper.insert(dictType);
		return 1;
	}

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 更新某个数据源的数据字典类型信息
	 * @param dictType
	 * @return int
	 */
	@Override
	public int updateDictTypeDsInfo(PDictType dictType) {
		pDictTypeMapper.updateDictTypeDsInfo(dictType);
		return 1;
	}

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 获取某数据源的数据字典类型的信息
	 * @param id
	 * @return PDictType
	 */
	@Override
	public PDictType getDictTypeDsById(String id) {
		return pDictTypeMapper.findById(id);
	}

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 删除数据字典类型信息
	 * @param id
	 * @return int
	 */
	@Override
	public int removeDictTypeDsByIds(String id) {
		String[] ids = id.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : ids){
			int result = pDictTypeMapper.getCountDictCodeByTypeId(str);
			if(result >0){
			}else{
				list.add(str);
			}
		}
		if(list!=null && list.size()>0){
			pDictTypeMapper.removeDictTypeStandardByIds(list);
		}else{
			return 2;
		}
		return 1;
	}

	/**
	 * @author 蔡祥龙
	 * @date Mar 9, 2013
	 * @description TODO 各数据源字典列表信息查询
	 * @param dictValue
	 * @param page
	 * @return Pagination<PDictValue>
	 */
	@Override
	public Pagination<PDictValue> getDictDsList(PDictValue dictValue,
			Pagination<PDictValue> page) {
		List<PDictValue> list = pDictValueMapper.searchDictDsList(page.getRowBounds(),dictValue);
		int count = pDictValueMapper.searchDictDsCount(dictValue);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * @author caixl
	 * @date Mar 9, 2013
	 * @description TODO 加载数据源字典的信息
	 * @param id 字典标识
	 * @return PDictValue
	 */
	@Override
	public PDictValue getDictDsById(String id) {
		return pDictValueMapper.findById(id);
	}

	/**
	 * @author caixl
	 * @date Mar 9, 2013
	 * @description TODO 保存数据字典信息
	 * @param dictValue
	 * @return int
	 */
	@Override
	public int saveDictDsInfo(PDictValue dictValue) {
		return pDictValueMapper.insert(dictValue);
	}

	/**
	 * @author 蔡祥龙
	 * @date Mar 9, 2013
	 * @description TODO 更新数据字典信息
	 * @param dictValue
	 * @return int
	 */
	@Override
	public int updateDictDsInfo(PDictValue dictValue) {
		return pDictValueMapper.updateById(dictValue);
	}

	/**
	 * @author caixl
	 * @date Mar 9, 2013
	 * @description TODO 删除数据源数据字典相关信息
	 * @param id
	 * @return int
	 */
	@Override
	public int removeDictDsByIds(String id) {
		String[] ids = id.split(",");
		List<String> list = new ArrayList<String>(ids.length);
		for(String str : ids){
			list.add(str);
		}
		if(list != null  && list.size()>0){
			pDictValueMapper.removeDictDsByIds(list);
			return 1;
		}
		return 0;
	}

	/**
	 * @author caixl
	 * @date Mar 11, 2013
	 * @description TODO 根据字典类型获取相应的字典信息
	 * @param typeId
	 * @return List<PDictValue>
	 */
	@Override
	public List<PDictValue> getDictDsByDictTypeId(String typeId) {
		return pDictValueMapper.getDictDsByDictTypeId(typeId);
	}

	/**
	 * @author caixl
	 * @date Mar 11, 2013
	 * @description TODO 获取字典相关信息
	 * @param dictId
	 * @return List<PDictionary>
	 */
	@Override
	public List<PDictionary> getDictStandardByIds(String dictId) {
		return pDictionaryMapper.getDictStandardById(dictId);
	}

	/**
	 * @author 根据字典类型获取字典
	 * @date Mar 11, 2013
	 * @description TODO
	 * @param dictId
	 * @return List<PDictionary>
	 */
	@Override
	public List<PDictionary> getDictStandardByDictId(String dictId) {
		return pDictionaryMapper.getDictStandardByDictId(dictId);
	}

	/**
	 * @author caixl
	 * @date Mar 11, 2013
	 * @description TODO 保存匹配的数据字典
	 * @param dictData
	 * @return int
	 */
	@Override
	public int saveDictMatchInfo(String dictData) {
		String[] strs = dictData.split(";");
		for(String data : strs){
			String[] str1s = data.split(",");
			PDictValue pdv = new PDictValue();
			pdv.setValueId(str1s[0]);
			pdv.setDictId(str1s[1]);
			pdv.setDictBaseCode(str1s[2]);
			pDictValueMapper.updateById(pdv);
		}
		return 1;
	}

	/**
	 * @author 蔡祥龙
	 * @date Apr 19, 2013
	 * @description TODO 获取某个数据源数据字典类型的信息
	 * @param dsId
	 * @return List<PDictType>
	 */
	@Override
	public List<PDictType> getAlldicttypeBydsId(String dsId) {
		
		return pDictTypeMapper.getTypeByDsId(dsId);
	}

}
