package com.zebone.pubsub.server.data.plugin;

import java.util.List;

public class PageData<T> {
	
	private long dataCount;
	
	private int curPage;
	
	private boolean hasNext;
	
	private List<T> dataList;
	
	private SqlParameter sqlParameter;

   
	public long getDataCount() {
		return dataCount;
	}

	public void setDataCount(long dataCount) {
		this.dataCount = dataCount;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public SqlParameter getSqlParameter() {
		return sqlParameter;
	}

	public void setSqlParameter(SqlParameter sqlParameter) {
		this.sqlParameter = sqlParameter;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	
	
	
}
