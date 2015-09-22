package com.zebone.dip.node.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.node.vo.Node;
@Mapper
public interface NodeMapper {

	Node findById(String id);

	/**
	 * @author caixl
	 * @date Feb 21, 2013
	 * @description TODO 获取节点列表信息
	 * @param rowBounds
	 * @param node 获取列表的条件
	 * @return List<PNode>
	 */
	List<Node> searchListPNode(RowBounds rowBounds, Node node);

	/**
	 * @author caixl
	 * @date Feb 21, 2013
	 * @description TODO 获取节点数
	 * @param node 获取列表的条件
	 * @return int
	 */
	int searchTotalCountPNode(Node node);

	/**
	 * @author caixl
	 * @date Feb 22, 2013
	 * @description TODO 根据节点标识 获取节点详细信息
	 * @param id 节点标识
	 * @return PNode
	 */
	Node getNodeInfoById(String id);

	/**
	 * @author caixl
	 * @date Feb 22, 2013
	 * @description TODO 保存节点基本信息
	 * @param node
	 * @return int
	 */
	int saveNodeBaseInfo(Node node);

	/**
	 * @author caixl
	 * @date Feb 22, 2013
	 * @description TODO 更新节点基本信息
	 * @param node
	 * @return int
	 */
	int updateNodeBaseInfo(Node node);

	/**
	 * @author caixl
	 * @date Feb 23, 2013
	 * @description TODO 根据节点判断节点现在状态
	 * @param id 节点标识
	 * @return int
	 */
	int getCountNodeById(String id);

	/**
	 * @author caixl
	 * @date Feb 23, 2013
	 * @description TODO 批量更新节点的信息
	 * @param list 节点标识的集合
	 * @return int
	 */
	int updateBatchNodeByNodeId(List<String> list);

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 获取所有的节点信息
	 * @return List<PNode>
	 */
	List<Node> getAllNodeInfo();

	/**
	 * @author caixl
	 * @date Apr 11, 2013 
	 * @description TODO 更新节点状态
	 * @param node void
	 */
	int updateNodeState(Node node);
	/**
	 * @author caixl
	 * @date Apr 17, 2013
	 * @description TODO 保存节点的网络或运行时状态
	 * @param node
	 * @return int
	 */
	int updateById(Node node);
}