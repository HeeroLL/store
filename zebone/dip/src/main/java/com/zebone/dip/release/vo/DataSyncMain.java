package com.zebone.dip.release.vo;

import java.util.List;

/**
 * 数据同步配置和记录表
 * @author YinCM
 * @since 2010-9-11 10:31:36
 */
public class DataSyncMain {
	private String id;
	private String syncFlag;
	private String lastSyncTime;
	private String currentSyncTime;
	private String sysName;
	private String isAll;
	private String dataSourceId;
	private String wsUrl;
	private List<DataSyncItem> dataSyncItemList;
	
	public List<DataSyncItem> getDataSyncItemList() {
		return dataSyncItemList;
	}
	public void setDataSyncItemList(List<DataSyncItem> dataSyncItemList) {
		this.dataSyncItemList = dataSyncItemList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSyncFlag() {
		return syncFlag;
	}
	public void setSyncFlag(String syncFlag) {
		this.syncFlag = syncFlag;
	}
	public String getLastSyncTime() {
		return lastSyncTime;
	}
	public void setLastSyncTime(String lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}
	public String getCurrentSyncTime() {
		return currentSyncTime;
	}
	public void setCurrentSyncTime(String currentSyncTime) {
		this.currentSyncTime = currentSyncTime;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getIsAll() {
		return isAll;
	}
	public void setIsAll(String isAll) {
		this.isAll = isAll;
	}
	public String getDataSourceId() {
		return dataSourceId;
	}
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
	public String getWsUrl() {
		return wsUrl;
	}
	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}
	
}
