package com.zebone.dnode.engine.clean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SourceDataTransfer {
	
	private static final String ROWNUM = "ROWNUM";
	
	public List<String> dataTansferToSql(String targetTable,List<Map<String,Object>> data){
	    List<String> sqls = new ArrayList<String>();
		StringBuilder iCol = null;
		StringBuilder iColv = null;
		String iCols = "";
		String iColvs = "";
		StringBuilder sql = null;
		for(Map<String,Object> dataMap : data){		
		    iCol = new StringBuilder();
		    iColv = new StringBuilder();
		    sql = new StringBuilder();
			for(Map.Entry<String, Object> entry: dataMap.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
			    if(hasRowNum(key)){
			    	continue;
			    }
				iCol.append(key).append(",");
				iColv.append(ColumnGenValue.GenColumnValue(value)).append(",");
			}
			iCols = " (" + iCol.substring(0, iCol.length() - 1) + ") ";
			iColvs = " (" + iColv.substring(0, iColv.length() - 1) + ") ";
			sql.append("insert into ").append(targetTable).append(iCols).
			append(" VALUES ").append(iColvs);
			sqls.add(sql.toString());
		}
		return sqls;
	}
	
	private boolean hasRowNum(Object value){
		if(ROWNUM.equals(value)){
			return true;
		}
		return false;
	}
	
    private static class ColumnGenValue{
    	
    	public static String GenColumnValue(Object value){
    	  String str = null;
    	  if (value instanceof String) {
    		  str = "'" + value +"'";
    	  }
    	  return str;
    	}
    }
	
}
