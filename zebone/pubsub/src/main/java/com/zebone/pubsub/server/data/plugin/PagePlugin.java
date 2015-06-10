package com.zebone.pubsub.server.data.plugin;

public class PagePlugin implements SqlPlugin {
		
	private PageParameter pageParameter;
	
	private String sql;

	
	public PagePlugin(String sql,PageParameter pageParameter) {
		super();
		this.sql = sql;
		this.pageParameter = pageParameter;
	}


	@Override
	public String genSql() {
		// TODO Auto-generated method stub
		String newSql = null;
		switch (pageParameter.getDbType()) {
		case ORACLE:
			newSql = OraclePageSql.GenPageSql(sql, pageParameter);
			break;

		default:
			break;
		}
		return newSql;
	}


	public PageParameter getPageParameter() {
		return pageParameter;
	}

	public void setPageParameter(PageParameter pageParameter) {
		this.pageParameter = pageParameter;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}




	public static class OraclePageSql{
		
		private static final String replaceSql = ":sql";
		

		private static final String oraclePageSql = "SELECT * FROM(SELECT A.*,ROWNUM RN FROM ("+replaceSql+") A WHERE ROWNUM <= ? )WHERE RN >= ?";
		
		public static String GenPageSql(String sql,PageParameter pageParameter){
			return oraclePageSql.replace(replaceSql, sql).replace("?", String.valueOf(pageParameter.getP1())).replace("?", String.valueOf(pageParameter.getP2()));
		}
	}


}
