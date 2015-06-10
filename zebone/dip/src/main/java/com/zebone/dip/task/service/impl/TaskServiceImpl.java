/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Feb 20, 2013		任务业务层
 */
package com.zebone.dip.task.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.task.dao.TaskMapper;
import com.zebone.dip.task.service.TaskService;
import com.zebone.dip.task.vo.Task;
@Service("taskService")
public class TaskServiceImpl implements TaskService{

	@Resource
	private TaskMapper taskMapper;
	
	/**
	 * @author caixl
	 * @date Feb 20, 2013
	 * @description TODO 获取列表的相关信息
	 * @param rowBounds
	 * @param task 查询列表的参数
	 * @return Pagination<PTask>
	 */
	@Override
	public Pagination<Task> taskInfoList(RowBounds rowBounds, Task task) {
		Pagination<Task> page = new Pagination<Task>();
		page.setData(taskMapper.searchListPTask(rowBounds,task));
		page.setTotalCount(taskMapper.searchTotalCountPTask(task));
		return page;
	}

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 获取任务相关信息
	 * @param id 任务标识
	 * @return PTask
	 */
	@Override
	public Task getTaskInfoById(String id) {
		return taskMapper.getTaskInfoById(id);
	}

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 保存任务的相关信息
	 * @param task
	 * @return int
	 */
	@Override
	public int saveTaskInfo(Task task) {
		return taskMapper.saveTaskInfo(task);
	}

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 更新任务的相关信息
	 * @param task
	 * @return int
	 */
	@Override
	public int updateTaskInfo(Task task) {
		return taskMapper.updateTaskInfo(task);
	}

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 启停各种任务
	 * @param taskData
	 * @return int
	 */
	@Override
	public int updateTaskState(String taskData) {
		String[] datas = taskData.split(";");
		for(int i=0;i<datas.length;i++){
			String[] strs = datas[i].split(",",2);
			Task pTask = new Task();
			pTask.setId(strs[0]);
			if("1".equals(strs[1])){
				pTask.setTaskDeploy("0");
			}else{
				pTask.setTaskDeploy("1");
			}
			taskMapper.updateTaskState(pTask);
		}
		return 1;
	}

	/**
	 * @author caixl
	 * @date Feb 25, 2013
	 * @description TODO 逻辑删除任务相关信息
	 * @param id 任务标识
	 * @return int
	 */
	@Override
	public int removetaskInfoById(String id) {
		String[] ids = id.split(",");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<ids.length;i++){
			int result = taskMapper.getStateTaskById(ids[i]);
			if(result < 1){
				list.add(ids[i]);
			}
		}
		if(list!=null && list.size()>0){
			taskMapper.updateBatchTaskByIds(list);
			return 1;
		}
		return 0;
	}
	
}
