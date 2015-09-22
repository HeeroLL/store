package com.zebone.dip.release.vo;

/**
 * 需要同步的数据表，一个同步main包含多个item
 * @author YinCM
 * @since 2010-9-11 10:31:36
 */
public class DataSyncItem {
	private String id;
	private String mainId;
	private String dataType;
	private String dataContent;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataContent() {
		return dataContent;
	}
	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}
	
}
