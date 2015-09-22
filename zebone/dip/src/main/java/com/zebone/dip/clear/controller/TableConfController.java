/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Apr 18, 2013		表配置管理控制层
 */
package com.zebone.dip.clear.controller;

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

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.dip.clear.service.TableConfService;
import com.zebone.dip.clear.vo.TableConf;
import com.zebone.dip.ds.service.DsObjService;
import com.zebone.dip.ds.vo.DsObj;
import com.zebone.util.JsonUtil;
import com.zebone.util.UUIDUtil;
@Controller
@Scope(value="prototype")
public class TableConfController extends BaseController {
	
	@Resource
	private TableConfService tableConfService;
	@Resource
	private DsObjService dsObjService;
	@RequestMapping("clear/tableConfIndex")
	public String tableConfIndex(){
		return "dip/clear/table_conf_index";
	}
	
	@RequestMapping("clear/tableConfList")
	@ResponseBody
	public JsonGrid tableConfList(TableConf tableConf,Pagination<TableConf> page){
		Pagination<TableConf> p = tableConfService.taskInfoList(page.getRowBounds(),tableConf);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		String s = JsonUtil.writeValueAsString(jGrid);
		return jGrid;
	}
	
	@RequestMapping("clear/tableConfEdit")
	public String tableConfEdit(HttpServletRequest request){
		String id = request.getParameter("id");
		TableConf tableConf = tableConfService.getTableConfById(id);
		List<DsObj> list = dsObjService.findDsObjAll();
		request.setAttribute("tableConf", tableConf);
		request.setAttribute("list", list);
		return "dip/clear/table_conf_edit";
	}
	
	@RequestMapping("clear/tableConfInfoSave")
	@ResponseBody
	public Map<String, Object> tableConfInfoSave(TableConf tableConf){
		int result = 0;
		
		tableConf.setTableName(tableConf.getTableName().toUpperCase());
		
		if(StringUtils.isEmpty(tableConf.getId())){
			tableConf.setId(UUIDUtil.getUuid());
			result = tableConfService.saveTableConfInfo(tableConf);
		}else{
			result = tableConfService.updateTableConfInfo(tableConf);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result>0?"true":"false");
		return map;
	}
	
	@RequestMapping("clear/tableConfInfoRemove")
	@ResponseBody
	public Map<String, Object> tableConfInfoRemove(HttpServletRequest request){
		int result = 0;
		String id = request.getParameter("id");
		result = tableConfService.removeTableConfByIds(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result>0?true:false);
		return map;
	}
}
