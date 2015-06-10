/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Feb 21, 2013		节点管理业务层实现
 */
package com.zebone.dip.node.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.NetCheck;
import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.node.dao.NodeMapper;
import com.zebone.dip.node.service.NodeService;
import com.zebone.dip.node.vo.Node;
import com.zebone.dip.task.dao.TaskMapper;
import com.zebone.dip.task.vo.Task;
import com.zebone.util.UUIDUtil;
@Service("nodeService")
public class NodeServiceImpl implements NodeService {

	@Resource
	private NodeMapper nodeMapper;
	@Resource
	private TaskMapper taskMapper;
	
	/**
	 * @author caixl
	 * @date Feb 21, 2013
	 * @description TODO 查询节点列表相关数据
	 * @param rowBounds
	 * @param node 查询条件参数
	 * @return Pagination<PNode>
	 */
	@Override
	public Pagination<Node> searchListPNode(RowBounds rowBounds, Node node) {
		Pagination<Node> page = new Pagination<Node>();
		page.setData(nodeMapper.searchListPNode(rowBounds,node));
		page.setTotalCount(nodeMapper.searchTotalCountPNode(node));
		return page;
	}

	/**
	 * @author caixl
	 * @date Feb 22, 2013
	 * @description TODO 根据节点标识，获取某个节点的详细信息
	 * @param id 节点标识
	 * @return PNode
	 */
	@Override
	public Node getNodeInfoById(String id) {
		return nodeMapper.getNodeInfoById(id);
	}

	/**
	 * @author caixl
	 * @date Feb 22, 2013
	 * @description TODO 保存节点基本信息
	 * @param node
	 * @return int
	 */
	@Override
	public int saveNodeInfo(Node node) {
		return nodeMapper.saveNodeBaseInfo(node);
	}

	/**
	 * @author caixl
	 * @date Feb 22, 2013
	 * @description TODO 更新节点基本信息
	 * @param node
	 * @return int
	 */
	@Override
	public int updateNodeBaseInfo(Node node) {
		return nodeMapper.updateNodeBaseInfo(node);
	}

	/**
	 * @author caixl
	 * @date Feb 23, 2013
	 * @description TODO 根据节点标识删除相关节点信息
	 * @param id 节点标识
	 * @return int
	 */
	@Override
	public int removeNodeInfoById(String id) {
		String[] ids = id.split(",");
		List<String> list = new ArrayList<String>();
		int result = 0;
		for(String nodeId : ids){
			result = taskMapper.getCountTaskByNodeId(nodeId);
			if(result < 1){
				result = nodeMapper.getCountNodeById(nodeId);
				if(result < 1){
					list.add(nodeId);
				}
			}
		}
		
		if(list!=null && list.size()>0){
			result = nodeMapper.updateBatchNodeByNodeId(list);
			return 1;
		}
		return 0;
	}

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 获取所有的节点信息
	 * @return List<PNode>
	 */
	@Override
	public List<Node> getAllNodeInfo() {
		return nodeMapper.getAllNodeInfo();
	}

	/**
	 * @author caixl 
	 * @date Apr 11, 2013
	 * @description TODO 更新节点启停状态
	 * @param nodeData
	 * @return int
	 */
	@Override
	public int updateNodeState(String nodeData) {
		String[] datas = nodeData.split(";");
		for(int i=0;i<datas.length;i++){
			String[] strs = datas[i].split(",",2);
			Node node = new Node();
			node.setId(strs[0]);
			if("1".equals(strs[1])){
				node.setNodeState("0");
			}else{
				node.setNodeState("1");
			}
			nodeMapper.updateNodeState(node);
		}
		return 1;
	}

	/**
	 * @author caixl
	 * @date Apr 15, 2013
	 * @description TODO 检测节点的运行状态
	 * @param nodeData
	 * @return int
	 */
	@Override
	public int checkNodeState(String nodeData) {
		String[] str = nodeData.split(",",2);
		if(NetCheck.pingServer(str[1])){
			Task task = new Task();
			task.setId(UUIDUtil.getUuid());
			task.setDelFlag("1");
			task.setTaskCode("csrw000");
			task.setTaskNode(str[0]);
			task.setTaskType("4");
			task.setTaskDeploy("1");
			taskMapper.saveTaskInfo(task);
			try {
				Thread.sleep(10000);
				Task t= taskMapper.getTaskInfoById(task.getId());
				if("1".equals(t.getTaskState())){
					taskMapper.deleteTaskById(task.getId());
					return 1;
				}else{
					taskMapper.deleteTaskById(task.getId());
					return 2;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @author caixl
	 * @date Apr 17, 2013
	 * @description TODO 保存节点的网络状态或运行状态
	 * @return int
	 */
	@Override
	public int updateById(Node node) {
		return nodeMapper.updateById(node);
	}

	/**
	 * @author caixl
	 * @date Apr 17, 2013
	 * @description TODO 检测节点的运行状态
	 * @param id
	 * @return int
	 */
	@Override
	public int checkNodeRun(String id) {
		Task task = new Task();
		task.setId(UUIDUtil.getUuid());
		task.setDelFlag("1");
		task.setTaskCode("csrw000");
		task.setTaskNode(id);
		task.setTaskType("4");
		task.setTaskDeploy("1");
		taskMapper.saveTaskInfo(task);
		try {
			Thread.sleep(10000);
			Task t= taskMapper.getTaskInfoById(task.getId());
			if("1".equals(t.getTaskState())){
				taskMapper.deleteTaskById(task.getId());
				return 1;
			}else{
				taskMapper.deleteTaskById(task.getId());
				return 0;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
