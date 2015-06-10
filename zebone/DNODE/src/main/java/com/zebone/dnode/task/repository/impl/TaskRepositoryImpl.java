package com.zebone.dnode.task.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zebone.dnode.task.dto.Node;
import com.zebone.dnode.task.dto.Task;
import com.zebone.dnode.task.enums.TaskType;
import com.zebone.dnode.task.repository.TaskRepository;

@Repository("taskRepository")
public class TaskRepositoryImpl implements TaskRepository{

    @Autowired  @Qualifier("centerConfigOracleJdbcTemplate")
	private NamedParameterJdbcTemplate npJdbcTemplate;

//	@Value("#{syspt['node.name']}")
	private String taskNode;

	public List<Task> getTasks(String taskCode) {
		String sql = "SELECT ID_, TASK_CODE, TASK_DESC, TASK_STATE, TASK_DEPLOY, TASK_FREQ, TASK_TIME, TASK_TYPE, TASK_NODE, "
				+"DEL_FLAG, TASK_TARGET "
				+ " FROM P_TASK WHERE TASK_NODE = ?";
		return npJdbcTemplate.getJdbcOperations().query(sql,
				new Object[] { taskCode }, new TaskMapper());
	}


	@Override
	public Node getTaskNode() {
		// TODO Auto-generated method stub
		String sql = "SELECT ID_, NODE_DESC, NODE_STATE, NODE_IP, NODE_NAME, NODE_NET, NODE_RUN,DEL_FLAG,NODE_PORT "
				+ " FROM P_NODE WHERE NODE_NAME = ? AND DEL_FLAG = 1";
		return npJdbcTemplate.getJdbcOperations().queryForObject(sql,
				new Object[] { taskNode }, new NodeMapper());
	}
	
	

	@Override
	public void updateTaskExecuteStatus(String taskState, String taskId) {
		// TODO Auto-generated method stub
		String sql = "UPDATE P_TASK set TASK_STATE = ? WHERE ID_ = ?";
		npJdbcTemplate.getJdbcOperations().update(sql, taskState, taskId);
	}
	
	
	private static final class TaskMapper implements RowMapper<Task> {
		@Override
		public Task mapRow(ResultSet rs, int paramInt) throws SQLException {
			// TODO Auto-generated method stub
			Task task = new Task();
			task.setId(rs.getString("ID_"));
			task.setTaskCode(rs.getString("TASK_CODE"));
			task.setTaskDesc(rs.getString("TASK_DESC"));
			task.setTaskState(rs.getString("TASK_STATE"));
			task.setTaskDeploy(rs.getString("TASK_DEPLOY"));
			task.setTaskFreq(StringUtils.defaultString(rs.getString("TASK_FREQ"),""));
			task.setTaskTime(rs.getString("TASK_TIME"));
			task.setTaskType(TaskType.getTypeFromString(rs.getString("TASK_TYPE")));
			task.setTaskNode(rs.getString("TASK_NODE"));
			task.setDelFlag(rs.getString("DEL_FLAG"));
			task.setTaskTarget(rs.getString("TASK_TARGET"));
			return task;
		}
	}
	
	private static final class NodeMapper implements RowMapper<Node> {
		@Override
		public Node mapRow(ResultSet rs, int paramInt) throws SQLException {
			// TODO Auto-generated method stub
			Node node = new Node();
			node.setId(rs.getString("ID_"));
			node.setNodeDesc(rs.getString("NODE_DESC"));
			node.setNodeState(rs.getString("NODE_STATE"));
			node.setNodeIp(rs.getString("NODE_IP"));
			node.setNodesName(rs.getString("NODE_NAME"));
			node.setNodeNet(rs.getString("NODE_NET"));
			node.setNodeRun(rs.getString("NODE_RUN"));
			node.setDelFlag(rs.getString("DEL_FLAG"));
			node.setNodePort(rs.getString("NODE_PORT"));
			return node;
		}
	}


	


}
