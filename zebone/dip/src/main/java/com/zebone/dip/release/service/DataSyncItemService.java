package com.zebone.dip.release.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zebone.dip.release.vo.DataSyncItem;
import com.zebone.dip.release.vo.DataSyncMain;

/**
 * 用于操作DataSyncMain
 * @author YinCM
 * @since 
 * 
 */
@Service
public interface DataSyncItemService {
	
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
	public List<DataSyncItem> getAllDataSyncItem();
	
	
	/**
	 * 按按ID数组删除
	 * 
	 * @param ids
	 */
	public void deleteDataSyncItemByIds(int[] ids);
	
	/**
	 * 按主表id删除所有字表信息
	 */
	public void deleteDataSyncItemByMainId(String mainId);

	/**
	 * 按主表id获取所有子表信息
	 */
	public List<DataSyncItem> getAllDataSyncItemByMainId(String id);
}
