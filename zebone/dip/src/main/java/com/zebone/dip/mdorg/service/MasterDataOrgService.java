package com.zebone.dip.mdorg.service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.vo.DipDictionaryType;
import com.zebone.dip.md.vo.MasterData;
import com.zebone.dip.md.vo.MasterDataField;

import java.util.List;

/**
 * 主数据结构管理服务
 * @author lin fengjian
 * @version 2014-4-25 下午2:10
 */
public interface MasterDataOrgService {
	
	public Pagination<MasterData> MasterDataStruList(Pagination<MasterData> page,MasterData masterData);
	public Pagination<MasterDataField> masterDataFieldPage(Pagination<MasterDataField> page,MasterDataField masterDataField);
	public MasterData getmasterDataStruInfoById(String id);
	public int saveMasterDataStruInfo(MasterData masterData);
	public int updateMasterDataStruInfo(MasterData masterData);
	public int removeMasterDataStruByIds(String[] ids);
	public MasterDataField getMasterDataStruFieldInfoById(String id);
	public int saveMasterDataStruFieldInfo(MasterDataField masterDataField);
	public int updateMasterDataStruFieldInfo(MasterDataField masterDataField);
	public int removeMasterDataStruFieldByIds(String[] ids);

    /**
     * 判断主数据表是否已经创建
     * @param mdTableName
     * @return
     */
    public int isMDTableExist(String mdTableName);

    /**
     * 获取匹配的值域信息
     * @param fieldType
     * @return
     */
    List<DipDictionaryType> getMatchInfo(String fieldType, String name);

    /**
     * 生成建表sql
     * @param masterDataId
     * @param tableName
     * @return
     */
    public String generateSql(String masterDataId,String tableName);
	
}
