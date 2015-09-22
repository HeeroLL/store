package com.zebone.dnode.engine.validation.service;

import com.zebone.dnode.engine.validation.domain.CheckLog;
import com.zebone.dnode.engine.validation.domain.CheckMain;
import com.zebone.dnode.engine.validation.domain.CheckResult;
import com.zebone.dnode.engine.validation.dto.ValidationResult;



/**
 * 整个校验模块的 service
 * @author 陈阵 
 * @date 2013-7-30 下午2:54:08
 */
public interface CheckDataService {
    
	/**
	 * 保存校验主表
	 * @param checkMain
	 * @author 陈阵 
	 * @date 2013-7-30 下午3:00:03
	 */
	public void saveCheckMain(CheckMain checkMain);
	
	/**
	 * 保存校验明细表
	 * @param checkLog
	 * @author 陈阵 
	 * @date 2013-7-30 下午3:01:37
	 */
	public void saveCheckLog(CheckLog checkLog);
	
	
	
	/**
	 * 保存校验信息
	 * @param validationResult 校验结果
	 * @param execTime 执行时间
	 * @author 陈阵 
	 * @date 2013-8-1 下午2:22:00
	 */
	public void saveValidationResult(ValidationResult validationResult, Long execTime);
	
	
	/**
	 * webservice保存校验信息
	 * @param validationResult 校验结果
	 * @param execTime 执行时间
	 * @author 陈阵 
	 * @date 2013-8-1 下午2:22:00
	 */
	public void wsSaveValidationResult(ValidationResult validationResult, long execTime);
	
	
	/**
	 * 保存校验结果
	 * @param checkResult
	 * @author 陈阵 
	 * @date 2013-8-6 下午1:50:47
	 */
	public void saveCheckResult(CheckResult checkResult);
	
	
}
