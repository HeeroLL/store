package com.zebone.btp.mdm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zebone.btp.mdm.vo.DBQuery;
import com.zebone.btp.mdm.vo.MainDataTypeVO;

/**
 * 主数据 DAO层 实现类
 * 
 * @author ouyangxin CreateDate: 2012-11-22
 */
@Repository
public class MainDataDaoImpl implements MainDataDao {

	/** JdbcTemplate */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 执行建表语句
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean createTableSql(MainDataTypeVO vo) {

		boolean flag = false;
		try {
			// TODO 执行之前 删除

			// 执行新建表语句
			String sqlCreate = vo.getSqlCreate();
			jdbcTemplate.execute(sqlCreate);

			// 执行注释语句
			String sqlComments = vo.getSqlComments();
			String[] comments = sqlComments.split("~");
			for (String sql : comments) {
				jdbcTemplate.execute(sql);
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 修改表添加表字段SQL
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean createAddColumnSql(MainDataTypeVO vo) {

		boolean flag = false;
		try {

			// 执行 修改表添加表字段SQL
			String sql = vo.getSqlCreate();
			String[] addColumn = sql.split("~");
			for (String add : addColumn) {
				jdbcTemplate.execute(add);
			}
			// 执行 添加注释
			String sqlComm = vo.getSqlComments();
			String[] addComments = sqlComm.split("~");
			for (String add : addComments) {
				jdbcTemplate.execute(add);
			}
			flag = true;

		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 删除表
	 */
	@Override
	public boolean dropTable(DBQuery dbQuery) {

		boolean bool = false;
		
		String sql = " drop table " + dbQuery.getDbName();
		try {
			
			jdbcTemplate.execute(sql);
			bool = true;
			
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}
}
