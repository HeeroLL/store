package com.zebone.dnode.engine.analyze.dao;

/**
 * 
 */

import java.util.List;

/**
 * 动态插入数据
 * @author songjunjie
 * @version 2013-8-12 下午03:48:47
 */
public interface InsertDataDao {
	
	/**
	 * 根据给定的表名、字段列表及对应的数值，动态生成insert语句，并将数据导入到表中
	 * @param tableName 表名字
	 * @param columnList 字段列表
	 * @param paramList 参数列表，与字段列表的顺序要对应
	 */
	public void insertData(String tableName,List<String> columnList,List<String> paramList);
	
	/**
	 * 删除业务表数据
	 * @param tableName 表的名字
	 * @param column 字段的名字
	 * @param docNo 文档唯一标识
	 */
	public void deleteData(String tableName,String column ,String docNo);
}
