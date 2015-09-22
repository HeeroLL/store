package com.zebone.dip.doc.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.dictionary.vo.DipDictionary;
@Mapper
public interface DocDictionaryMapper {
	public List<DipDictionary> listDirectoryValueByName(DipDictionary dipDictionary);
	public DipDictionary getDirectoryRandom(String dicttype_id);
	 public DipDictionary listDirectoryValueByDocId(String docid);
}
