package com.zebone.dip.etl.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.dip.etl.dao.NodeAndJobRuleMapper;
import com.zebone.dip.etl.dao.NodeAndTransRuleMapper;
import com.zebone.dip.etl.service.NodeETLService;
import com.zebone.dip.etl.vo.NodeAndJobRule;
import com.zebone.dip.etl.vo.NodeAndTransRule;

/**
 * @author songjunjie
 * 
 */
@Service("nodeETLService")
public class NodeETLServiceImpl implements NodeETLService {
	@Resource
	private NodeAndJobRuleMapper nodeAndJobRuleMapper;

	@Resource
	private NodeAndTransRuleMapper nodeAndTransRuleMapper;

	@Override
	public void addJob(String nodeId, Integer jobId) {
		NodeAndJobRule nodeAndJobRule = new NodeAndJobRule();
		nodeAndJobRule.setNodeId(nodeId);
		nodeAndJobRule.setJobId(jobId);
		nodeAndJobRule.setCreateTime(new Date());
		nodeAndJobRuleMapper.insert(nodeAndJobRule);
	}

	@Override
	public void addTrans(String nodeId, Integer transId) {
		NodeAndTransRule nodeAndTransRule = new NodeAndTransRule();
		nodeAndTransRule.setNodeId(nodeId);
		nodeAndTransRule.setTransId(transId);
		nodeAndTransRule.setCreateTime(new Date());
		nodeAndTransRuleMapper.insert(nodeAndTransRule);
	}

	@Override
	public void removeJob(String nodeId, Integer jobId) {
		nodeAndJobRuleMapper.delete(nodeId, jobId);

	}

	@Override
	public void removeTrans(String nodeId, Integer transId) {
		nodeAndTransRuleMapper.delete(nodeId, transId);
	}

	/**
	 * @see com.zebone.dip.etl.service.NodeETLService#getTansIdByName(java.lang.String)
	 */
	@Override
	public Integer getTansIdByName(String name) {
		List<Integer> list = this.nodeAndTransRuleMapper.getTansIdByName(name);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * @see com.zebone.dip.etl.service.NodeETLService#getJobIdByName()
	 */
	@Override
	public Integer getJobIdByName(String name) {
		List<Integer> list = this.nodeAndJobRuleMapper.getJobIdByName(name);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 找到未部署的任务
	 * @return
	 */
	public List<String> getJobForNotDeploy(){
		List<String> list = this.nodeAndJobRuleMapper.getJobForNotDeploy();
		return list;
	}
	
	/**
	 * 查询出未部署的转换
	 * @return
	 */
	public List<String> getTransForNotDeploy(){
		List<String> list = this.nodeAndTransRuleMapper.getTransForNotDeploy();
		return list;
	}
}
