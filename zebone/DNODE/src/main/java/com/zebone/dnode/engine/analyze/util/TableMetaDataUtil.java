package com.zebone.dnode.engine.analyze.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

/**
 * 数据库表元数据信息工具
 * @author songjunjie
 * @version 2013-8-12 下午01:56:42
 */
public class TableMetaDataUtil {
	/**
	 * 获取指定表的字段信息
	 * @param tableName 表名字
	 * @return
	 */
	public static Map<String,String> getTableMetaData(DataSource dataSource,String tableName){
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e1) {
			throw new RuntimeException("从数据库连接池中获取数据库连接时出现异常！",e1);
		}
		DatabaseMetaData databaseMetaData = null;
		try {
			databaseMetaData = con.getMetaData();
		} catch (SQLException e) {
			throw new RuntimeException("获取数据库元数据信息的时候出现异常！",e);
		}
		
		ResultSet rs = null;
		Map<String,String> tableMetaData = new HashMap<String,String>();
		try {
			rs = databaseMetaData.getColumns(null, null, tableName, "%");
			while(rs.next()){
				String columnName = rs.getString("COLUMN_NAME").toUpperCase();
				int data_type = rs.getInt("DATA_TYPE");//来自 java.sql.Types 的 SQL 类型 
				String columnType = rs.getString("TYPE_NAME");
				String column_size = rs.getString("COLUMN_SIZE");//列的大小
				tableMetaData.put(columnName, columnType);
			}
		} catch (SQLException e) {
			throw new RuntimeException("获取表的元数据信息时出现异常："+tableName,e);
		}
		return tableMetaData;
	}
	
	public static void getFKTable(DataSource dataSource,String tableName){
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e1) {
			throw new RuntimeException("从数据库连接池中获取数据库连接时出现异常！",e1);
		}
		DatabaseMetaData databaseMetaData = null;
		try {
			databaseMetaData = con.getMetaData();
		} catch (SQLException e) {
			throw new RuntimeException("获取数据库元数据信息的时候出现异常！",e);
		}
		ResultSet rs = null;
		try {
			rs = databaseMetaData.getExportedKeys(null, null, tableName);
			while(rs.next()){
				String FKTABLE_NAME = rs.getString("FKTABLE_NAME").toUpperCase();
				String PKCOLUMN_NAME = rs.getString("PKCOLUMN_NAME").toUpperCase();
				String FKCOLUMN_NAME = rs.getString("FKCOLUMN_NAME").toUpperCase();
				String PKTABLE_NAME = rs.getString("PKTABLE_NAME").toUpperCase();
				
				System.out.println("FKTABLE_NAME:"+FKTABLE_NAME);
				System.out.println("PKCOLUMN_NAME:"+PKCOLUMN_NAME);
				System.out.println("FKCOLUMN_NAME:"+FKCOLUMN_NAME);
				System.out.println("PKTABLE_NAME:"+PKTABLE_NAME);
			}
		} catch (SQLException e) {
			throw new RuntimeException("获取表的元数据信息时出现异常："+tableName,e);
		}
	}
}
