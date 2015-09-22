package com.zebone.dip.operate.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.security.UserDetails;
import com.zebone.dip.operate.constant.ConstantObj;
import com.zebone.dip.operate.constant.SubFrequ;
import com.zebone.dip.operate.dao.OperationalGuidanceMapper;
import com.zebone.dip.operate.service.OperationalGuidanceService;
import com.zebone.dip.operate.vo.NodeRegister;
import com.zebone.dip.operate.vo.ServiceRegister;
import com.zebone.dip.operate.vo.SubscribeEvent;
import com.zebone.dip.operate.vo.SubscribeService;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

@Service
public class OperationalGuidanceServiceImpl implements OperationalGuidanceService{

	@Autowired
	private OperationalGuidanceMapper operationalGuidanceMapper;
	
	@Autowired
	private SubFrequ subFrequ;
	
	/**
	 * 查询全部节点信息
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	@Override
	public List<NodeRegister> getNodesList(String nodeName,String orgLevelCode) {
		return operationalGuidanceMapper.getNodesList(nodeName,orgLevelCode);
	}

	/**
	 * 节点信息加载
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeId
	 * @return
	 */
	@Override
	public NodeRegister getNodeInfoById(String nodeId) {
		return operationalGuidanceMapper.getNodeInfoById(nodeId);
	}

	/**
	 * 节点信息新增
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	@Override
	public boolean insertNodeInfo(NodeRegister node) {
		boolean bool = false;
		int i = operationalGuidanceMapper.insertNodeInfo(node);
		if(i>0){
			bool = true;
		}
		return bool;
	}
	
	/**
	 * 节点信息更新
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	@Override
	public boolean updateNodeInfo(NodeRegister node) {
		boolean bool = false;
		int i = operationalGuidanceMapper.updateNodeInfo(node);
		if(i>0){
			bool = true;
		}
		return bool;
	}

	/**
	 * 节点名字唯一校验
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	@Override
	public boolean nodeNameUniqueCheck(String nodeName,String nodeId) {
		boolean bool = false;
		List<NodeRegister> list = operationalGuidanceMapper.getNodesListByName(nodeName);
		if(nodeId!=null&&nodeId!=""){
			if(list!=null&&list.size()>0){
				if(nodeId.equals(list.get(0).getId())){
					bool = true;
				}else{
					bool = false;
				}
			}else{
				bool = true;
			}
		}else{
			if(list!=null&&list.size()>0){
				bool = false;
			}else{
				bool = true;
			}
		}
		return bool;
	}
	
	/**
	 * 所属机构唯一校验
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeOrg
	 * @return
	 */
	@Override
	public boolean nodeOrgUniqueCheck(String nodeOrg,String nodeId) {
		boolean bool = false;
		List<NodeRegister> list = operationalGuidanceMapper.getNodesListByOrg(nodeOrg);
		if(nodeId!=null&&nodeId!=""){
			if(list!=null&&list.size()>0){
				if(nodeId.equals(list.get(0).getId())){
					bool = true;
				}else{
					bool = false;
				}
			}else{
				bool = true;
			}
		}else{
			if(list!=null&&list.size()>0){
				bool = false;
			}else{
				bool = true;
			}
		}
		return bool;
	}

	/**
	 * 节点信息删除
	 * LinBin
	 * May 4, 2014
	 * @param nodeId
	 * @return
	 */
	@Override
	public boolean deleteNodeInfo(String nodeId) {
		boolean bool = false;
		int i = operationalGuidanceMapper.deleteNodeInfo(nodeId);
		if(i>0){
			bool = true;
		}
		return bool;
	}

	/**
	 * 分页查询服务信息
	 * LinBin
	 * May 4, 2014
	 * @param page
	 * @param serviceRegister
	 * @return
	 */
	@Override
	public Pagination<ServiceRegister> getServicesPage(Pagination<ServiceRegister> page, ServiceRegister serviceRegister) {
		List<ServiceRegister> list = operationalGuidanceMapper.getServiceInfoList(page.getRowBounds(),serviceRegister);
		int count = operationalGuidanceMapper.getServiceInfoCount(serviceRegister);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * 服务信息加载
	 * LinBin
	 * May 4, 2014
	 * @param serviceId
	 * @return
	 */
	@Override
	public ServiceRegister getServiceInfoById(String serviceId) {
		return operationalGuidanceMapper.getServiceInfoById(serviceId);
	}

	/**
	 * 服务名字唯一校验
	 * LinBin
	 * May 4, 2014
	 * @param serviceName
	 * @param id
	 * @return
	 */
	@Override
	public boolean serviceNameUniqueCheck(String serviceName, String serviceId) {
		boolean bool = false;
		List<ServiceRegister> list = operationalGuidanceMapper.getServiceListByName(serviceName);
		if(serviceId!=null&&serviceId!=""){
			if(list!=null&&list.size()>0){
				if(serviceId.equals(list.get(0).getId())){
					bool = true;
				}else{
					bool = false;
				}
			}else{
				bool = true;
			}
		}else{
			if(list!=null&&list.size()>0){
				bool = false;
			}else{
				bool = true;
			}
		}
		return bool;
	}

	/**
	 * 服务信息新增
	 * LinBin
	 * May 4, 2014
	 * @param service
	 * @return
	 */
	@Override
	public boolean insertServiceInfo(ServiceRegister service) {
		boolean bool = false;
		int i = operationalGuidanceMapper.insertServiceInfo(service);
		if(i>0){
			bool = true;
		}
		return bool;
	}

	/**
	 * 服务信息更新
	 * LinBin
	 * May 4, 2014
	 * @param service
	 * @return
	 */
	@Override
	public boolean updateServiceInfo(ServiceRegister service) {
		boolean bool = false;
		int i = operationalGuidanceMapper.updateServiceInfo(service);
		if(i>0){
			bool = true;
		}
		return bool;
	}

	/**
	 * 服务信息删除
	 * LinBin
	 * May 4, 2014
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteServiceInfo(String serviceId) {
		boolean bool = false;
		int i = operationalGuidanceMapper.deleteServiceInfo(serviceId);
		if(i>0){
			bool = true;
		}
		return bool;
	}

	/**
	 * 分页查询订阅信息
	 * LinBin
	 * May 5, 2014
	 * @param page
	 * @param subscribeService
	 * @return
	 */
	@Override
	public Pagination<SubscribeService> getSubscribePage(Pagination<SubscribeService> page, SubscribeService subscribeService) {
		List<SubscribeService> list = operationalGuidanceMapper.getSubscribeInfoList(page.getRowBounds(),subscribeService);
		for(SubscribeService sub : list){
			String str = "";
			if(ConstantObj.HISTORY_DATA_SELECT.equals(sub.getIsHistory())){
				if(sub.getBeginDate()!=null&&sub.getBeginDate()!=""){
					sub.setBeginDate(DateUtil.format(sub.getBeginDate(), "yyyyMMdd", "yyyy-MM-dd"));
				}
				if(sub.getEndDate()!=null&&sub.getEndDate()!=""){
					sub.setEndDate(DateUtil.format(sub.getEndDate(), "yyyyMMdd", "yyyy-MM-dd"));
				}
				str += "历史数据：从"+sub.getBeginDate()+"到"+sub.getEndDate()+";";
			}
			if(ConstantObj.CURRENT_DATA_SELECT.equals(sub.getIsCurrent())){
				str += "当前数据：";
				Map<String, String> map = subFrequ.getSub();
				Set<String> key = map.keySet();
		        for (Iterator it = key.iterator(); it.hasNext();) {
		            String stc = (String) it.next();
		            if(stc.equals(sub.getSubFrequ())){
						str += map.get(stc)+";";
					}
		        }
			}
			sub.setSubFrequ(str);
		}
		int count = operationalGuidanceMapper.getSubscribeInfoCount(subscribeService);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * 获得所有服务
	 * LinBin
	 * May 5, 2014
	 * @return
	 */
	@Override
	public List<ServiceRegister> getAllService() {
		return operationalGuidanceMapper.getAllService();
	}

	/**
	 * 获取当前登录用户所在机构的所有订阅服务
	 * LinBin
	 * May 6, 2014
	 * @param orgCode
	 * @return
	 */
	@Override
	public List<SubscribeService> getAllSubServiceByOrg(String orgCode) {
		return operationalGuidanceMapper.getAllSubServiceByOrg(orgCode);
	}

	/**
	 * 订阅信息删除
	 * LinBin
	 * May 6, 2014
	 * @param subid
	 * @return
	 */
	@Override
	public boolean deleteSubService(String subid) {
		boolean bool = false;
		int i = operationalGuidanceMapper.deleteSubService(subid);
		if(i>0){
			bool = true;
		}
		return bool;
	}

	/**
	 * 订阅信息保存
	 * LinBin
	 * May 6, 2014
	 * @param sub
	 * @return
	 */
	@Override
	public SubscribeService saveSubService(SubscribeService sub) {
		int i = 0;
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		sub.setUserId(userDetails.getPersonnelId());
		sub.setOrgCode(userDetails.getMhoList().get(0).getLevelCode());
		sub.setSubTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
		if(sub.getBeginDate()!=null&&sub.getBeginDate()!=""){
			sub.setBeginDate(DateUtil.format(sub.getBeginDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(sub.getEndDate()!=null&&sub.getEndDate()!=""){
			sub.setEndDate(DateUtil.format(sub.getEndDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		sub.setIsHistoryRun(ConstantObj.HISTORY_TASK_RUN);
		if(sub.getId()!=null&&sub.getId()!=""){
			//更新
			operationalGuidanceMapper.deleteSubService(sub.getId());
			operationalGuidanceMapper.insertSubService(sub);
		}else{
			//新建
			sub.setId(UUIDUtil.getUuid());
			operationalGuidanceMapper.insertSubService(sub);
		}
		return sub;
	}

	/**
	 * 节点启动停止
	 * LinBin
	 * May 7, 2014
	 * @param nodeId
	 * @param nodeState
	 * @return
	 */
	@Override
	public boolean nodeChangeState(String nodeId, String nodeState) {
		boolean bool = false;
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		if(nodeState.equals(ConstantObj.NODE_STATE_RUN)){
			//节点停止，数据记录
			List<SubscribeService> list = operationalGuidanceMapper.getNeedStopSub(userDetails.getMhoList().get(0).getLevelCode(),null);
			if(list!=null&&list.size()>0){
				List<SubscribeEvent> list2 = new ArrayList<SubscribeEvent>();
				for(SubscribeService ss : list){
					SubscribeEvent se = new SubscribeEvent();
					se.setId(ss.getId());
					se.setServiceId(ss.getServiceId());
					se.setSubTime(ss.getSubTime());
					se.setOrgCode(ss.getOrgCode());
					se.setUserId(ss.getUserId());
					se.setEventTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
					se.setEventType(ConstantObj.EVENT_TYPE_NODE);
					list2.add(se);
				}
				if(list2.size()>0){
					//保存数据记录
					int result = operationalGuidanceMapper.insertSubscribeEvents(list2);
				}
			}
			nodeState=ConstantObj.SERVICE_STATE_STOP;
		}else{
			//节点启动，自动生成订阅服务（通过机构编码查询）
			List<SubscribeEvent> list = operationalGuidanceMapper.getNeedStartSubByOrgCode(userDetails.getMhoList().get(0).getLevelCode());
			if(list!=null&&list.size()>0){
				List<SubscribeService> list2 = new ArrayList<SubscribeService>();
				for(SubscribeEvent se : list){
					SubscribeService ss = new SubscribeService();
					ss.setId(UUIDUtil.getUuid());
					ss.setServiceId(se.getServiceId());
					ss.setIsHistory(ConstantObj.HISTORY_DATA_SELECT);
					ss.setBeginDate(se.getEventTime().substring(0, 8));
					ss.setEndDate(DateUtil.getCurrentDate("yyyyMMdd"));
					ss.setIsCurrent(ConstantObj.CURRENT_DATA_NOT_SELECT);
					ss.setSubTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
					ss.setUserId("system");
					ss.setOrgCode(se.getOrgCode());
					ss.setIsHistoryRun(ConstantObj.HISTORY_TASK_RUN);
					if(!ss.getBeginDate().equals(ss.getEndDate())){
						ss.setEndDate(DateUtil.getAdvanceDay(-1));
						list2.add(ss);
					}
				}
				if(list2.size()>0){
					//新增历史数据订阅
					int result = operationalGuidanceMapper.insertSubscribes(list2);
				}
				//删除事件表（已启动的订阅数据记录）
				operationalGuidanceMapper.deleteSubscribeEvents(list);
			}
			nodeState=ConstantObj.NODE_STATE_RUN;
		}
		int i = operationalGuidanceMapper.nodeChangeState(nodeId,nodeState);
		if(i>0){
			bool = true;
		}
		return bool;
	}
	
	/**
	 * 服务启动停止
	 * LinBin
	 * May 7, 2014
	 * @param serviceId
	 * @param serviceState
	 * @return
	 */
	@Override
	public boolean serviceChangeState(String serviceId, String serviceState) {
		boolean bool = false;
		if(serviceState.equals(ConstantObj.SERVICE_STATE_RUN)){
			//服务停止，记录需要停止的订阅
			List<SubscribeService> list = operationalGuidanceMapper.getNeedStopSub(null,serviceId);
			if(list!=null&&list.size()>0){
				List<SubscribeEvent> list2 = new ArrayList<SubscribeEvent>();
				for(SubscribeService ss : list){
					SubscribeEvent se = new SubscribeEvent();
					se.setId(ss.getId());
					se.setServiceId(ss.getServiceId());
					se.setSubTime(ss.getSubTime());
					se.setOrgCode(ss.getOrgCode());
					se.setUserId(ss.getUserId());
					se.setEventTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
					se.setEventType(ConstantObj.EVENT_TYPE_SERVICE);
					list2.add(se);
				}
				if(list2.size()>0){
					//保存数据记录
					int result = operationalGuidanceMapper.insertSubscribeEvents(list2);
				}
			}
			serviceState=ConstantObj.SERVICE_STATE_STOP;
		}else{
			//服务启动，自动生成订阅服务（通过服务id查询）
			List<SubscribeEvent> list = operationalGuidanceMapper.getNeedStartSubByServiceId(serviceId);
			if(list!=null&&list.size()>0){
				List<SubscribeService> list2 = new ArrayList<SubscribeService>();
				for(SubscribeEvent se : list){
					SubscribeService ss = new SubscribeService();
					ss.setId(UUIDUtil.getUuid());
					ss.setServiceId(se.getServiceId());
					ss.setIsHistory(ConstantObj.HISTORY_DATA_SELECT);
					ss.setBeginDate(se.getEventTime().substring(0, 8));
					ss.setEndDate(DateUtil.getCurrentDate("yyyyMMdd"));
					ss.setIsCurrent(ConstantObj.CURRENT_DATA_NOT_SELECT);
					ss.setSubTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
					ss.setUserId("system");
					ss.setOrgCode(se.getOrgCode());
					ss.setIsHistoryRun(ConstantObj.HISTORY_TASK_RUN);
					if(!ss.getBeginDate().equals(ss.getEndDate())){
						ss.setEndDate(DateUtil.getAdvanceDay(-1));
						list2.add(ss);
					}
				}
				if(list2.size()>0){
					//新增历史数据订阅
					int result = operationalGuidanceMapper.insertSubscribes(list2);
				}
				//删除事件表（已启动的订阅数据记录）
				operationalGuidanceMapper.deleteSubscribeEvents(list);
			}
			serviceState=ConstantObj.SERVICE_STATE_RUN;
		}
		int i = operationalGuidanceMapper.serviceChangeState(serviceId,serviceState);
		if(i>0){
			bool = true;
		}
		return bool;
	}

	/**
	 * 删除节点前校验是否存在订阅
	 * LinBin
	 * May 13, 2014
	 * @param id
	 * @return
	 */
	@Override
	public boolean checkNodeSubscribe(String id) {
		boolean bool = true;
		int i = operationalGuidanceMapper.checkNodeSubscribe(id);
		if(i>0){
			bool = false;
		}
		return bool;
	}

	/**
	 * 删除服务前校验是否存在订阅
	 * LinBin
	 * May 13, 2014
	 * @param id
	 * @return
	 */
	@Override
	public boolean checkServiceSubscribe(String id) {
		boolean bool = true;
		int i = operationalGuidanceMapper.checkServiceSubscribe(id);
		if(i>0){
			bool = false;
		}
		return bool;
	}

	/**
	 * 通过层级码查询节点
	 * LinBin
	 * May 27, 2014
	 * @param levelCode
	 * @return
	 */
	@Override
	public NodeRegister getNodeInfoByLevelCode(String levelCode) {
		return operationalGuidanceMapper.getNodeInfoByLevelCode(levelCode);
	}

	/**
	 * 通过机构名称查询机构
	 * LinBin
	 * May 27, 2014
	 * @param name
	 * @return
	 */
	@Override
	public List<NodeRegister> getOrgByName(String name) {
		return operationalGuidanceMapper.getOrgByName(name);
	}

}
