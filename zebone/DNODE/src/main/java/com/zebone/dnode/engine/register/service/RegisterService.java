package com.zebone.dnode.engine.register.service;

/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 蔡祥龙              New             Aug 14, 2013     文档注册引擎接口
 */

public interface RegisterService {
	/**
	 * @author 蔡祥龙
	 * @date Aug 14, 2013
	 * @description TODO 文档注册服务
	 */
	public void register(String analyzeThreads, String threadNo);
	
	
	/**
	 * 文档注册服务
	 * @param docOrgCode
	 * @author 陈阵 
	 * @date 2014-1-2 上午9:49:09
	 */
	public void register(String docOrgCode);
}
