/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Feb 21, 2013     节点管理控制层
 */
package com.zebone.dip.node.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.NetCheck;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.dip.node.service.NodeService;
import com.zebone.dip.node.vo.Node;
import com.zebone.util.UUIDUtil;
@Controller
@Scope(value="prototype")
public class NodeController extends BaseController {

	@Resource
	private NodeService nodeService;
	
	@RequestMapping("node/nodeIndex")
	public String nodeIndex(){
		return "dip/node/node_index";
	}
	
	/**
	 * @author caixl
	 * @date Feb 21, 2013
	 * @description TODO 获取节点列表信息
	 * @param page
	 * @return JsonGrid
	 */
	@RequestMapping("node/nodeManaList")
	public @ResponseBody JsonGrid nodeInfoList(Node pNode,Pagination<Node> page){
		Pagination<Node> p = nodeService.searchListPNode(page.getRowBounds(),pNode);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	@RequestMapping("node/nodeInfoEdit")
	public String nodeInfoEdit(HttpServletRequest request){
		String id = request.getParameter("id");
		Node pNode = nodeService.getNodeInfoById(id);
		request.setAttribute("pNode", pNode);
		return "dip/node/node_edit";
	}
	
	@RequestMapping("node/nodeInfoSave")
	public @ResponseBody Map<String, String> nodeInfoSave(Node pNode){
		int result =0;
		if(StringUtils.isEmpty(pNode.getId())){
			pNode.setId(UUIDUtil.getUuid());
			pNode.setDelFlag("1");
			result = nodeService.saveNodeInfo(pNode);
		}else{
			result = nodeService.updateNodeBaseInfo(pNode);
		}
		Map<String , String> map = new HashMap<String, String>();
		map.put("success", result>0?"true":"false");
	    map.put("id", pNode.getId());
		return map;
	}
	
	@RequestMapping("node/nodeInfoRemove")
	public @ResponseBody Map<String, Object> nodeInfoRemove(HttpServletRequest request){
		String id = request.getParameter("id");
		int result = nodeService.removeNodeInfoById(id);
		Map<String , Object> map = new HashMap<String, Object>();
		if(result == 1){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		return map;
	}
	
	@RequestMapping("node/updateNodeState")
	@ResponseBody
	public Map<String, Object> updateNodeState(HttpServletRequest request){
		String nodeData = request.getParameter("nodeData");
		int result = nodeService.updateNodeState(nodeData);
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("success", result>0 ? true:false);
		return map;
	}
	
	@RequestMapping("node/checkNodeState")
	@ResponseBody
	public Map<String, Object> checkNodeState(HttpServletRequest request){
		String nodeData = request.getParameter("nodeData");
		int result = nodeService.checkNodeState(nodeData);
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("success", result==1 ? true:false);
		map.put("status", result);
		return map;
	}
	
	@RequestMapping("node/checkNodeNet")
	@ResponseBody
	public Map<String, Object> checkNodeNet(HttpServletRequest request){
		String id = request.getParameter("id");
		String nodeIp = request.getParameter("nodeIp");
		int result = 0;
		Node node = new Node();
		node.setId(id);
		if(NetCheck.pingServer(nodeIp)){
			result = 1;
			node.setNodeNet("1");
		}else{
			node.setNodeNet("0");
		}
		nodeService.updateById(node);
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("success", result==1 ? true:false);
		map.put("msg", node.getNodeNet());
		return map;
	}
	
	@RequestMapping("node/checkNodeRun")
	@ResponseBody
	public Map<String, Object> checkNodeRun(HttpServletRequest request){
		String id = request.getParameter("id");
		int result = 0;
		result = nodeService.checkNodeRun(id);
		Node node = new Node();
		node.setId(id);
		if(result == 1){
			node.setNodeRun("1");
		}else{
			node.setNodeRun("0");
		}
		nodeService.updateById(node);
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("success", result==1 ? true:false);
		map.put("msg", node.getNodeRun());
		return map;
	}
}
