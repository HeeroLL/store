package com.zebone.dnode.etl.load.service;

import com.zebone.dnode.etl.load.pojo.LoadConfig;

public interface LoadService {
	
	
	public static final int  BATCH_NUM = 1000;
	
	/**
	 * 数据加载
	 * @param loadConfig  加载配置参数
	 * @author 陈阵 
	 * @date 2014-2-20 下午3:46:20
	 */
	void loadData(LoadConfig loadConfig);
	
}
