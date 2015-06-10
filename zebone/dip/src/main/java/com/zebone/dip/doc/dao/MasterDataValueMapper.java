package com.zebone.dip.doc.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.doc.vo.MasterDataValue;

@Mapper
public interface MasterDataValueMapper {

	public List<MasterDataValue> listDirectoryValue(MasterDataValue masterDataValue);
	public String getMasterDataTableByName(String masterdataname);
}
