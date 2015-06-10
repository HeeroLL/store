package com.zebone.dip.task.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.app.module.vo.BtpModule;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.dip.node.service.NodeService;
import com.zebone.dip.node.vo.Node;
import com.zebone.dip.task.service.TaskService;
import com.zebone.dip.task.vo.Task;
import com.zebone.util.JsonUtil;
import com.zebone.util.UUIDUtil;

/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Feb 20, 2013		任务管理controller层
 */
@Controller
@Scope(value="prototype")
public class TaskController extends BaseController {
	
	@Resource
	private TaskService taskService;
	@Resource
	private NodeService nodeService;
	
	@RequestMapping("task/taskIndex")
	public String taskIndex(HttpServletRequest request){
		List<Node> list = nodeService.getAllNodeInfo();
		request.setAttribute("list", list);
		return "dip/task/task_index";
	}
	
	/**
	 * 获取任务列表信息
	 * @param pTask
	 * @param page
	 * @author caixl
	 * @date Feb 20, 2013
	 * @return JsonGrid
	 */
	@RequestMapping("task/taskInfoList")
	public @ResponseBody JsonGrid taskInfoList(Task pTask,Pagination<BtpModule> page){
		Pagination<Task> p = taskService.taskInfoList(page.getRowBounds(),pTask);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		String s = JsonUtil.writeValueAsString(jGrid);
		return jGrid;
	}
	
	@RequestMapping("task/taskInfoEdit")
	public String taskInfoEdit(HttpServletRequest request){
		String id = request.getParameter("id");
		List<Node> list = nodeService.getAllNodeInfo();
		Task pTask = taskService.getTaskInfoById(id);
		request.setAttribute("pTask", pTask);
		request.setAttribute("list", list);
		return "dip/task/task_edit";
	}
	
	@RequestMapping("task/taskInfoSave")
	public @ResponseBody Map<String, String> taskInfoSave(Task pTask){
		int result = 0;
		if(StringUtils.isEmpty(pTask.getId())){
			pTask.setId(UUIDUtil.getUuid());
			pTask.setDelFlag("1");
			result = taskService.saveTaskInfo(pTask);
		}else{
			result = taskService.updateTaskInfo(pTask);
		}
		Map<String , String> map = new HashMap<String, String>();
		map.put("success", result>0?"true":"false");
	    map.put("id", pTask.getId());
		return map;
	}
	
	@RequestMapping("task/updateTaskState")
	public @ResponseBody Map<String, Object> updateTaskState(HttpServletRequest request){
		String taskData = request.getParameter("taskData");
		int result = taskService.updateTaskState(taskData);
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("success", result>0 ? true:false);
		return map;
	}
	
	@RequestMapping("task/taskInfoRemove")
	public @ResponseBody Map<String, Object> taskInfoRemove(HttpServletRequest request){
		String id = request.getParameter("id");
		int result = taskService.removetaskInfoById(id);
		Map<String , Object> map = new HashMap<String, Object>();
		if(result == 1){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		return map;
	}
	
	@RequestMapping("task/taskTimeConf")
	public String taskTimeConf(HttpServletRequest request){
		
		return "dip/task/task_time";
	}
}
