/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * wangpeng             New             2012-11-27     字典类型管理service实现类
 */
package com.zebone.btp.app.dict.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zebone.btp.app.dict.dao.DictionaryTypeMapper;
import com.zebone.btp.app.dict.pojo.DictionaryType;
import com.zebone.btp.app.dict.service.DictionaryTypeService;
import com.zebone.btp.core.util.Pagination;
import com.zebone.util.UUIDUtil;

@Service("dictionaryTypeService")
public class DictionaryTypeServiceImpl implements DictionaryTypeService {

	@Resource
	private DictionaryTypeMapper dictionaryTypeMapper;

	/* 测试
	 * (non-Javadoc)
	 * @see com.zebone.btp.app.dict.service.DictionaryService#findDictionary(com.zebone.btp.app.dict.pojo.Dictionary)
	 */
	@Override
	public Pagination<DictionaryType> findPage(DictionaryType dictionaryType) {
		Pagination<DictionaryType> page = new Pagination<DictionaryType>();
		//page.setLimit(15);
		dictionaryType.setParentId("");
		page.setData(dictionaryTypeMapper.findDictionaryTypePage(page.getRowBounds(),dictionaryType));
		page.setTotalCount(dictionaryTypeMapper.findDictionaryTypeTotalPage(dictionaryType));
		return page;
	}
	
	/**
	 * 根据字典类型id删除字典类型
	 * @date 2012-11-27
	 * @param typeIds
	 * @return
	 */
	@Override
	public boolean deleteDictionaryType(String[] typeIds) {
		DictionaryType dictionaryType = null;
		for(String typeId : typeIds){
			dictionaryType = dictionaryTypeMapper.findById(typeId);
			dictionaryType.setIsDeleted(1);
			dictionaryTypeMapper.updateById(dictionaryType);
		}
		return true;
	}

	/**
	 * 获取所有字典类型
	 * @date 2012-11-27
	 * @return
	 */
	@Override
	public List<DictionaryType> getAllDictionaryType() {
		return dictionaryTypeMapper.findAllDictionaryType();
	}

	/**
	 * 根据字典类型id获取字典类型信息
	 * @date 2012-11-27
	 * @param typeId
	 * @return
	 */
	@Override
	public DictionaryType getDictionaryTypeById(String typeId) {
		return dictionaryTypeMapper.findById(typeId);
	}

	/**
	 * 查询字典类型中父类型为空的字典类型
	 * @date 2012-11-27
	 * @return
	 */
	@Override
	public List<DictionaryType> getDictionaryTypeNoPid() {
		return dictionaryTypeMapper.findDictionaryTypeNoPid();
	}

	/**
	 * 字典类型分页查询
	 * @param page
	 * @param dictionaryType
	 * @return
	 */
	@Override
	public Pagination<DictionaryType> getDictionaryTypePage(Pagination<DictionaryType> page, DictionaryType dictionaryType) {
		List<DictionaryType> dictionaryTypeList = dictionaryTypeMapper.findDictionaryTypePage(page.getRowBounds(),dictionaryType);
		int count = dictionaryTypeMapper.findDictionaryTypeTotalPage(dictionaryType);
		page.setData(dictionaryTypeList);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * 插入一条字典类型信息
	 * @date 2012-11-27
	 * @param dictionaryType
	 */
	@Override
	public void insertDictionaryType(DictionaryType dictionaryType) {
		dictionaryType.setTypeId(UUIDUtil.getUuid());
		dictionaryType.setIsDeleted(0);
		dictionaryTypeMapper.insert(dictionaryType);
	}

	/**
	 * 判断字典类型是否存在
	 * @date 2012-11-27
	 * @param dictionaryType
	 * @return boolean
	 */
	@Override
	public boolean selectDictionaryType(DictionaryType dictionaryType) {
		int result = dictionaryTypeMapper.findDictionaryTypeForCheck(dictionaryType);
		if(result != 0){ //有相同的字典类型存在
			return false;
		}
		return true;
	}

	/**
	 * 更新字典类型信息
	 * @date 2012-11-27
	 * @param dictionaryType
	 */
	@Override
	public void updateDictionaryType(DictionaryType dictionaryType) {
		dictionaryTypeMapper.updateById(dictionaryType);
	}

	/**
	 * 保存一条数据字典信息
	 * @date 2012-11-27
	 * @param dictionary
	 */
	@Override
	public boolean saveDictionaryType(DictionaryType dictionaryType) {
		boolean bool = false;
		if(StringUtils.isNotEmpty(dictionaryType.getTypeId())){ //更新
			//dictionaryTypeMapper.updateById(dictionaryType);
			updateDictionaryType(dictionaryType);
			bool = true;
		}else{ //新建
			insertDictionaryType(dictionaryType);
//			dictionaryType.setTypeId(UUIDUtil.getUuid());
//			dictionaryType.setIsDeleted(0);
//			dictionaryTypeMapper.insert(dictionaryType);
			bool = true;
		}		
		return bool;
	}

    @Override
    public List<DictionaryType> getDictionaryTypeForMasterData(String typeCode) {
        return dictionaryTypeMapper.getDictionaryTypeForMasterData(typeCode);
    }
}
