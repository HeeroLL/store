package com.zebone.dip.task.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.task.vo.Task;
@Mapper
public interface TaskMapper {

	/**
	 * @author caixl
	 * @date Feb 21, 2013
	 * @description TODO 获取任务列表信息
	 * @param rowBounds
	 * @param task 查询参数
	 * @return List<PTask>
	 */
	List<Task> searchListPTask(RowBounds rowBounds, Task task);

	/**
	 * @author caixl
	 * @date Feb 21, 2013
	 * @description TODO 获取任务总数量
	 * @param task 查询参数
	 * @return int
	 */
	int searchTotalCountPTask(Task task);

	/**
	 * @author caixl
	 * @date Feb 23, 2013
	 * @description TODO 查询某一节点有几个任务
	 * @param taskNode 节点标识
	 * @return int
	 */
	int getCountTaskByNodeId(String taskNode);

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 获取任务相关信息
	 * @param id 任务标识
	 * @return PTask
	 */
	Task getTaskInfoById(String id);

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 保存任务相关信息
	 * @param task
	 * @return int
	 */
	int saveTaskInfo(Task task);

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 更新任务相关信息
	 * @param task
	 * @return int
	 */
	int updateTaskInfo(Task task);

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 更新任务的状态
	 * @param pTask 任务
	 * @return int
	 */
	int updateTaskState(Task pTask);

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 根据id获取启用任务数
	 * @param id 任务标识
	 * @return int
	 */
	int getStateTaskById(String id);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 批量删除相关任务
	 * @param list 任务标识的集合
	 * @return int
	 */
	int updateBatchTaskByIds(List<String> list);
	/**
	 * @author caixl
	 * @date Apr 15, 2013
	 * @description TODO 物理删除某个测试任务
	 * @param id
	 * @return int
	 */
	int deleteTaskById(String id);
}