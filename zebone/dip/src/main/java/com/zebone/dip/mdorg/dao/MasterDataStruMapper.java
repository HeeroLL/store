package com.zebone.dip.mdorg.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.md.vo.MasterData;

/**
 * @author Yang Ying
 * @version 2013-07-02
 */

@Mapper
public interface MasterDataStruMapper {

	public List<MasterData> masterDataStruList(RowBounds rowBounds, MasterData masterData );
	public int masterDataStruTotalCount(MasterData masterData);
	public MasterData getmasterDataStruInfoById(String id);
	public int countMasterDataName(MasterData masterData);
	public int saveMasterDataStruInfo(MasterData masterData);
	public int updateMasterDataStruInfo(MasterData masterData);
	int existsFieldSet (String id);
	int deleteMasterDataStruByIds (List<String> list);

    /**
     * 判断主数据表是否已经创建
     * @param mdTableName
     * @return
     */
    int countMDTable(String mdTableName);

    /**
     * 判断主数据表名是否已经存在
     * @param masterData
     * @return
     */
    public int countMDTableName(MasterData masterData);

    /**
     * 判断主数据编码是否已经存在
     * @param masterData
     * @return
     */
    public int countMDCode(MasterData masterData);

    /**
     * 根据主数据ID获取主数据表名
     * @param id
     * @return
     */
    public String getMDTableName(String id);
}