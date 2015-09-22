package com.zebone.dip.etl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.etl.vo.NodeAndTransRule;

/**
 * 
 * @author songjunjie
 *
 */
@Mapper
public interface NodeAndTransRuleMapper {
	public void insert(NodeAndTransRule nodeAndTransRule);

	public void delete(@Param("nodeId") String nodeId , @Param("transId") Integer transId);

	public List<String> getTransNameByNodeId(String nodeId);
	
	/**
	 * 根据转换的名字，得到对应的id
	 * @param name
	 * @return
	 */
	public List<Integer> getTansIdByName(String name);
	
	/**
	 * 查询出未部署的转换
	 * @return
	 */
	public List<String> getTransForNotDeploy();
}
