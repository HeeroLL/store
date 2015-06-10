package com.zebone.dip.etl.service;

import java.util.List;

/**
 * 跟etl和节点相关的服务。
 * 
 * @author songjunjie
 * 
 */
public interface NodeETLService {
	public void addTrans(String nodeId, Integer transId);

	public void addJob(String nodeId, Integer jobId);

	public void removeTrans(String nodeId, Integer transId);

	public void removeJob(String nodeId, Integer jobId);
	
	/**
	 * 根据转换的名字，得到对应的id
	 * @param name
	 * @return
	 */
	public Integer getTansIdByName(String name);
	
	/**
	 * 根据转换的名字得到对应的id
	 * @return
	 */
	public Integer getJobIdByName(String name);
	
	
	/**
	 * 找到未部署的任务
	 * @return
	 */
	public List<String> getJobForNotDeploy();
	
	/**
	 * 查询出未部署的转换
	 * @return
	 */
	public List<String> getTransForNotDeploy();
}
