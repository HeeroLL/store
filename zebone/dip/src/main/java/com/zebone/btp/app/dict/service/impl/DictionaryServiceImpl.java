/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * wangpeng             New             2012-11-27     字典管理service实现类
 */
package com.zebone.btp.app.dict.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zebone.btp.app.dict.dao.DictionaryMapper;
import com.zebone.btp.app.dict.pojo.Dictionary;
import com.zebone.btp.app.dict.service.DictionaryService;
import com.zebone.btp.core.util.ChineseToPinYin;
import com.zebone.btp.core.util.Pagination;
import com.zebone.util.UUIDUtil;

@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {
	
	@Resource
	private DictionaryMapper dictionaryMapper;

	/* 测试
	 * (non-Javadoc)
	 * @see com.zebone.btp.app.dict.service.DictionaryService#findDictionary(com.zebone.btp.app.dict.pojo.Dictionary)
	 */
	@Override
	public Pagination<Dictionary> findPage(Dictionary dictionary) {
		Pagination<Dictionary> page = new Pagination<Dictionary>();
		//page.setLimit(15);
		dictionary.setDicttypeId("typeid1");
		dictionary.setDictCode("code1");
		page.setData(dictionaryMapper.findDictionaryPage(dictionary));
		page.setTotalCount(dictionaryMapper.findDictionaryTotalPage(dictionary));
		return page;
	}

	/**
	 * 根据字典id删除字典信息
	 * @date 2012-11-27
	 * @param dictIds
	 * @return
	 */
	@Override
	public boolean deleteDictionary(String[] dictIds) {
		Dictionary dictionary = null;
		for(String dictId : dictIds){
			dictionary = dictionaryMapper.findById(dictId);
			dictionary.setIsDeleted(1);
			dictionaryMapper.updateById(dictionary);
		}
		return true;
	}

	/**
	 * 根据字典编码查询字典信息
	 * @date 2012-11-27
	 * @param dictCode
	 * @return
	 */
	@Override
	public Dictionary getDictionaryByCode(String dictCode) {
		Dictionary dictionary = dictionaryMapper.findByCode(dictCode);
		if(dictionary != null){
			return dictionary;
		}			
		return null;
	}

	/**
	 * 根据字典id查询字典信息
	 * @date 2012-11-27
	 * @param dictId
	 * @return
	 */
	@Override
	public Dictionary getDictionaryById(String dictId) {
		Dictionary dictionary = dictionaryMapper.findById(dictId);
		if(dictionary != null){
			return dictionary;
		}			
		return null;
	}

	/**
	 * 根据字典类型编码 查询字典类型下所有的字典信息.查询结果按照排序号正序
	 * @date 2012-11-27
	 * @param typeCode
	 * @return
	 */
	@Override
	public List<Dictionary> getDictionaryByTypeCode(String typeCode) {
		return dictionaryMapper.findDictionaryByTypeCode(typeCode);
	}

	/**
	 * 查询字典信息（不分页）
	 * @date 2012-11-27
	 * @param page
	 * @param dictionary
	 */
	@Override
	public Pagination<Dictionary> getDictionaryPage(Pagination<Dictionary> page, Dictionary dictionary) {
		List<Dictionary> dictionaryList = dictionaryMapper.findDictionaryPage(dictionary);
		int count = dictionaryMapper.findDictionaryTotalPage(dictionary);
		page.setData(dictionaryList);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * 插入一条数据字典信息
	 * @date 2012-11-27
	 * @param dictionary
	 */
	@Override
	public void insertDictionary(Dictionary dictionary) {
		dictionary.setDictId(UUIDUtil.getUuid());
		dictionary.setIsDeleted(0);
		String[] py = ChineseToPinYin.chineseToPinyinAndShort(dictionary.getDictName()).split(",");
		if(py.length>1){
			dictionary.setNameJianpin(py[1]);
		}else{
			dictionary.setNameJianpin(py[0]);
		}
		dictionary.setNamePinyin(py[0]);
		//获得最大的排序号,排序号包括已经删除的
		int no = dictionaryMapper.findDictionaryMaxNo(dictionary.getDicttypeId());
		dictionary.setOrderNo(no + 1);
		dictionary.setIsDeleted(0);
		dictionaryMapper.insert(dictionary);
	}

	/**
	 * 更新一条数据字典信息
	 * @date 2012-11-27
	 * @param dictionary
	 */
	@Override
	public void updateDictionary(Dictionary dictionary) {
		String[] py = ChineseToPinYin.chineseToPinyinAndShort(dictionary.getDictName()).split(",");
		if(py.length>1){
			dictionary.setNameJianpin(py[1]);
		}else{
			dictionary.setNameJianpin(py[0]);
		}
		dictionary.setNamePinyin(py[0]);
		dictionaryMapper.updateById(dictionary);		
	}

	/**
	 * 保存一条数据字典信息
	 * @date 2012-11-27
	 * @param dictionary
	 */
	@Override
	public boolean saveDictionary(Dictionary dictionary) {
		boolean bool = false;
		if(StringUtils.isNotEmpty(dictionary.getDictId())){ //更新
			updateDictionary(dictionary);
//			String[] py = ChineseToPinYin.chineseToPinyinAndShort(dictionary.getDictName()).split(",");
//			if(py.length>1){
//				dictionary.setNameJianpin(py[1]);
//			}else{
//				dictionary.setNameJianpin(py[0]);
//			}			
//			dictionary.setNamePinyin(py[0]);
//			dictionaryMapper.updateById(dictionary);
			bool = true;
		}else{ //新建
			insertDictionary(dictionary);
//			dictionary.setDictId(UUIDUtil.getUuid());
//			dictionary.setIsDeleted(0);
//			String[] py = ChineseToPinYin.chineseToPinyinAndShort(dictionary.getDictName()).split(",");
//			if(py.length>1){
//				dictionary.setNameJianpin(py[1]);
//			}else{
//				dictionary.setNameJianpin(py[0]);
//			}
//			dictionary.setNamePinyin(py[0]);
//			//获得最大的排序号,排序号包括已经删除的
//			int no = dictionaryMapper.findDictionaryMaxNo(dictionary.getDicttypeId());
//			dictionary.setOrderNo(no + 1);
//			dictionary.setIsDeleted(0);
//			dictionaryMapper.insert(dictionary);
			bool = true;
		}		
		return bool;
	}

	/**
	 * 交换排序号
	 * @date 2012-11-29
	 * @param dictionary
	 * @return
	 */
	@Override
	public Boolean dictionaryOrder(Dictionary dictionary) {
		Boolean bool = false;
		//获取选中的字典,可能会出现重复
		Dictionary dict1 = null;
		List<Dictionary> dictList = dictionaryMapper.findDictionaryByOrderNo(dictionary.getDicttypeId(),dictionary.getOrderNo());
		/*
		 * 一个字典类型下面的排序号是唯一的，如果根据一个排序号查询出多个结果，说明排序号重复。此时需要
		 *更新排序号，让排序号唯一。
		 */
		if(dictList.size()>1){ //需要重新排序
			orderDictionaryByTypeId(dictionary.getDicttypeId());
//			List<Dictionary> dList = dictionaryMapper.findDictionaryByTypeId(dictionary.getDicttypeId());
//			int i = 1;
//			for(Dictionary d:dList){				
//				d.setOrderNo(i);
//				i++;
//				dictionaryMapper.updateById(d);				
//			}			
			dict1 = dictionaryMapper.findById(dictionary.getDictId());
		}else{
			dict1 = dictList.get(0);
		}
		//获取需要交换排序号的字典信息,isDeleted 临时作为向上或向下移动的标志，1：向上；2：向下
		int upOrdown = dictionary.getIsDeleted();
		Dictionary dict2 = dictionaryMapper.findDictionaryForOrder(dictionary.getDicttypeId(), dictionary.getOrderNo(), upOrdown);
		if(dict1 != null && dict2 != null){
			int temp;
			temp = dict1.getOrderNo();
			dict1.setOrderNo(dict2.getOrderNo());
			dict2.setOrderNo(temp);
			dictionaryMapper.updateById(dict1);
			dictionaryMapper.updateById(dict2);
			bool = true;
		}		
		return bool;
	}

	/**
	 * 根据类型编码和字典编码获取字典值
	 * @date 2012-12-07
	 * @param typeCode
	 * @param dictCode
	 * @return
	 */
	@Override
	public Dictionary findDictionaryByTypeCodeAndDictCode(String typeCode, String dictCode) {
		Dictionary dictionary = dictionaryMapper.findDictionaryByTypeCodeAndDictCode(typeCode, dictCode);
		if(dictionary != null ){
			return dictionary;
		}else{
			return null;
		}
	}

	/**
	 * 根据类型id查询字典并重新排序
	 * @date 2012-12-08
	 * @param dicttypeId
	 */
	@Override
	public void orderDictionaryByTypeId(String dicttypeId) {
		List<Dictionary> dList = dictionaryMapper.findDictionaryByTypeId(dicttypeId);
		int i = 1;
		for(Dictionary d:dList){				
			d.setOrderNo(i);
			i++;
			dictionaryMapper.updateById(d);				
		}	
	}	

}
