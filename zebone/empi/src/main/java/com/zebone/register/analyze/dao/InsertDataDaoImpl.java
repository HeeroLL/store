package com.zebone.register.analyze.dao;



import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 动态插入数据到表
 * @author songjunjie
 * @version 2013-8-12 下午03:47:22
 */
@Repository("insertDataDao")
public class InsertDataDaoImpl implements InsertDataDao{
	private static final Log log = LogFactory.getLog(InsertDataDaoImpl.class);
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
//	@Resource(name="dataSource_dc")
//	private DataSource dcDataSource;
	
	/**
	 * @see com.zebone.register.analyze.dao.dnode.analyze.dao.InsertDataDao#insertData(java.lang.String, java.util.List, java.util.List)
	 */
	public void insertData(String tableName,List<String> columnList,List<String> paramList){
		
		//insert语句模板
		String insertSqlTemp = "INSERT INTO #tableName (#columns) values(#params)";
		StringBuilder sbcolumns = new StringBuilder();
		StringBuilder sbparams = new StringBuilder();
		//得到所有的字段，及参数。
		for(String column : columnList){
			sbcolumns.append(column).append(",");
			sbparams.append("?").append(",");
		}
		String columns = sbcolumns.substring(0, sbcolumns.length()-1);
		String params = sbparams.substring(0, sbparams.length()-1);
		String insertSql = insertSqlTemp.replaceAll("#tableName", tableName);
		insertSql = insertSql.replaceAll("#columns", columns);
		insertSql = insertSql.replaceAll("#params", params);
		if(log.isDebugEnabled()){
			StringBuilder sb = new StringBuilder();
			sb.append("\n\n---------------------------------------------------\n");
			sb.append("执行文档解析入库insert语句：\n");
			sb.append(insertSql);
			sb.append("\n参数:").append(ArrayUtils.toString(paramList.toArray()));
			sb.append("\n\n");
			log.debug(sb);
		}
		
//		Map<String, String> tableMetaData = TableMetaDataUtil.getTableMetaData(dcDataSource, tableName);
		try {
			jdbcTemplate.update(insertSql,paramList.toArray());
		} catch (DataAccessException e) {
			String errorMsg = "\n\n执行insert语句出错："+insertSql;
			errorMsg += "\n参数："+ArrayUtils.toString(paramList.toArray());
			log.error(errorMsg,e);
			throw new RuntimeException(errorMsg,e);
		}
	}
	
	/**
	 * 删除业务表数据
	 * @param tableName 表的名字
	 * @param column 字段的名字
	 * @param docNo 文档唯一标识
	 */
	public void deleteData(String tableName,String column ,String docNo){
		String deleteSql = "DELETE FROM #tableName WHERE  #column = ?";
		deleteSql = deleteSql.replaceAll("#tableName", tableName);
		deleteSql = deleteSql.replaceAll("#column", column);
		if(log.isDebugEnabled()){
			StringBuilder sb = new StringBuilder();
			sb.append("\n\n---------------------------------------------------\n");
			sb.append("删除业务表数据sql语句:\n");
			sb.append(deleteSql);
			sb.append("\n参数:").append(docNo);
			sb.append("\n");
			log.debug(sb);
		}
		jdbcTemplate.update(deleteSql, docNo);
	}
}
