package com.zebone.dip.etl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.etl.vo.NodeAndJobRule;

/**
 * 
 * @author songjunjie
 * 
 */
@Mapper
public interface NodeAndJobRuleMapper {
	public void insert(NodeAndJobRule nodeAndJobRule);

	public void delete(@Param("nodeId") String nodeId,
			@Param("jobId") Integer jobId);

	public List<String> getJobNameByNodeId(String nodeId);
	
	/**
	 * 根据转换的名字得到对应的id
	 * @return
	 */
	public List<Integer> getJobIdByName(String name);
	
	/**
	 * 找到未部署的任务
	 * @return
	 */
	public List<String> getJobForNotDeploy();
}
