package com.zebone.dip.operate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.security.UserDetails;
import com.zebone.dip.operate.constant.ConstantObj;
import com.zebone.dip.operate.constant.SubFrequ;
import com.zebone.dip.operate.service.OperationalGuidanceService;
import com.zebone.dip.operate.vo.NodeRegister;
import com.zebone.dip.operate.vo.ServiceRegister;
import com.zebone.dip.operate.vo.SubscribeService;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

/**
 * 运行管理
 * @author LinBin
 *
 */
@RequestMapping("operationalGuidance")
@Controller
public class OperationalGuidanceController {
	
	@Autowired
	private OperationalGuidanceService operationalGuidanceService;
	
	@Autowired
	private SubFrequ subFrequ;

	/**************************************节点管理************************************************/
	
	@RequestMapping("/index")
    public String operationalGuidanceIndex (HttpServletRequest request, HttpServletResponse response){
        return "dip/operate/operateIndex";
    }
	
	/**
	 * 节点管理页面
	 * LinBin
	 * Apr 29, 2014
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/nodeManage")
    public String nodeManage (HttpServletRequest request, HttpServletResponse response){
        return "dip/operate/nodeInfo";
    }
	
	/**
	 * 查询全部节点信息
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/nodesQuery")
    public JsonGrid getNodesList (String nodeName,String orgLevelCode){
		List<NodeRegister> list = operationalGuidanceService.getNodesList(nodeName,orgLevelCode);
		JsonGrid jGrid = new JsonGrid("true",list.size(),list);
		return jGrid;
    }
	
	/**
	 * 通过机构名称查询机构
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOrgInfo")
    public Map<String, Object> getOrgInfo (@RequestParam("query") String name){
		List<NodeRegister> list = operationalGuidanceService.getOrgByName(name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("success", true);
		int total =0;
		if(list != null && list.size() > 0){
			total = list.size();
		}
		map.put("query", name);
		map.put("total", total);
		return map;
    }
	
	/**
	 * 节点启动停止
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/nodeChangeState")
    public Map<String ,Object> nodeChangeState (String nodeId,String nodeState){
		Map<String ,Object> map = new HashMap<String, Object>();
		boolean bool = false;
		bool = operationalGuidanceService.nodeChangeState(nodeId,nodeState);
		map.put("success", bool);
		return map;
    }
	
	/**
	 * 节点信息加载
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/nodeLoad")
    public Map<String ,Object> getNodeInfoById (String nodeId){
		Map<String ,Object> map = new HashMap<String, Object>();
		NodeRegister nodeInfo = operationalGuidanceService.getNodeInfoById(nodeId);
		map.put("success", true);
		map.put("data", nodeInfo);
		return map;
    }
	
	/**
	 * 节点名字唯一校验
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/nodeNameUniqueCheck")
    public Map<String ,Object> nodeNameUniqueCheck (String nodeName,String nodeId){
		Map<String ,Object> map = new HashMap<String, Object>();
		boolean bool = false;
		bool = operationalGuidanceService.nodeNameUniqueCheck(nodeName,nodeId);
		String msg = "";
		if(!bool){
			msg = "节点名字已经存在！";
		}
		map.put("success", bool);
		map.put("msg", msg);
		return map;
    }
	
	/**
	 * 节点信息保存
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/nodeSave")
    public Map<String ,Object> saveNodeInfo (NodeRegister node){
		Map<String ,Object> map = new HashMap<String, Object>();
		//保存前先校验名字唯一
		if(!(operationalGuidanceService.nodeNameUniqueCheck(node.getNodeName1(),node.getId()))){
			String msg = "节点名字已经存在！";
			map.put("success", false);
			map.put("msg", msg);
			return map;
		}
		//保存前先校验所属机构唯一
		if(!(operationalGuidanceService.nodeOrgUniqueCheck(node.getNodeOrg(),node.getId()))){
			String msg = "所属机构已经存在！";
			map.put("success", false);
			map.put("msg", msg);
			return map;
		}
		boolean bool = false;
		if(node.getId()!=""&&node.getId()!=null){
			bool = operationalGuidanceService.updateNodeInfo(node);
		}else{
			node.setId(UUIDUtil.getUuid());
			node.setNodeState(ConstantObj.NODE_STATE_STOP);
			bool = operationalGuidanceService.insertNodeInfo(node);
		}
		map.put("success", bool);
		return map;
    }
	
	/**
	 * 节点信息删除
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/nodeDelete")
    public Map<String ,Object> deleteNodeInfo (String id){
		Map<String ,Object> map = new HashMap<String, Object>();
		boolean bool = false;
		//删除前校验是否存在订阅
		bool = operationalGuidanceService.checkNodeSubscribe(id);
		if(bool){
			bool = operationalGuidanceService.deleteNodeInfo(id);
		}else{
			map.put("msg", "存在订阅信息，无法删除！");
		}
		map.put("success", bool);
		return map;
    }
	
	/**************************************服务管理************************************************/
	
	@RequestMapping("/serviceManage")
    public String serviceManage (HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("subFrequMap", subFrequ.getSub());
        return "dip/operate/serviceInfo";
    }
	
	/**
	 * 分页查询服务信息
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/servicesQuery")
    public JsonGrid getNodesList (Pagination<ServiceRegister> page,ServiceRegister serviceRegister){
		Pagination<ServiceRegister> p = operationalGuidanceService.getServicesPage(page,serviceRegister);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
    }
	
	/**
	 * 服务启动停止
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/serviceChangeState")
    public Map<String ,Object> serviceChangeState (String serviceId,String serviceState){
		Map<String ,Object> map = new HashMap<String, Object>();
		boolean bool = false;
		bool = operationalGuidanceService.serviceChangeState(serviceId,serviceState);
		map.put("success", bool);
		return map;
    }
	
	/**
	 * 服务信息加载
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/serviceLoad")
    public Map<String ,Object> getServiceInfoById (String serviceId){
		Map<String ,Object> map = new HashMap<String, Object>();
		ServiceRegister serviceInfo = operationalGuidanceService.getServiceInfoById(serviceId);
		map.put("success", true);
		map.put("data", serviceInfo);
		return map;
    }
	
	/**
	 * 服务信息保存
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/serviceSave")
    public Map<String ,Object> saveServiceInfo (ServiceRegister service){
		Map<String ,Object> map = new HashMap<String, Object>();
		//保存前先校验
		if(!(operationalGuidanceService.serviceNameUniqueCheck(service.getServiceName(),service.getId()))){
			String msg = "服务名字已经存在！";
			map.put("success", false);
			map.put("msg", msg);
			return map;
		}
		boolean bool = false;
		if(service.getId()!=""&&service.getId()!=null){
			bool = operationalGuidanceService.updateServiceInfo(service);
		}else{
			service.setId(UUIDUtil.getUuid());
			service.setServiceState(ConstantObj.SERVICE_STATE_STOP);
			bool = operationalGuidanceService.insertServiceInfo(service);
		}
		map.put("success", bool);
		return map;
    }
	
	/**
	 * 服务名字唯一校验
	 * LinBin
	 * May 4, 2014
	 * @param serviceName
	 * @param serviceId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/serviceNameUniqueCheck")
    public Map<String ,Object> serviceNameUniqueCheck (String serviceName,String serviceId){
		Map<String ,Object> map = new HashMap<String, Object>();
		boolean bool = false;
		bool = operationalGuidanceService.nodeNameUniqueCheck(serviceName,serviceId);
		String msg = "";
		if(!bool){
			msg = "服务名字已经存在！";
		}
		map.put("success", bool);
		map.put("msg", msg);
		return map;
    }
	
	/**
	 * 服务信息删除
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/serviceDelete")
    public Map<String ,Object> deleteServiceInfo (String id){
		Map<String ,Object> map = new HashMap<String, Object>();
		boolean bool = false;
		//删除前校验是否存在订阅
		bool = operationalGuidanceService.checkServiceSubscribe(id);
		if(bool){
			bool = operationalGuidanceService.deleteServiceInfo(id);
		}else{
			map.put("msg", "存在订阅信息，无法删除！");
		}
		map.put("success", bool);
		return map;
    }
	
	/**************************************订阅管理************************************************/
	
	@RequestMapping("/subscribeManage")
    public String subscribeManage (HttpServletRequest request, HttpServletResponse response){
        return "dip/operate/subscribeInfo";
    }
	
	/**
	 * 分页查询订阅信息
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/subscribeQuery")
    public JsonGrid getSubscribeList (Pagination<SubscribeService> page,SubscribeService subscribeService){
		Pagination<SubscribeService> p = operationalGuidanceService.getSubscribePage(page,subscribeService);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
    }
	
	/**
	 * 机构订阅信息
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	@RequestMapping("/subscribeInfo")
	public String subscribeInfo (HttpServletRequest request, HttpServletResponse response){
		List<ServiceRegister> list = operationalGuidanceService.getAllService();
		List<ServiceRegister> listFile = new ArrayList<ServiceRegister>();
		List<ServiceRegister> listResource = new ArrayList<ServiceRegister>();
		for(ServiceRegister o :list){
			if(ConstantObj.SERVICE_TYPE_FILE.equals(o.getServiceType())){
				listFile.add(o);
			}else if(ConstantObj.SERVICE_TYPE_RESOURCE.equals(o.getServiceType())){
				listResource.add(o);
			}
		}
		request.setAttribute("listFile", listFile);
		request.setAttribute("listResource", listResource);
		Map<String, String> map = subFrequ.getSub();
		Set<String> key = map.keySet();
		String str = "{";
        for (Iterator it = key.iterator(); it.hasNext();) {
            String stc = (String) it.next();
            str+=stc+":'"+map.get(stc)+"',";
        }
        str = str.substring(0, str.length()-1);
        str += "}";
		request.setAttribute("subFrequMap", str);
		//查询机构是否停用
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		NodeRegister nodeInfo = operationalGuidanceService.getNodeInfoByLevelCode(userDetails.getMhoList().get(0).getLevelCode());
        boolean bool = false;
		if(nodeInfo!=null){
        	if(nodeInfo.getNodeState().equals(ConstantObj.NODE_STATE_RUN)){
        		bool = true;
            }
        }
		request.setAttribute("opeFlag", bool);
		request.setAttribute("lastSubTime", subFrequ.getLastSubTime());
		
		return "dip/operate/subscribeContent";
    }
	
	/**
	 * 订阅信息加载
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/subLoad")
    public Map<String ,Object> subLoad(String id){
		Map<String ,Object> map = new HashMap<String, Object>();
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		List<SubscribeService> listSub = operationalGuidanceService.getAllSubServiceByOrg(userDetails.getMhoList().get(0).getLevelCode());
		for(SubscribeService sub : listSub){
			if(sub.getBeginDate()!=null&&sub.getBeginDate()!=""){
				sub.setBeginDate(DateUtil.format(sub.getBeginDate(), "yyyyMMdd", "yyyy-MM-dd"));
			}
			if(sub.getEndDate()!=null&&sub.getEndDate()!=""){
				sub.setEndDate(DateUtil.format(sub.getEndDate(), "yyyyMMdd", "yyyy-MM-dd"));
			}
		}
		map.put("success", true);
		map.put("listSub", listSub);
		return map;
    }
	
	/**
	 * 订阅信息删除
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteSub")
    public Map<String ,Object> deleteSub(String subid){
		Map<String ,Object> map = new HashMap<String, Object>();
		boolean bool = false;
		bool = operationalGuidanceService.deleteSubService(subid);
		map.put("success", bool);
		return map;
    }
	
	/**
	 * 订阅信息保存
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveSub")
    public Map<String ,Object> saveSub(SubscribeService sub){
		Map<String ,Object> map = new HashMap<String, Object>();
		//查询机构是否停用
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		NodeRegister nodeInfo = operationalGuidanceService.getNodeInfoByLevelCode(userDetails.getMhoList().get(0).getLevelCode());
        boolean bool = false;
		if(nodeInfo!=null){
        	if(nodeInfo.getNodeState().equals(ConstantObj.NODE_STATE_RUN)){
        		bool = true;
            }
        }
		if(!bool){
			map.put("success", false);
			map.put("msg", "本机构已经停用，无法保存订阅");
			return map;
		}
		//查询服务是否停用
		ServiceRegister serviceInfo = operationalGuidanceService.getServiceInfoById(sub.getServiceId());
		boolean bool2 = false;
		if(nodeInfo!=null){
        	if(serviceInfo.getServiceState().equals(ConstantObj.SERVICE_STATE_RUN)){
        		bool2 = true;
            }
        }
		if(!bool2){
			map.put("success", false);
			map.put("msg", "服务已经停用，无法保存订阅");
			return map;
		}
		SubscribeService obj = operationalGuidanceService.saveSubService(sub);
		map.put("success", true);
		map.put("subid", obj.getId());
		return map;
    }

}
