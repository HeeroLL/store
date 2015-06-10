package com.zebone.dnode.engine.analyze.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.DcMapper;
import com.zebone.dnode.engine.analyze.vo.DocumentInfo;

/**
 * 文档存储数据表相关操作。
 * 
 * @author songjunjie
 * @version 2013-8-6 下午03:45:32
 */
@DcMapper
public interface DocAnalyzedMapper {

	/**
	 * 查询出没有解析的文档，其中需要包含需要重新解析的。
	 * @param count 查询出的条数。
     * @param threadCount 线程总数
     * @param threadNo 本线程编号
	 * @return
	 */
	public List<DocumentInfo> getNoAnalyzedDoc(@Param("count")int count,@Param("threadCount")int threadCount, @Param("threadNo")int threadNo);
	
	
	/**
	 * 查询出没有解析的文档，其中需要包含需要重新解析的。
	 * @param count
	 * @return
	 * @author 陈阵 
	 * @date 2014-1-2 下午3:33:50
	 */
	public List<DocumentInfo> getNoAnalyzedDocByDocOrgCode(@Param("docOrgCode") String docOrgCode, int count);
	
	/**
	 * 更新文档信息的解析状态
	 * @param id 文档的ID
	 * @param parseState 文档状态
	 */
	public void updateParseState(@Param("id")String id,@Param("parseState")String parseState);

}
