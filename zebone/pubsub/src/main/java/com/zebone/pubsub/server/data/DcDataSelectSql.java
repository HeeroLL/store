package com.zebone.pubsub.server.data;



public class DcDataSelectSql implements SelectSql {
	
	
	private static final String sql = "SELECT DOC_XML FROM D_DOC_STORAGE WHERE DOC_PARSE_STATE = '1' AND DOC_ORG = ?";
	
	@Override
	public String getSelectSql() {
		// TODO Auto-generated method stub
		return sql;
	}

	@Override
	public String getCountSelectSql() {
		// TODO Auto-generated method stub
		return sql.replaceFirst("DOC_XML", " count(*) as count ");

	}

}
