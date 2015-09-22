package com.zebone.dip.doc.service;

import java.io.IOException;
import java.util.List;

import org.dom4j.DocumentException;

import com.zebone.dip.dictionary.vo.DipDictionary;
import com.zebone.dip.doc.vo.DocXml;
import com.zebone.dip.doc.vo.MasterDataValue;
import com.zebone.dip.metadata.vo.DocConf;
import com.zebone.dip.metadata.vo.DocMapping;
import com.zebone.dip.metadata.vo.FieldConf;



public interface DocService {
	public String getDocXmlInfo(String id)throws DocumentException,IOException;
	public String getXmlTestInfo(String id)throws DocumentException,IOException;
	public DocXml getDocWordInfo(String id)throws DocumentException,IOException;

    /**
     * 获取所有文档信息
     *
     * @return List<DocConf>
     */
    public List<DocConf> getDocInfoLic();
    public FieldConf getFieldInfo(String fieldCode);
    
    public List<DipDictionary> listDirectoryValueByName(DipDictionary dipDictionary);
    public List<MasterDataValue> listMasterDateByName(String masterdataname,String query);
    public DocMapping getDocMappingInfo(String id);
    public DipDictionary getDirectoryRandom(String dicttype_id);
    public boolean updateDocSampleXml(DocXml docXml);
    public FieldConf getFieldInfoByid(String id);
    public DipDictionary listDirectoryValueByDocId(String docid);
}
