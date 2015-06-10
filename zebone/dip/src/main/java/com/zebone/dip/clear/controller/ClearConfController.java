/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Apr 18, 2013		清洗配置管理控制层
 */
package com.zebone.dip.clear.controller;

import java.util.ArrayList;
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

import com.zebone.btp.core.web.BaseController;
import com.zebone.dip.clear.service.ClearConfService;
import com.zebone.dip.clear.service.TableConfService;
import com.zebone.dip.clear.vo.ClearCloumn;
import com.zebone.dip.clear.vo.ClearConf;
import com.zebone.dip.clear.vo.TableConf;
import com.zebone.dip.dictionary.service.DictService;
import com.zebone.dip.dictionary.vo.PDictType;
import com.zebone.dip.node.service.NodeService;
import com.zebone.dip.node.vo.Node;
import com.zebone.dip.task.service.TaskService;
import com.zebone.dip.task.vo.Task;
import com.zebone.util.JsonUtil;
import com.zebone.util.UUIDUtil;
@Controller
@Scope(value="prototype")
public class ClearConfController extends BaseController {
	
	@Resource
	private TableConfService tableConfService;
	@Resource
	private ClearConfService clearConfService;
	@Resource
	private TaskService taskService;
	@Resource
	private NodeService nodeService;
	@Resource
	private DictService dictService;
	@RequestMapping("clear/clearIndex")
	public String clearIndex(){
		return "dip/clear/clear_index";
	}
	
	@RequestMapping("clear/tableTreeLeft")
	public String tableTreeLeft(HttpServletRequest request){
		List<TableConf> list = tableConfService.getAllTableConf();
		if(list == null){
			list = new ArrayList<TableConf>();
		}
		TableConf t1 = new TableConf();
		t1.setTableName("源数据层");
		t1.setId("1");
		TableConf t2 = new TableConf();
		t2.setTableName("报表层");
		t2.setId("2");
		list.add(t1);
		list.add(t2);
		String str = JsonUtil.writeValueAsString(list);
		request.setAttribute("list", str);
		return "dip/clear/table_tree_left";
	}
	
	@RequestMapping("clear/clearConfMain")
	public String clearConfMain(TableConf tableConf,HttpServletRequest request){
		//获取表的所有字段
		List<String> cols = null;
		if(!StringUtils.isEmpty(tableConf.getId())){
			cols = clearConfService.getColsByTableConfInfo(tableConf);
		}
		//获取清洗配置表信息
		ClearConf clearConf = clearConfService.getClearConfByTableId(tableConf.getId());
		Task task = null;
		List<ClearCloumn> cCloumns = null;
		List<PDictType> pDictTypes = null;
		//获取某个数据源的数据字典类型
		pDictTypes = dictService.getAlldicttypeBydsId(tableConf.getDsId());
		if(clearConf!=null){
			//获取任务相关信息
			task = taskService.getTaskInfoById(clearConf.getTaskId());
			//获取字段配置相关信息的集合
			cCloumns = clearConfService.getClearCloumnsByClearId(clearConf.getId());
		}
		List<Node> list = nodeService.getAllNodeInfo();
		request.setAttribute("pDictTypes", pDictTypes);
		request.setAttribute("cols", cols);
		request.setAttribute("pTask", task);
		request.setAttribute("cCloumns", cCloumns);
		request.setAttribute("tableId", tableConf.getId());
		request.setAttribute("clearConf", clearConf);
		request.setAttribute("list", list);
		return "dip/clear/clear_conf_main";
	}
	
	@RequestMapping("clear/clearInfoSave")
	@ResponseBody
	public Map<String, Object> clearInfoSave(Task task,ClearConf clearConf,HttpServletRequest request){
		String clearData = request.getParameter("clearData");
		int result = 0;
		String msg = "";
		if(StringUtils.isEmpty(clearConf.getId())){
			clearConf.setId(UUIDUtil.getUuid());
			task.setId(UUIDUtil.getUuid());
			task.setDelFlag("1");
			clearConf.setTaskId(task.getId());
			result = clearConfService.saveClearInfo(task,clearConf,clearData);
			if(result >0) {
				msg = "保存成功";
			}else{
				msg = "保存失败";
			}
		}else{
			task.setId(clearConf.getTaskId());
			result = clearConfService.updateClearInfo(task,clearConf,clearData);
			if(result >0) {
				msg = "更新成功";
			}else{
				msg = "更新失败";
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result >0 ? true:false);
		map.put("msg", msg);
		map.put("id", clearConf.getId());
		map.put("taskId", task.getId());
		return map;
	}
	
	@RequestMapping("clear/clearInfoPublish")
	@ResponseBody
	public Map<String, Object> clearInfoPublish(HttpServletRequest request){
		String id = request.getParameter("id");
		ClearConf clearConf = null;
		if(clearConfService.updateClearDeployFlag(id)>0){
			clearConf = clearConfService.getClearConfById(id);
		}
		//ClearConf clearConf = clearConfService
		Map<String, Object> map = new HashMap<String, Object>();
		if(clearConf!=null){
			map.put("success", true);
			map.put("deployFlag", clearConf.getDeployFlag());
		}else{
			map.put("success", false);
		}
		return map;
	}
}
