package com.zebone.dip.operate.service;

import java.util.List;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.operate.vo.NodeRegister;
import com.zebone.dip.operate.vo.ServiceRegister;
import com.zebone.dip.operate.vo.SubscribeService;

public interface OperationalGuidanceService {

	/**
	 * 查询全部节点信息
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	List<NodeRegister> getNodesList(String nodeName,String orgLevelCode);

	/**
	 * 节点信息加载
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeId
	 * @return
	 */
	NodeRegister getNodeInfoById(String nodeId);

	/**
	 * 节点信息新增
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	boolean insertNodeInfo(NodeRegister node);
	
	/**
	 * 节点信息更新
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	boolean updateNodeInfo(NodeRegister node);

	/**
	 * 节点名字唯一校验
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	boolean nodeNameUniqueCheck(String nodeName,String nodeId);

	/**
	 * 节点信息删除
	 * LinBin
	 * May 4, 2014
	 * @param nodeId
	 * @return
	 */
	boolean deleteNodeInfo(String nodeId);

	/**
	 * 查询全部服务信息
	 * LinBin
	 * May 4, 2014
	 * @param page
	 * @param serviceRegister
	 * @return
	 */
	Pagination<ServiceRegister> getServicesPage(Pagination<ServiceRegister> page, ServiceRegister serviceRegister);

	/**
	 * 服务信息加载
	 * LinBin
	 * May 4, 2014
	 * @param serviceId
	 * @return
	 */
	ServiceRegister getServiceInfoById(String serviceId);

	/**
	 * 服务名字唯一校验
	 * LinBin
	 * May 4, 2014
	 * @param serviceName
	 * @param id
	 * @return
	 */
	boolean serviceNameUniqueCheck(String serviceName, String serviceId);

	/**
	 * 服务信息更新
	 * LinBin
	 * May 4, 2014
	 * @param service
	 * @return
	 */
	boolean updateServiceInfo(ServiceRegister service);
	
	/**
	 * 服务信息新增
	 * LinBin
	 * May 4, 2014
	 * @param service
	 * @return
	 */
	boolean insertServiceInfo(ServiceRegister service);

	/**
	 * 服务信息删除
	 * LinBin
	 * May 4, 2014
	 * @param id
	 * @return
	 */
	boolean deleteServiceInfo(String serviceId);

	/**
	 * 分页查询订阅信息
	 * LinBin
	 * May 5, 2014
	 * @param page
	 * @param subscribeService
	 * @return
	 */
	Pagination<SubscribeService> getSubscribePage(Pagination<SubscribeService> page, SubscribeService subscribeService);

	/**
	 * 获得所有服务
	 * LinBin
	 * May 5, 2014
	 * @return
	 */
	List<ServiceRegister> getAllService();

	/**
	 * 获取当前登录用户所在机构的所有订阅服务
	 * LinBin
	 * May 6, 2014
	 * @param orgCode
	 * @return
	 */
	List<SubscribeService> getAllSubServiceByOrg(String orgCode);

	/**
	 * 订阅信息删除
	 * LinBin
	 * May 6, 2014
	 * @param subid
	 * @return
	 */
	boolean deleteSubService(String subid);

	/**
	 * 订阅信息保存
	 * LinBin
	 * May 6, 2014
	 * @param sub
	 * @return
	 */
	SubscribeService saveSubService(SubscribeService sub);

	/**
	 * 节点启动停止
	 * LinBin
	 * May 7, 2014
	 * @param nodeId
	 * @param nodeState
	 * @return
	 */
	boolean nodeChangeState(String nodeId, String nodeState);

	/**
	 * 服务启动停止
	 * LinBin
	 * May 7, 2014
	 * @param serviceId
	 * @param serviceState
	 * @return
	 */
	boolean serviceChangeState(String serviceId, String serviceState);

	/**
	 * 删除前校验是否存在订阅
	 * LinBin
	 * May 13, 2014
	 * @param id
	 * @return
	 */
	boolean checkNodeSubscribe(String id);

	/**
	 * 删除前校验是否存在订阅
	 * LinBin
	 * May 13, 2014
	 * @param id
	 * @return
	 */
	boolean checkServiceSubscribe(String id);

	/**
	 * 通过层级码查询节点
	 * LinBin
	 * May 27, 2014
	 * @param levelCode
	 * @return
	 */
	NodeRegister getNodeInfoByLevelCode(String levelCode);

	/**
	 * 所属机构唯一校验
	 * LinBin
	 * May 27, 2014
	 * @param nodeOrg
	 * @param id
	 * @return
	 */
	boolean nodeOrgUniqueCheck(String nodeOrg, String id);

	/**
	 * 通过机构名称查询机构
	 * LinBin
	 * May 27, 2014
	 * @param name
	 * @return
	 */
	List<NodeRegister> getOrgByName(String name);

}
