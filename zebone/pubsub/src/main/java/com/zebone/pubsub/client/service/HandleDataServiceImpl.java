package com.zebone.pubsub.client.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

@Service("handleDataService")
public class HandleDataServiceImpl implements HandleDataService{
	
	@Resource(name="jdbcTemplateCli")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void handleData(String data) {
		// TODO Auto-generated method stub
		PubSubData psd = new PubSubData();
		psd.setSubData(data);
		jdbcTemplate.update("INSERT INTO PUB_SUB_DATA(SUB_DATA) VALUES(?)",new PubSubDataPSS(psd));
	}
	
	
	
	private static class PubSubData{
		private String subData;
		
	
		public String getSubData() {
			return subData;
		}


		public void setSubData(String subData) {
			this.subData = subData;
		}
		
	}
	
	
	
	private final class PubSubDataPSS implements PreparedStatementSetter{
		
		private PubSubData psd;
		
		public PubSubDataPSS(PubSubData psd) {
			super();
			this.psd = psd;
		}

		@Override
		public void setValues(PreparedStatement paramPreparedStatement)
				throws SQLException {
			// TODO Auto-generated method stub
			paramPreparedStatement.setString(1, psd.getSubData());
		}		
	}
	
	
}
