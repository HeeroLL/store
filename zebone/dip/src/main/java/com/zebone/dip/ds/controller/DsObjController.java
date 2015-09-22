/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Feb 26, 2013     数据源对象控制层
 */
package com.zebone.dip.ds.controller;

import java.util.HashMap;
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
import com.zebone.dip.ds.service.DsObjService;
import com.zebone.dip.ds.vo.DsObj;
import com.zebone.util.UUIDUtil;
@Controller
@Scope(value="prototype")
public class DsObjController extends BaseController {
	@Resource
	private DsObjService dsObjService;
	
	@RequestMapping("ds/datasourceIndex")
	public String datasourceIndex(){
		return "dip/ds/datasource_index";
	}
	
	@RequestMapping("ds/dsObjList")
	public @ResponseBody JsonGrid dsObjList(DsObj dsObj,Pagination<DsObj> page){
		Pagination<DsObj> p = dsObjService.searchListDsObj(page.getRowBounds(),dsObj);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	@RequestMapping("ds/dsObjInfoEdit")
	public String dsObjInfoEdit(HttpServletRequest request){
		String id = request.getParameter("id");
		DsObj dsObj = dsObjService.getDsObjInfoById(id);
		request.setAttribute("dsObj", dsObj);
		return "dip/ds/datasource_edit";
	}
	
	@RequestMapping("ds/dsObjInfoSave")
	public @ResponseBody Map<String, Object> dsObjInfoSave(DsObj dsObj){
		int result =0;
		if(StringUtils.isEmpty(dsObj.getId())){
			dsObj.setId(UUIDUtil.getUuid());
			dsObj.setDelFlag("1");
			result = dsObjService.saveDsObjInfo(dsObj);
		}else{
			result = dsObjService.updateDsObjInfo(dsObj);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result == 1 ? true:false);
		map.put("id", dsObj.getId());
		return map;
	}
	
	@RequestMapping("ds/dsObjRemove")
	public @ResponseBody Map<String, Object> dsObjInfoRemove(HttpServletRequest request){
		String id = request.getParameter("id");
		int result = dsObjService.removeDsObjById(id);
		Map<String , Object> map = new HashMap<String, Object>();
		if(result == 1){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		return map;
	}
	
	@RequestMapping("ds/detectDsObjById")
	public @ResponseBody Map<String, Object> detectDsObjById(HttpServletRequest request){
		String id = request.getParameter("id");
		DsObj dsObj = dsObjService.getDsObjInfoById(id);
		int result = dsObjService.detectDsObj(dsObj);
		Map<String , Object> map = new HashMap<String, Object>();
		if(result == 1){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		return map;
	}
}
