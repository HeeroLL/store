package com.zebone.dip.release.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zebone.dip.release.vo.DataSyncMain;

/**
 * 用于操作DataSyncMain
 * @author YinCM
 * @since 
 * 
 */
@Service
public interface DataSyncMainService {
	
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
	 * 按按ID数组删除
	 * 
	 * @param ids
	 */
	public void deleteDataSyncMainByIds(String[] ids);
 
	/**
	 * 根据id获取DataSyncItem数据
	 * @param id
	 */
	public DataSyncMain getDataSyncMainById(String id);
	
	/**
	 * 查找所有dataMain对象
	 * @return
	 */
	public List<DataSyncMain> getAllDataSyncMain();
}
