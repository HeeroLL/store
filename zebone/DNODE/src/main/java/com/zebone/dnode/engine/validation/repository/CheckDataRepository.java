package com.zebone.dnode.engine.validation.repository;

import java.util.List;

import com.zebone.dnode.engine.validation.domain.CheckLog;


/**
 * 数据校验模块Repository
 * @author 陈阵 
 * @date 2013-8-3 上午10:07:28
 */
public interface CheckDataRepository {
	
	/**
	 * 批量保存校验明细
	 * @param checkLog
	 * @author 陈阵 
	 * @date 2013-8-3 上午10:15:39
	 */
	public void saveBatchCheckLog(List<CheckLog> checkLogList);

}
