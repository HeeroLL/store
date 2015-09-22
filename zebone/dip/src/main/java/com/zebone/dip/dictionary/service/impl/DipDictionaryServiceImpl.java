package com.zebone.dip.dictionary.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.dao.DipDictionaryMapper;
import com.zebone.dip.dictionary.dao.DipDictionaryTypeMapper;
import com.zebone.dip.dictionary.service.DipDictionaryService;
import com.zebone.dip.dictionary.vo.DipDictionary;
import com.zebone.dip.dictionary.vo.DipDictionaryType;
import com.zebone.dip.md.vo.NameCode;
/**
 * 字典Service Implementation
 * @author YinCm
 * @version 2013-7-18 上午10:10:20
 */
@Service("dipDictionaryService")
public class DipDictionaryServiceImpl implements DipDictionaryService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private DipDictionaryMapper dipDictionaryMapper;
	@Resource
	private DipDictionaryTypeMapper dipDictionaryTypeMapper;
	
	
	@Override
	public int countDipDictionaryByParentId(String id) {
		
		int count = this.dipDictionaryTypeMapper.getDipDictionaryTypeByParentIdCount(id);
		return count;
	}
 
	@Override
	public List<String> getTypeCodeListByParentIdAndTypeName(
			DipDictionaryType dictType) {
		List<String> code_list = this.dipDictionaryTypeMapper.getTypeCodeListByParentIdAndTypeName(dictType);
		return code_list;
	}



	@Override
	public int countTypeByParentIdAndName(DipDictionaryType ddt) {
		int count = this.dipDictionaryTypeMapper.countTypeByParentIdAndName(ddt);
		return count;
	}



	@Override
	public boolean addDipDictionaryType(DipDictionaryType dt) {
		try{
			dipDictionaryTypeMapper.addDipDictionaryType(dt);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}



	@Override
	public boolean deleteDipDictionaryTypeByIds(String[] ids) {
		try{
			if(this.dipDictionaryTypeMapper.checkDicTypeChildrenBeforeDelete(ids)>0 || this.dipDictionaryMapper.checkDicTypeChildrenDicBeforeDelete(ids)>0){
				return false;
			}
			this.dipDictionaryTypeMapper.deleteDipDictionaryTypeByIds(ids);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	
	@Override
	public boolean checkDicTypeChildrenBeforeDelete(String[] ids) {
		if(this.dipDictionaryTypeMapper.checkDicTypeChildrenBeforeDelete(ids)>0 || this.dipDictionaryMapper.checkDicTypeChildrenDicBeforeDelete(ids)>0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public Pagination<DipDictionaryType> searchDipDictionaryType(DipDictionaryType dictType, Pagination<DipDictionaryType> page) {
		List<DipDictionaryType> ddt = dipDictionaryTypeMapper.searchDipDictionaryType(page.getRowBounds(), dictType);
		int totalCount = dipDictionaryTypeMapper.searchDipDictionaryTypeCount(dictType);
		page.setTotalCount(totalCount);
		page.setData(ddt);
		return page;
	}

	@Override
	public List<DipDictionaryType> getAllDipDictionaryType() {
		List<DipDictionaryType> lst = dipDictionaryTypeMapper.getAllDipDictionaryType();
		return lst;
	}

	@Override
	public boolean updateDipDictionaryType(DipDictionaryType dictType) {
		try{
			dipDictionaryTypeMapper.updateDipDictionaryType(dictType);
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	@Override
	public DipDictionaryType getDipDictionaryTypeById(String id) {
		DipDictionaryType ddt = dipDictionaryTypeMapper.getDipDictionaryTypeById(id);
		return ddt;
	}



	public DipDictionaryTypeMapper getDipDipDictionaryTypeMapper() {
		return dipDictionaryTypeMapper;
	}

	public void setDipDictionaryTypeMapper(
			DipDictionaryTypeMapper dipDictionaryTypeMapper) {
		this.dipDictionaryTypeMapper = dipDictionaryTypeMapper;
	}
	
	///////////////////////////////////////////////////////////////////////////
	//////////////////Dictionary elements/////////////////////////////////////

	@Override
	public boolean deleteDipDictionaryByIds(String[] ids) {
		boolean success;
		try{
			this.dipDictionaryMapper.deleteDipDictionaryByIds(ids);
			success = true;
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			success = false;
		}
		return success;
	}



	@Override
	public boolean addDipDictionary(DipDictionary dic) {
		boolean success;
		try{
			this.dipDictionaryMapper.addDipDictionary(dic);
			success = true;
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			success = false;
		}
		return success;
	}



	@Override
	public Pagination<DipDictionary> searchDipDictionary(DipDictionary dic, Pagination<DipDictionary> page) {
		List<DipDictionary> ddt = dipDictionaryMapper.searchDipDictionary(page.getRowBounds(), dic);
		int totalCount = dipDictionaryMapper.searchDipDictionaryCount(dic);
		page.setTotalCount(totalCount);
		page.setData(ddt);
		return page;
	}



	@Override
	public DipDictionary findDipDictionaryById(String id) {
		DipDictionary dd = this.dipDictionaryMapper.findDipDictionaryById(id);
		return dd;
	}



	@Override
	public boolean updateDipDictionary(DipDictionary dic) {
		boolean success;
		try{
			this.dipDictionaryMapper.updateDipDictionary(dic);
			success = true;
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			success = false;
		}
		return success;
	}

    @Override
    public List<NameCode> getDictNameListByCode(String typeCode) {
        return dipDictionaryMapper.getDictNameListByType(typeCode);
    }

    /**
	 * 查询一级字典类型
	 * @author caixl
	 * @date Dec 5, 2013
	 * @return 
	 * List<DipDictionaryType>
	 */
	@Override
	public List<DipDictionaryType> getLevel1DictType() {
		return dipDictionaryTypeMapper.getLevel1DictType();
	}

	/**
	 * 根据标准编码获取获取某字典类型
	 * @param code
	 * @return 
	 * List<NameCode>
	 */
	@Override
	public List<NameCode> getTypeByCode(String code) {
		return dipDictionaryMapper.getDictByCode(code);
	}

	/**
	 * 按字典类别和编码查找字典
	 * @param id
	 * @return
	 */
	@Override
	public NameCode findDipDictionaryByTypeAndId(String type, String code) {
		return dipDictionaryMapper.findDipDictionaryByTypeAndId(type,code);
	}

}
