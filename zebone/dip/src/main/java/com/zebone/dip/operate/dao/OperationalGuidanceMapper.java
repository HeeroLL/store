package com.zebone.dip.operate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.operate.vo.NodeRegister;
import com.zebone.dip.operate.vo.ServiceRegister;
import com.zebone.dip.operate.vo.SubscribeEvent;
import com.zebone.dip.operate.vo.SubscribeService;

@Mapper
public interface OperationalGuidanceMapper {

	/**
	 * 查询全部节点信息
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	List<NodeRegister> getNodesList(@Param("nodeName")String nodeName,@Param("orgLevelCode")String orgLevelCode);

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
	int insertNodeInfo(NodeRegister node);

	/**
	 * 节点信息更新
	 * LinBin
	 * Apr 29, 2014
	 * @param node
	 * @return
	 */
	int updateNodeInfo(NodeRegister node);

	/**
	 * 通过名字查询节点（精确查询）
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	List<NodeRegister> getNodesListByName(String nodeName);

	/**
	 * 节点信息删除
	 * LinBin
	 * May 4, 2014
	 * @param nodeId
	 * @return
	 */
	int deleteNodeInfo(String nodeId);

	/**
	 * 分页查询服务信息
	 * LinBin
	 * May 4, 2014
	 * @param rowBounds
	 * @param serviceRegister
	 * @return
	 */
	List<ServiceRegister> getServiceInfoList(RowBounds rowBounds,ServiceRegister serviceRegister);

	/**
	 * 查询服务信息总数
	 * LinBin
	 * May 4, 2014
	 * @param serviceRegister
	 * @return
	 */
	int getServiceInfoCount(ServiceRegister serviceRegister);

	/**
	 * 服务信息加载
	 * LinBin
	 * May 4, 2014
	 * @param serviceId
	 * @return
	 */
	ServiceRegister getServiceInfoById(String serviceId);

	/**
	 * 通过名字查询服务（精确查询）
	 * LinBin
	 * Apr 29, 2014
	 * @param nodeName
	 * @return
	 */
	List<ServiceRegister> getServiceListByName(String serviceName);

	/**
	 * 服务信息新增
	 * LinBin
	 * May 4, 2014
	 * @param service
	 * @return
	 */
	int insertServiceInfo(ServiceRegister service);

	/**
	 * 服务信息更新
	 * LinBin
	 * May 4, 2014
	 * @param service
	 * @return
	 */
	int updateServiceInfo(ServiceRegister service);

	/**
	 * 服务信息删除
	 * LinBin
	 * May 4, 2014
	 * @param id
	 * @return
	 */
	int deleteServiceInfo(String serviceId);

	/**
	 * 分页查询订阅务信息
	 * LinBin
	 * May 4, 2014
	 * @param rowBounds
	 * @param serviceRegister
	 * @return
	 */
	List<SubscribeService> getSubscribeInfoList(RowBounds rowBounds,SubscribeService subscribeService);

	/**
	 * 查询订阅信息总数
	 * LinBin
	 * May 4, 2014
	 * @param serviceRegister
	 * @return
	 */
	int getSubscribeInfoCount(SubscribeService subscribeService);

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
	int deleteSubService(String subid);

	/**
	 * 订阅信息保存
	 * LinBin
	 * May 6, 2014
	 * @param subid
	 * @return
	 */
	int insertSubService(SubscribeService sub);

	/**
	 * 节点启动停止
	 * LinBin
	 * May 7, 2014
	 * @param nodeId
	 * @param nodeState
	 * @return
	 */
	int nodeChangeState(@Param("nodeId")String nodeId,@Param("nodeState") String nodeState);

	/**
	 * 服务启动停止
	 * LinBin
	 * May 7, 2014
	 * @param serviceId
	 * @param serviceState
	 * @return
	 */
	int serviceChangeState(@Param("serviceId")String serviceId, @Param("serviceState") String serviceState);

	/**
	 * 删除节点前校验是否存在订阅
	 * LinBin
	 * May 13, 2014
	 * @param id
	 * @return
	 */
	int checkNodeSubscribe(String id);

	/**
	 * 删除服务前校验是否存在订阅
	 * LinBin
	 * May 13, 2014
	 * @param id
	 * @return
	 */
	int checkServiceSubscribe(String id);

	/**
	 * 需要停止的服务
	 * LinBin
	 * May 16, 2014
	 * @param levelCode
	 * @return
	 */
	List<SubscribeService> getNeedStopSub(@Param("levelCode")String levelCode,@Param("serviceId")String serviceId);

	/**
	 * 保存需要停止的服务
	 */
	int insertSubscribeEvents(List<SubscribeEvent> list);

	/**
	 * 需要启动的服务（通过机构编码查询）
	 * LinBin
	 * May 16, 2014
	 * @param levelCode
	 * @return
	 */
	List<SubscribeEvent> getNeedStartSubByOrgCode(String levelCode);
	
	/**
	 * 需要启动的服务（通过服务id查询）
	 * LinBin
	 * May 16, 2014
	 * @param levelCode
	 * @return
	 */
	List<SubscribeEvent> getNeedStartSubByServiceId(String serviceId);

	/**
	 * 新增历史数据订阅
	 * LinBin
	 * May 16, 2014
	 * @param list2
	 * @return
	 */
	int insertSubscribes(List<SubscribeService> list2);

	/**
	 * 删除事件表（已启动的订阅数据记录）
	 * LinBin
	 * May 16, 2014
	 * @param list
	 * @return
	 */
	int deleteSubscribeEvents(List<SubscribeEvent> list);

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
	 * @return
	 */
	List<NodeRegister> getNodesListByOrg(String nodeOrg);

	/**
	 * 通过机构名称查询机构
	 * LinBin
	 * May 27, 2014
	 * @param name
	 * @return
	 */
	List<NodeRegister> getOrgByName(String name);

	

}
