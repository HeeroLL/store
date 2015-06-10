package com.zebone.dip.release.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.dip.release.dao.DataSyncMainMapper;
import com.zebone.dip.release.service.DataSyncMainService;
import com.zebone.dip.release.vo.DataSyncMain;

/**
 * DataSyncMainService实现类
 * @author YinCM
 * @since 2010-9-13 15:55:32
 */
@Service
public class DataSyncMainServiceImpl implements DataSyncMainService{

	@Resource
	DataSyncMainMapper dataSyncMainMapper;
	
	
	@Override
	public void insertDataSyncMain(DataSyncMain dataSyncMain) {
		dataSyncMainMapper.insertDataSyncMain(dataSyncMain);
	}

	@Override
	public void updateDataSyncMain(DataSyncMain dataSyncMain) {
		dataSyncMainMapper.updateDataSyncMain(dataSyncMain);
	}

	@Override
	public void deleteDataSyncMainByIds(String[] ids) {
		dataSyncMainMapper.deleteDataSyncMainByIds(ids);
	}

	@Override
	public List<DataSyncMain> getAllDataSyncMain() {
		DataSyncMain dataSyncMain = new DataSyncMain();
		List<DataSyncMain> list = dataSyncMainMapper.getDataSyncMainEquals(dataSyncMain);
		return list;
	}

	
	
	@Override
	public DataSyncMain getDataSyncMainById(String id) {
		DataSyncMain dataSyncMain = new DataSyncMain();
		dataSyncMain.setId(id);
		List<DataSyncMain> list = dataSyncMainMapper.getDataSyncMainEquals(dataSyncMain);
		if(list!=null){
			dataSyncMain = list.get(0);
		}else{
			dataSyncMain = null;
		}
		return dataSyncMain;
	}

	public DataSyncMainMapper getDataSyncMainMapper() {
		return dataSyncMainMapper;
	}

	public void setDataSyncMainMapper(DataSyncMainMapper dataSyncMainMapper) {
		this.dataSyncMainMapper = dataSyncMainMapper;
	}

}
