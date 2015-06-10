package com.zebone.register.validation.repository.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.zebone.register.validation.dao.MetaDataMapper;
import com.zebone.register.validation.domain.DocConf;
import com.zebone.register.validation.domain.DocMapping;
import com.zebone.register.validation.domain.FieldConf;
import com.zebone.register.validation.domain.FieldTableColumn;
import com.zebone.register.validation.repository.MetaDataRepository;

@Repository("metaDataRepository")
public class MetaDataRepositoryImpl implements MetaDataRepository{
	
	
	@Resource
	private MetaDataMapper metaDataMapper;
	
	@Cacheable(value="xsd",key="'xsd_' + #id")
	@Override
	public DocConf getDocConfigById(String id){
		return metaDataMapper.getDocConfigById(id);
	}

    @Cacheable(value="metadata",key="'doc_' + #docId +'_' + #xpath")
    @Override
	public DocMapping getDocMappingByXpathAndDocId(String xpath, String docId) {
		// TODO Auto-generated method stub
		return metaDataMapper.getDocMappingByXpathAndDocId(xpath, docId);
	}

    @Cacheable(value="metadata",key="'field_'+ #id")
    @Override
	public FieldConf getFieldConfById(String id) {
		// TODO Auto-generated method stub
		return metaDataMapper.getFieldConfById(id);
	}

    @Cacheable(value="metadata",key="'fieldId_' + #fieldId")
    @Override
	public FieldConf getFieldConfByFieldId(String fieldId) {
		// TODO Auto-generated method stub
		return metaDataMapper.getFieldConfByFieldId(fieldId);
	}

	@Override
	public DocConf getDocConfigByDocTypeCodeAndDocVersion(String docTypeCode,String docVersion) {
		// TODO Auto-generated method stub
		return metaDataMapper.getDocConfigByDocTypeCodeAndDocVersion(docTypeCode,docVersion);
	}
	
	@Override
	public DocMapping getDocMappingByFieldId(String docId,String fieldId){
		return metaDataMapper.getDocMappingByFieldId(docId, fieldId);
	}

	@Override
	public FieldTableColumn getFieldTableColumn(String columnId) {
		// TODO Auto-generated method stub
		return metaDataMapper.getFieldTableColumn(columnId);
	}
}
