package com.zebone.pubsub.server.data.plugin;

public class SqlParameter {
	
	private String sql;
	
	private Object paraObject[];

	public SqlParameter(String sql, Object[] paraObject) {
		super();
		this.sql = sql;
		this.paraObject = paraObject;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getParaObject() {
		return paraObject;
	}

	public void setParaObject(Object[] paraObject) {
		this.paraObject = paraObject;
	}
	
	

}
