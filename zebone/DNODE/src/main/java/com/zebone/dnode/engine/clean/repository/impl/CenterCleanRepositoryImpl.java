package com.zebone.dnode.engine.clean.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zebone.dnode.engine.clean.repository.CenterCleanRepository;

@Repository("centerCleanRepository")
public class CenterCleanRepositoryImpl implements CenterCleanRepository {
	
	@Autowired @Qualifier("centerDataOracleJdbcTemplate")
	private NamedParameterJdbcTemplate npJdbcTemplate;

	@Override
	public void execute(List<String> sqls) {
		// TODO Auto-generated method stub
        if(sqls != null && sqls.size() >= 0){
        	String sql[] = new String[sqls.size()];
        	sqls.toArray(sql);
            npJdbcTemplate.getJdbcOperations().batchUpdate(sql);
        }
	}

}
