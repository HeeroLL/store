package com.zebone.dnode.engine.store.service;

/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 蔡祥龙              New             Aug 13, 2013     文档存储引擎接口
 */

public interface DocumentStoreService {
	/**
	 * @author caixl
	 * @date Aug 13, 2013
	 * @description TODO 存储相关信息
	 */
	public void store(String analyzeThreads, String threadNo);
	
	
	/**
	 * 存储相关信息
	 * @param orgCode 机构编码
	 * @author 陈阵 
	 * @date 2014-1-2 上午9:11:41
	 */
	public void store(String orgCode);
	
	
}
