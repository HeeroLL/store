package com.zebone.pubsub.server.data.plugin;

public class PageParameter {
	
	private DbType dbType;
	
	private long p1;
	
	private long p2;

	public PageParameter(DbType dbType, long p1, long p2) {
		super();
		this.dbType = dbType;
		this.p1 = p1;
		this.p2 = p2;
	}

	public DbType getDbType() {
		return dbType;
	}

	public void setDbType(DbType dbType) {
		this.dbType = dbType;
	}

	public long getP1() {
		return p1;
	}

	public void setP1(long p1) {
		this.p1 = p1;
	}

	public long getP2() {
		return p2;
	}

	public void setP2(long p2) {
		this.p2 = p2;
	}
	
	
	
}
