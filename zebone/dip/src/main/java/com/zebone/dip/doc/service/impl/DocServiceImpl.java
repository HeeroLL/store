package com.zebone.dip.doc.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.zebone.dip.metadata.dao.DocConfMapper;
import com.zebone.dip.metadata.dao.DocMappingMapper;
import com.zebone.dip.metadata.dao.FieldConfMapper;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.zebone.dip.dictionary.vo.DipDictionary;
import com.zebone.dip.doc.dao.DocDictionaryMapper;
import com.zebone.dip.doc.dao.DocMapper;
import com.zebone.dip.doc.dao.MasterDataValueMapper;
import com.zebone.dip.doc.service.DocService;
import com.zebone.dip.doc.vo.DocXml;
import com.zebone.dip.doc.vo.MasterDataValue;
import com.zebone.dip.metadata.vo.DocConf;
import com.zebone.dip.metadata.vo.DocMapping;
import com.zebone.dip.metadata.vo.FieldConf;
@Service("docService")
public class DocServiceImpl implements DocService {

	
	@Resource
	private DocMappingMapper docMappingMapper;
	@Resource
	private DocMapper docMapper;
	@Resource
	private DocDictionaryMapper docDictionaryMapper;
	@Resource
	private MasterDataValueMapper masterDataValueMapper;
	
    @Resource
    private DocConfMapper docConfMapper;
    @Resource
    private FieldConfMapper fieldConfMapper;

	@Override
	public String getDocXmlInfo(String id) throws DocumentException,
			IOException {
		DocXml doc = docMapper.getDocConfById(id);
		return doc.getDocXml();
	}
	public String getXmlTestInfo(String id)throws DocumentException,IOException
	{
		DocXml doc = docMapper.getDocConfById(id);
		return doc.getXmlSample();
	}

	@Override
	public DocXml getDocWordInfo(String id) throws DocumentException,
			IOException {
		// TODO Auto-generated method stub
		return docMapper.getDocConfById(id);
	}

    @Override
    public List<DocConf> getDocInfoLic() {
        return docConfMapper.getDocInfoLic();
    }
    public FieldConf getFieldInfo(String fieldCode)
    {
    	
    	return fieldConfMapper.selectByFieldId(fieldCode);
    }
    public FieldConf getFieldInfoByid(String id)
    {
    	
    	return fieldConfMapper.selectByPrimaryKey(id);
    }
    
    public List<DipDictionary> listDirectoryValueByName(DipDictionary dipDictionary)
    {
    	return docDictionaryMapper.listDirectoryValueByName(dipDictionary);
    }
    
    public List<MasterDataValue> listMasterDateByName(String masterdataname,String query)
    {
    	String tableName=masterDataValueMapper.getMasterDataTableByName(masterdataname);
    	MasterDataValue masterDataValue = new MasterDataValue();
    	masterDataValue.setTableName(tableName);
    	masterDataValue.setMdname(query);
    	return masterDataValueMapper.listDirectoryValue(masterDataValue);
    }
    
    public DocMapping getDocMappingInfo(String id)
    {
    	return docMappingMapper.selectByPrimaryKey(id);
    }
    public DipDictionary getDirectoryRandom(String dicttype_id)
    {
    	return docDictionaryMapper.getDirectoryRandom(dicttype_id);
    }
    public boolean updateDocSampleXml(DocXml docXml)
    {
    	try{
    		docMapper.updateDocSampleXml(docXml);
		}catch(Exception e){
			//log.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
    	return true;
    }
    
    public DipDictionary listDirectoryValueByDocId(String docid)
    {
    	return docDictionaryMapper.listDirectoryValueByDocId(docid);
    }
}
