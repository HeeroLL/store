package com.zebone.dnode.etl.extract.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class TableParameter {
	
	@XStreamAlias("fromName")
	@XStreamAsAttribute
	private String fromtTableName;
	
	@XStreamAlias("toName")
	@XStreamAsAttribute
	private String toTableName;
	
	@XStreamAlias("desc")
	@XStreamAsAttribute
	private String tableDesc;
	
	private String fromDataSource;
	
	private String toDataSource;
	
	private String fromTime;
	
	private String toTime;

	public String getFromtTableName() {
		return fromtTableName;
	}

	public void setFromtTableName(String fromtTableName) {
		this.fromtTableName = fromtTableName;
	}

	public String getToTableName() {
		return toTableName;
	}

	public void setToTableName(String toTableName) {
		this.toTableName = toTableName;
	}

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}

	public String getFromDataSource() {
		return fromDataSource;
	}

	public void setFromDataSource(String fromDataSource) {
		this.fromDataSource = fromDataSource;
	}

	public String getToDataSource() {
		return toDataSource;
	}

	public void setToDataSource(String toDataSource) {
		this.toDataSource = toDataSource;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TableParameter [fromtTableName=");
		builder.append(fromtTableName);
		builder.append(", toTableName=");
		builder.append(toTableName);
		builder.append(", tableDesc=");
		builder.append(tableDesc);
		builder.append(", fromDataSource=");
		builder.append(fromDataSource);
		builder.append(", toDataSource=");
		builder.append(toDataSource);
		builder.append(", fromTime=");
		builder.append(fromTime);
		builder.append(", toTime=");
		builder.append(toTime);
		builder.append("]");
		return builder.toString();
	}

    
   
}
