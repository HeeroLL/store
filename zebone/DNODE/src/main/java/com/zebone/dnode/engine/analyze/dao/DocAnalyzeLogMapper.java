package com.zebone.dnode.engine.analyze.dao;


import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.dnode.engine.analyze.vo.DocAnalyzeLog;
import com.zebone.dnode.engine.analyze.vo.ParesTableLog;

/**
 * 文档解析日志Dao
 * @author songjunjie
 * @version 2013-8-15 下午05:44:47
 */
@DipMapper
public interface DocAnalyzeLogMapper {
	
	/**
	 * 保存解析日志
	 * @param docAnalyzeLog
	 */
	public void insert(DocAnalyzeLog docAnalyzeLog);
	
	/**
	 * 保存业务表的变化日志
	 * @param paresTableLog
	 */
	public void insertChangeLog(ParesTableLog paresTableLog);
}
