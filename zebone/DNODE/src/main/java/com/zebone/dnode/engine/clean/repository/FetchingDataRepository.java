package com.zebone.dnode.engine.clean.repository;

import java.util.List;
import java.util.Map;


/**
 * 获取数据
 * @author cz
 *
 */
public interface FetchingDataRepository {
	
	/**
	 * 获取某张表的数据
	 * @param table
	 * @return
	 */
	List<Map<String,Object>> getFetchData(String table);
}

