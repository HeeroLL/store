package com.zebone.dip.metadata.service;

import java.util.List;

import com.zebone.dip.metadata.vo.AcResult;
import com.zebone.dip.metadata.vo.DocConf;
import com.zebone.dip.metadata.vo.DocDataMap;
import com.zebone.dip.metadata.vo.FDocMapping;
import com.zebone.dip.metadata.vo.MdNode;

/**
 * 文档数据映射
 * @author cz
 *
 */
public interface DocDataMapService {
	/**
	 * 获取所有的文档
	 */
	List<DocConf> getAllDoc();
	
	/**
	 * 根据文档id获取主键
	 * @param docID
	 * @return
	 */
	DocConf getDocById(String docId);
	
	
	/**
	 * 加载结点(用于生成树状结构)
	 * @param mdNodeList
	 * @return
	 */
	String loadNodes(List<MdNode> mdNodeList, String docId);
	
	
	
	/**
	 * 加载结点
	 * @param mdNodeList dom4j解析之后的结点信息
	 * @param docId	文档id
	 * @param start 用户记录生成元素id
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-16 下午1:01:09
	 */
	List<DocDataMap> loadNodesMap(List<MdNode> mdNodeList, String docId, int start);
	
	
	
	
	/**
	 * 模糊查询table
	 * @param tableName
	 * @return
	 */
	AcResult getTable(String tableDesc);
	
	
	
	/**
	 * 模糊查询 table 列
	 * @param tableName
	 * @param colDesc
	 * @return
	 */
	AcResult getCol(String tableId,String colDesc);
	
	
	/**
	 * 获取所有的列
	 * @param tableId
	 * @return
	 */
	AcResult getAllCol(String tableId);

	
	/**
	 * 保存文档数据映射
	 * @param dmList
	 */
	void saveDocDataMap(List<FDocMapping> fdmList);
	
	

}
