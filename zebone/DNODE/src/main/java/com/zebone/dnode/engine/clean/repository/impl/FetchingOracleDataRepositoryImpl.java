package com.zebone.dnode.engine.clean.repository.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zebone.dnode.engine.clean.repository.FetchingDataRepository;

public class FetchingOracleDataRepositoryImpl implements FetchingDataRepository {
	
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	public FetchingOracleDataRepositoryImpl(DataSource ds) {
		this.npJdbcTemplate = new NamedParameterJdbcTemplate(ds);
	}




	public List<Map<String,Object>> getFetchData(String table){
		String innerSql = "SELECT * FROM " + table;
		String sql = "SELECT t.*,ROWNUM FROM (" +innerSql +") t WHERE ROWNUM <= 20";
		return npJdbcTemplate.getJdbcOperations().queryForList(sql);
	}


    
}
