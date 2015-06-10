package com.zebone.btp.mdm.dao;

import com.zebone.btp.mdm.vo.DBQuery;
import com.zebone.btp.mdm.vo.MainDataTypeVO;

/**
 * 主数据 DAO层
 * 
 * @author ouyangxin
 * 
 * CreateDate: 2012-11-22
 */
public interface MainDataDao {

	/**
	 * 
	 * 建表语句
	 * @param vo
	 * @return
	 */
	boolean createTableSql(MainDataTypeVO vo);
	
	/**
	 * 
	 * 修改表添加表字段SQL
	 * @param vo
	 * @return
	 */
	boolean createAddColumnSql(MainDataTypeVO vo);
	
	/**
	 * 
	 * 根据表名删除表
	 * @param dbQuery
	 * @return
	 */
	boolean dropTable(DBQuery dbQuery);
	
}
