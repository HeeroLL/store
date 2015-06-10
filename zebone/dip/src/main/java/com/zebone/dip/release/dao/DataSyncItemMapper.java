package com.zebone.dip.release.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.release.vo.DataSyncItem;

/**
 * DataSyncItem数据库操作接口
 * @author YinCM
 *
 */
@Mapper
public interface DataSyncItemMapper {

	/**
	 * 插入一条item数据
	 * @param dataSyncItem
	 */
	public void insertDataSyncItem(DataSyncItem dataSyncItem);
	
	/**
	 * 更新一条item数据
	 * @param dataSyncItem
	 */
	public void updateDataSyncItem(DataSyncItem dataSyncItem);
	
	/**
	 * 精确匹配查询DataSyncItem
	 * @param dataSyncItem
	 * @return DataSyncItem的List
	 */
	public List<DataSyncItem> getDataSyncItemEquals(DataSyncItem dataSyncItem);
	
	/**
	 * 模糊匹配查询DataSyncItem
	 * @param dataSyncItem
	 * @return DataSyncItem的List
	 */
	public List<DataSyncItem> getDataSyncItemLikes(DataSyncItem dataSyncItem);
	
	/**
	 * 按按ID数组删除
	 * 
	 * @param ids
	 */
	public void deleteDataSyncItemByIds(int[] ids);
	
	/**
	 * 按按MAIN_ID删除 
	 * @param ids
	 */
	public void deleteDataSyncItemByMainId(String mainId);
}