package com.zebone.dnode.etl.load.pojo;

import java.util.List;
import java.util.Map;

import com.zebone.dnode.etl.load.convert.Convert;

public class LoadParameter {
	
	private String fromSql;
	
	private  Map<String,String> toTable;
	
	
	private Map<String, List<Convert>> colConvertMap;

	public String getFromSql() {
		return fromSql;
	}

	public void setFromSql(String fromSql) {
		this.fromSql = fromSql;
	}


	public Map<String, String> getToTable() {
		return toTable;
	}

	public void setToTable(Map<String, String> toTable) {
		this.toTable = toTable;
	}


	public Map<String, List<Convert>> getColConvertMap() {
		return colConvertMap;
	}

	public void setColConvertMap(Map<String, List<Convert>> colConvertMap) {
		this.colConvertMap = colConvertMap;
	}

    
}
