package com.zebone.dip.mdorg.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.md.vo.MasterDataField;


/**
 * @author Yang Ying
 * @version 2013-07-02
 */
@Mapper
public interface MasterDataFieldStruMapper {

	public List<MasterDataField> masterDataFieldList(RowBounds rowBounds, MasterDataField masterDataField );
	public int masterDataFieldTotalCount(MasterDataField masterDataField);
	public MasterDataField selectByPrimaryKey(String id);
	public int fieldNameCount(MasterDataField masterDataField);
	public int saveMasterDataStruFieldInfo(MasterDataField masterDataField);
	public int updateMasterDataStruFieldInfo(MasterDataField masterDataField);
	int deleteMasterDataStruFieldByIds (List<String> list);

    /**
     * 根据主数据ID获取主数据字段信息列表
     * @param masterDataId
     * @return
     */
    public List<MasterDataField> getMDFieldLic (String masterDataId);

    /**
     * 根据主数据ID删除字段信息
     * @param list
     * @return
     */
    int deleteMDFieldByMDIds (List<String> list);
}