package com.zebone.dnode.task.dto;

/**
 * sql任务
 * @author cz
 *
 */
public class SqlTask extends Task {

	private static final long serialVersionUID = 256549239241026103L;
	
	/** 数据中心jdbc参数 **/
    private JdbcPropertys centerJdbcPropertys;
    
    /** 抓取数据库jdbc相关参数 **/
    private JdbcPropertys sourceJdbcPropertys;
    
    /** 具体执行的sql语句  **/
    private String sql;

	public JdbcPropertys getSourceJdbcPropertys() {
		return sourceJdbcPropertys;
	}

	public SqlTask() {
		super();
	}

	public void setSourceJdbcPropertys(JdbcPropertys sourceJdbcPropertys) {
		this.sourceJdbcPropertys = sourceJdbcPropertys;
	}

	public JdbcPropertys getCenterJdbcPropertys() {
		return centerJdbcPropertys;
	}

	public void setCenterJdbcPropertys(JdbcPropertys centerJdbcPropertys) {
		this.centerJdbcPropertys = centerJdbcPropertys;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

    
    
    

}
