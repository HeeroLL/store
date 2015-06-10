package com.zebone.dnode.engine.validation.mapper;

import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.dnode.engine.validation.domain.CheckLog;
import com.zebone.dnode.engine.validation.domain.CheckMain;
import com.zebone.dnode.engine.validation.domain.CheckResult;


/**
 * 检验模块mapper
 * @author 陈阵 
 * @date 2013-7-30 上午10:06:38
 */
@DipMapper
public interface CheckDataMapper {
	

	/**
	 * 保存检验主表
	 * @param checkMain
	 * @author 陈阵 
	 * @date 2013-7-30 上午11:14:35
	 */
	public void saveCheckMain(CheckMain checkMain);
	
	/**
	 * 保存校验日志明细  
	 * @param checkLog
	 * @author 陈阵 
	 * @date 2013-7-30 上午11:14:51
	 */
	public void saveCheckLog(CheckLog checkLog);
	
	
	
	/**
	 * 保存校验结果
	 * @param checkResult
	 * @author 陈阵 
	 * @date 2013-8-6 下午1:47:45
	 */
	public void saveCheckResult(CheckResult checkResult);

}
