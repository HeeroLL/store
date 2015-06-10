package com.zebone.register.validation.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.zebone.register.validation.dao.MasterDataMapper;
import com.zebone.register.validation.domain.DicType;
import com.zebone.register.validation.domain.DicValue;
import com.zebone.register.validation.repository.MasterDataRepository;

/**
 * 
 * 主数据Repository 实现类
 * @author 陈阵 
 * @date 2013-8-7 下午1:43:24
 */
@Repository("masterDataRepository")
public class MasterDataRepositoryImpl implements MasterDataRepository{
    
	@Resource
	private MasterDataMapper  masterDataMapper;
	
	
	@Cacheable(value="masterdata",key="'dicType_'+ #dicTypeId")
	public List<DicValue> getDicByDicTypeId(String dicTypeId) {
		// TODO Auto-generated method stub
		return masterDataMapper.getDicByDicTypeId(dicTypeId);
	}
    
	@Cacheable(value="masterdata",key="'dic_' + #typeId")
	public DicType getDicTypeByTypeId(String typeId) {
		// TODO Auto-generated method stub
		return masterDataMapper.getDicTypeByTypeId(typeId);
	}
	
}
