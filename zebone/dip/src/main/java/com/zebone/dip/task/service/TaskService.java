/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * Administrator              New             Feb 20, 2013
 */
package com.zebone.dip.task.service;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.task.vo.Task;

public interface TaskService {

	/**
	 * @author caixl
	 * @date Feb 20, 2013
	 * @description TODO 获取列表的相关信息
	 * @param rowBounds
	 * @param task 查询列表的参数
	 * @return Pagination<PTask>
	 */
	Pagination<Task> taskInfoList(RowBounds rowBounds, Task task);

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
	 * @description TODO 保存任务的相关信息
	 * @param task
	 * @return int
	 */
	int saveTaskInfo(Task task);

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 更新任务的相关信息
	 * @param task
	 * @return int
	 */
	int updateTaskInfo(Task task);

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 启停各种任务
	 * @param taskData
	 * @return int
	 */
	int updateTaskState(String taskData);

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 逻辑删除任务相关信息
	 * @param id 任务标识
	 * @return int
	 */
	int removetaskInfoById(String id);

}
