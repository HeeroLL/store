/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Feb 21, 2013	   节点管理业务层接口
 */
package com.zebone.dip.node.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.node.vo.Node;

public interface NodeService {

	/**
	 * @author caixl
	 * @date Feb 21, 2013
	 * @description TODO 查询节点列表相关数据
	 * @param rowBounds
	 * @param node 查询条件参数
	 * @return Pagination<PNode>
	 */
	Pagination<Node> searchListPNode(RowBounds rowBounds, Node node);

	/**
	 * @author caixl
	 * @date Feb 22, 2013
	 * @description TODO 根据节点标识，获取某个节点的详细信息
	 * @param id 节点标识
	 * @return Node
	 */
	Node getNodeInfoById(String id);

	/**
	 * @author caixl
	 * @date Feb 22, 2013
	 * @description TODO 保存节点基本信息
	 * @param node
	 * @return int
	 */
	int saveNodeInfo(Node node);

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
	 * @description TODO 根据节点标识删除相关节点信息
	 * @param id 节点标识
	 * @return int
	 */
	int removeNodeInfoById(String id);

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
	 * @description TODO 更新节点启停状态
	 * @param nodeData
	 * @return int
	 */
	int updateNodeState(String nodeData);

	/**
	 * @author caixl
	 * @date Apr 15, 2013
	 * @description TODO 检测节点的运行状态
	 * @param nodeData
	 * @return int
	 */
	int checkNodeState(String nodeData);

	/**
	 * @author caixl
	 * @date Apr 17, 2013
	 * @description TODO 保存节点的网络状态或运行状态
	 * @return int
	 */
	int updateById(Node node);

	/**
	 * @author caixl
	 * @date Apr 17, 2013
	 * @description TODO 检测节点的运行状态
	 * @param id
	 * @return int
	 */
	int checkNodeRun(String id);

}
