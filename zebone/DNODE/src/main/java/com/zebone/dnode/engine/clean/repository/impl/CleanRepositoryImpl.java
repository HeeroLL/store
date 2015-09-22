package com.zebone.dnode.engine.clean.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zebone.dnode.engine.clean.pojo.ClearCloumn;
import com.zebone.dnode.engine.clean.pojo.ClearConfig;
import com.zebone.dnode.engine.clean.pojo.ClearLog;
import com.zebone.dnode.engine.clean.pojo.DsObj;
import com.zebone.dnode.engine.clean.pojo.TableConfig;
import com.zebone.dnode.engine.clean.repository.CleanRepository;

@Repository("cleanRepository")
public class CleanRepositoryImpl implements CleanRepository {
	
    @Autowired @Qualifier("centerConfigOracleJdbcTemplate")
	private NamedParameterJdbcTemplate centerOracleJdbcTemplate;

	@Override
	public TableConfig getTableConfig(String tableConfigId) {
		// TODO Auto-generated method stub
	   String sql = "SELECT a.ID_ as AID,a.TABLE_NAME,a.TABLE_DESC,a.TABLE_TYPE,a.DS_ID,a.IS_DELETED " +
	   		",b.ID_ as BID,b.P_DRIVER,b.P_URL,b.P_USER_NAME,b.P_PASSWORD,b.DEL_FLAG,b.P_NAME,b.P_REMARK " +
	   		"FROM P_TABLE_CONF a LEFT JOIN P_DS_OBJ b on a.DS_ID = b.ID_ WHERE a.ID_ = ?";
	   return centerOracleJdbcTemplate.getJdbcOperations().queryForObject(sql, new Object[]{tableConfigId}, new TableConfigMapper());
	}

	@Override
	public ClearConfig getClearConfig(String taskId) {
		// TODO Auto-generated method stub
		String sql = "SELECT ID_,TABLE_ID,DEPLOY_FLAG,AGAIN_FLAG,TASK_ID FROM P_CLEAR_CONF WHERE TASK_ID = ?";
		return centerOracleJdbcTemplate.getJdbcOperations().queryForObject(sql, new Object[]{taskId}, new ClearConfigMapper());
	}
    
	
	@Override
	public List<ClearCloumn> getClearCloumn(String clearConfigId) {
		// TODO Auto-generated method stub
		String sql = "SELECT ID_,CLEAR_ID,CLOUMN_NAME,CLEAR_TYPE,CLEAR_CONTENT FROM P_CLEAR_CLOUMN WHERE CLEAR_ID = ?";
		return centerOracleJdbcTemplate.getJdbcOperations().query(sql, new Object[]{clearConfigId}, new ClearCloumnMapper());
	}
    
    
	
	
	private static final class ClearConfigMapper implements RowMapper<ClearConfig> {
		@Override
		public ClearConfig mapRow(ResultSet rs, int paramInt) throws SQLException {
			// TODO Auto-generated method stub
			ClearConfig cc = new ClearConfig();
			cc.setId(rs.getString("ID_"));
			cc.setTable_id(rs.getString("TABLE_ID"));
			cc.setDeployFlag(rs.getString("DEPLOY_FLAG"));
			cc.setAgainFlag(rs.getString("AGAIN_FLAG"));
			cc.setTaskId(rs.getString("TASK_ID"));
			return cc;
		}
	}
	
	private static final class TableConfigMapper implements RowMapper<TableConfig> {
		@Override
		public TableConfig mapRow(ResultSet rs, int paramInt) throws SQLException {
			// TODO Auto-generated method stub
			TableConfig tc = new TableConfig();
			DsObj dso = new DsObj();
			
			dso.setId(rs.getString("BID"));
			dso.setDriver(rs.getString("P_DRIVER"));
			dso.setUrl(rs.getString("P_URL"));
			dso.setUserName(rs.getString("P_USER_NAME"));
			dso.setPassword(rs.getString("P_PASSWORD"));
			dso.setDelFlag(rs.getString("DEL_FLAG"));
			dso.setName(rs.getString("P_NAME"));
			
		    tc.setId(rs.getString("AID"));
		    tc.setTableName(rs.getString("TABLE_NAME"));
		    tc.setTableDesc(rs.getString("TABLE_DESC"));
		    tc.setTableType(rs.getString("TABLE_TYPE"));
		    tc.setDsObj(dso);
			return tc;
		}
	}
	
	private static final class ClearCloumnMapper implements RowMapper<ClearCloumn> {
		@Override
		public ClearCloumn mapRow(ResultSet rs, int paramInt) throws SQLException {
			// TODO Auto-generated method stub
	        ClearCloumn cc = new ClearCloumn();
	        cc.setId(rs.getString("ID_"));
	        cc.setClearId(rs.getString("CLEAR_ID"));
	        cc.setCloumnName(rs.getString("CLOUMN_NAME"));
	        cc.setClearType(rs.getString("CLEAR_TYPE"));
	        cc.setClearContent(rs.getString("CLEAR_CONTENT"));
	        return cc;
		}
	}

	@Override
	public void saveClearLog(List<ClearLog> clearLogs) {
		// TODO Auto-generated method stub
		if (clearLogs != null && clearLogs.size() > 0) {
			List<Object[]> batch = new ArrayList<Object[]>();
			for (ClearLog clearLog : clearLogs) {
				Object[] values = new Object[] { clearLog.getId(),
						clearLog.getTableName(), clearLog.getClearType(),
						clearLog.getCloumnName(), clearLog.getErrCode(),
						clearLog.getErrDesc(), clearLog.getClearId() };
				batch.add(values);
			}
			centerOracleJdbcTemplate
					.getJdbcOperations()
					.batchUpdate("insert into P_CLEAR_LOG(ID_,TABLE_NAME,CLAER_TYPE,CLOUMN_NAME,ERR_CODE,ERR_DESC,CLEAR_ID)"
									+ " values(?,?,?,?,?,?,?)", batch);
		}

	}


}
