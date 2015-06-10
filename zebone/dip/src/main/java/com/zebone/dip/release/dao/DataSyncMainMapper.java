package com.zebone.dip.release.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.release.vo.DataSyncMain;

/**
 * DataSyncMain数据库操作接口
 * @author YinCM
 * @since 2010-9-11 11:08:58
 */
@Mapper
public interface DataSyncMainMapper {

	/**
	 * 插入一条main数据
	 * @param dataSyncMain
	 */
	public void insertDataSyncMain(DataSyncMain dataSyncMain);
	
	/**
	 * 更新一条main数据
	 * @param dataSyncMain
	 */
	public void updateDataSyncMain(DataSyncMain dataSyncMain);
	
	/**
	 * 精确匹配查询DataSyncMain
	 * @param dataSyncMain
	 * @return DataSyncMain的List
	 */
	public List<DataSyncMain> getDataSyncMainEquals(DataSyncMain dataSyncMain);
	
	/**
	 * 模糊匹配查询DataSyncMain
	 * @param dataSyncMain
	 * @return DataSyncMain的List
	 */
	public List<DataSyncMain> getDataSyncMainLikes(DataSyncMain dataSyncMain);
	
	/**
	 * 按按ID数组删除
	 * 
	 * @param ids
	 */
	public void deleteDataSyncMainByIds(String[] ids);
	
}