package com.zebone.dip.release.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.dip.release.dao.DataSyncItemMapper;
import com.zebone.dip.release.service.DataSyncItemService;
import com.zebone.dip.release.vo.DataSyncItem;

/**
 * DataSyncItemService
 * @author YinCM
 * @since 2010-9-13 16:08:26
 */
@Service
public class DataSyncItemServiceImpl implements DataSyncItemService{

	@Resource
	DataSyncItemMapper dataSyncItemMapper;
	
	@Override
	public void insertDataSyncItem(DataSyncItem dataSyncItem) {
		dataSyncItemMapper.insertDataSyncItem(dataSyncItem);	
	}

	@Override
	public void updateDataSyncItem(DataSyncItem dataSyncItem) {
		dataSyncItemMapper.updateDataSyncItem(dataSyncItem);
		
	}

	@Override
	public List<DataSyncItem> getAllDataSyncItem() {
		DataSyncItem dataSyncItem = new DataSyncItem();
		List<DataSyncItem> list = dataSyncItemMapper.getDataSyncItemLikes(dataSyncItem);
		return list;
	}

	@Override
	public void deleteDataSyncItemByIds(int[] ids) {
		dataSyncItemMapper.deleteDataSyncItemByIds(ids);
	}

	@Override
	public void deleteDataSyncItemByMainId(String mainId) {
		dataSyncItemMapper.deleteDataSyncItemByMainId(mainId);
		
	}

	@Override
	public List<DataSyncItem> getAllDataSyncItemByMainId(String id) {
		DataSyncItem dataSyncItem = new DataSyncItem();
		dataSyncItem.setMainId(id);
		List<DataSyncItem> list = dataSyncItemMapper.getDataSyncItemEquals(dataSyncItem);
		return list;
	}
	
	public DataSyncItemMapper getDataSyncItemMapper() {
		return dataSyncItemMapper;
	}

	public void setDataSyncItemMapper(DataSyncItemMapper dataSyncItemMapper) {
		this.dataSyncItemMapper = dataSyncItemMapper;
	}

}
