package com.zebone.dnode.engine.analyze.service;

/**
 * 文档解析服务接口
 * @author songjunjie
 * @version 2013-8-8 下午03:21:48
 */
public interface DocumentAnalyzeService {
	/**
	 * 业务库中文档编号字段的名字
	 */
//	public static final String DOC_NO_COLUMN = "DOC_NO";
	/**
	 * 解析指定的文档
	 */
	public void analyze(String analyzeThreads, String threadNo);
	
	/**
	 * 将xml格式的文档解析到业务库
	 * @param docId 文档存储表中的此文档对应的ID(主键)
	 * @param parseState 文档解析状态
	 * @param docxml
	 */
	public void analyze(String docId,String empiId, String parseState, String docxml);
	
	
	/**
	 * 解析文档
	 * @param docOrgCode
	 * @author 陈阵 
	 * @date 2014-1-2 下午3:41:10
	 */
	public void analyze(String docOrgCode);
}
