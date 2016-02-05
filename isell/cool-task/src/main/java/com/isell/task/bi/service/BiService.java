package com.isell.task.bi.service;

public interface BiService {
	
	/**
	 * 每天定时插入一条时间数据
	 */
	public void setBiDate();
	
	/**
	 * 酷店统计项抽取
	 */
	public void etlCoonShop();

}
