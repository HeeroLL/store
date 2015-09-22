package com.zebone.btp.log.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.btp.log.pojo.ErrorLog;

/**
 * 错误信息DAO
 * 
 * @author 宋俊杰
 * 
 */
@Mapper
public interface ErrorLogMapper {
	int deleteById(String errorLogId);

	int insert(ErrorLog record);

	ErrorLog findById(String errorLogId);

	int updateById(ErrorLog record);

	/**
	 * 根据查询条件查询出错误日志。查询出来结果，按照 日志记录时间倒序。
	 * @param className 类名，模糊查询
	 * @param methodName 方法名，模糊查询
	 * @param errorinfo 日志信息 ，模糊查询
	 * @param beginCreateTime 开始时间
	 * @param endCreateTime 结束时间
	 * @param rowBounds 分页信息
	 * @return
	 */
	List<ErrorLog> findErrorInfo(@Param("className") String className,
			@Param("methodName") String methodName,
			@Param("errorInfo") String errorinfo,
			@Param("beginCreateTime") Date beginCreateTime,
			@Param("endCreateTime") Date endCreateTime,RowBounds rowBounds);
	
	/**
	 * 根据查询条件查询出错误日志记录数
	 * @param className 类名，模糊查询
	 * @param methodName 方法名，模糊查询
	 * @param errorinfo 日志信息 ，模糊查询
	 * @param beginCreateTime 开始时间
	 * @param endCreateTime 结束时间
	 * @return
	 */
	Integer findErrorInfoCount(@Param("className") String className,
			@Param("methodName") String methodName,
			@Param("errorInfo") String errorinfo,
			@Param("beginCreateTime") Date beginCreateTime,
			@Param("endCreateTime") Date endCreateTime);
	
	/**
	 * 根据日志ID得到日志详细信息。
	 * @param errorInfoId
	 * @return
	 */
	String getErrorInfoById(String errorLogid);
}