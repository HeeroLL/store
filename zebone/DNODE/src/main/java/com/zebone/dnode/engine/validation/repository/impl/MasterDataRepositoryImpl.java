package com.zebone.dnode.engine.validation.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.zebone.dnode.engine.validation.domain.DicType;
import com.zebone.dnode.engine.validation.domain.DicValue;
import com.zebone.dnode.engine.validation.mapper.MasterDataMapper;
import com.zebone.dnode.engine.validation.repository.MasterDataRepository;

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
	
	
	@Cacheable(value="dnode",key="'pd_'+ #dicTypeId")
	public List<DicValue> getDicByDicTypeId(String dicTypeId) {
		// TODO Auto-generated method stub
		return masterDataMapper.getDicByDicTypeId(dicTypeId);
	}
    
	@Cacheable(value="dnode",key="'pdt_' + #typeId")
	public DicType getDicTypeByTypeId(String typeId) {
		// TODO Auto-generated method stub
		return masterDataMapper.getDicTypeByTypeId(typeId);
	}
	
}
