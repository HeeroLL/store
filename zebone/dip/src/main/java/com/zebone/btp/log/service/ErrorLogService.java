package com.zebone.btp.log.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.log.pojo.ErrorLog;

/**
 * 错误日志Servie
 * 
 * @author 宋俊杰
 * 
 */
public interface ErrorLogService {

	/**
	 * 刪除日志
	 * 
	 * @param errorLogId
	 *            日志id
	 */
	public void deleteById(String errorLogId);

	/**
	 * 保存日志
	 * 
	 * @param errorLog
	 */
	public void save(ErrorLog errorLog);

	/**
	 * 根据日志id得到错误日志信息
	 * 
	 * @param errorLogId
	 * @return
	 */
	public ErrorLog findById(String errorLogId);

	/**
	 * 根据错误日志id更新日志信息
	 * 
	 * @param record
	 */
	public void updateById(ErrorLog errorLog);
	
	/**
	 * 在一个单独的线程中保存错误日志信息。
	 * @param errorLog
	 */
	public void saveInThread(ErrorLog errorLog);
	
	/**
	 * 根据查询条件查询出错入日志。查询出来结果，按照 日志记录时间倒序。
	 * @param className 类名，模糊查询
	 * @param methodName 方法名，模糊查询
	 * @param errorinfo 日志信息 ，模糊查询
	 * @param beginCreateTime 开始时间
	 * @param endCreateTime 结束时间
	 * @param pagination 分页信息
	 * @return
	 */
	public Pagination<ErrorLog> findErrorInfo(String className, String methodName,String errorinfo, Date beginCreateTime, Date endCreateTime,Pagination<ErrorLog> pagination);
	
	/**
	 * 根据错误日志ID，得到日志的详情
	 * @param errorInfoId
	 * @return
	 */
	public String getErrorInfoById(String errorLogid);
}
