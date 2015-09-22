package com.zebone.dip.doc.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.doc.vo.DocXml;
import com.zebone.dip.metadata.vo.DocConf;
@Mapper
public interface DocMapper {

	DocXml getDocConfById(String id);
	
	public String getFieldType(String fieldCode);
	
	public int updateDocSampleXml(DocXml docXml);
}
