package com.zebone.btp.app.module.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.app.module.service.BtpModuleService;
import com.zebone.btp.app.module.vo.BtpModule;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.util.JsonUtil;
import com.zebone.util.UUIDUtil;
/**
 * 模块管理的控制层
 * @author 蔡祥龙
 * 2012-11-24
 */
@Controller
@Scope(value="prototype")
public class BtpModuleController extends BaseController{
	@Resource
	private BtpModuleService btpModuleService;
	/**
	 * 功能模块管理入口界面
	 * @author 蔡祥龙
	 * 2012-11-24
	 * @return
	 */
	@RequestMapping("btp/module/moduleIndex")
	public String btpModelIndex(){
		return "btp/module/module_index";
	}
	/**
	 * 左侧功能模块树展示页面
	 * @author 蔡祥龙
	 * 2012-11-24
	 * @return
	 */
	@RequestMapping("btp/module/moduleLeftTree")
	public String btpModelTree(HttpServletRequest request,HttpServletResponse response){
		List<BtpModule> list = btpModuleService.getBtpModuleInfos();
//		BtpModule s = new BtpModule();
//		s.setModuleId("1000");
//		s.setParentModuleId("");
//		s.setModuleName("模块树");
//		list.add(s);
		String str = JsonUtil.writeValueAsString(list);
		request.setAttribute("list", str);
		return "btp/module/module_left_tree";
	}
	/**
	 * 右侧功能模块管理列表展示页面
	 * @author 蔡祥龙
	 * 2012-11-24
	 * @return
	 */
	@RequestMapping("btp/module/moduleRightIndex")
	public String btpModelRight(){
		return "btp/module/module_right_index";
	}
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-27
	 * @description 模块信息新建修改页面
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping("btp/module/moduleRightEdit")
	public String rightModelEdit(HttpServletRequest request,HttpServletResponse response){
		String moduleId = request.getParameter("moduleId");
		String parentModuleId = request.getParameter("parentModuleId");
		BtpModule btpModule = btpModuleService.getBtpModuleById(moduleId);
		if(btpModule == null){
			btpModule = new BtpModule();
			btpModule.setParentModuleId(parentModuleId);
		}
		request.setAttribute("btpModule", btpModule);
		return "btp/module/module_right_edit";
	}
	/**
	 * 
	 * @author caixianglong
	 * @date 2012-11-27
	 * @description 保存更新模块管理相关信息
	 * @param btpModule
	 * @return Map<String,String>
	 */
	@RequestMapping("btp/module/moduleInfoSave")
	public @ResponseBody Map<String , String> moduleInfoSave(BtpModule btpModule){
		int result =0;
		if("1".equals(btpModule.getRemark())){
			btpModule.setParentModuleId("1000");
		}
		if(StringUtils.isEmpty(btpModule.getModuleId())){
			btpModule.setModuleId(UUIDUtil.getUuid());
			result = btpModuleService.saveBtpModuleInfo(btpModule);
		}else{
			result = btpModuleService.updateBtpModuleInfo(btpModule);
		}
		Map<String , String> map = new HashMap<String, String>();
	    map.put("success", result>0?"true":"false");
	    map.put("moduleId", btpModule.getModuleId());
		return map;
	}
	/**
	 * 
	 * @author 蔡祥龙
	 * @date 2012-11-28
	 * @description 列表显示模块信息
	 * @param btpModule 
	 * @param request
	 * @return Pagination<BtpModule>
	 */
	@RequestMapping("btp/module/moduleInfoList")
	public @ResponseBody JsonGrid moduleInfoList(BtpModule btpModule,HttpServletRequest request,Pagination<BtpModule> page){
		Pagination<BtpModule> p = btpModuleService.searchBtpModule(btpModule);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	/**
	 * 
	 * @author caixianglong
	 * @date 2012-11-28
	 * @description 删除模块的相关信息
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping("btp/module/moduleInfoRemove")
	public @ResponseBody Map<String, Object> moduleInfoRemove(HttpServletRequest request,HttpServletResponse response){
		String moduleId = request.getParameter("id");
		int result = btpModuleService.removeModuleInfo(moduleId);
		Map<String, Object> jObject = new HashMap<String, Object>();
		boolean bool = false;
		String msg = "";
		if(result == 1){
			bool = true;
			msg = "删除成功";
		}else if(result == 2){
			bool = false;
			msg = "该模块与医疗机构存在关系，不能删除";
		}else if(result == 3){
			bool = false;
			msg = "该模块与角色和医疗机构存在关系，不能删除";
		}else{
			bool = false;
			msg = "该模块与角色存在关系，不能删除";
		}
		jObject.put("success", bool);
		jObject.put("msg", msg);
		return jObject;
	}
	/**
	 * 
	 * @author 蔡祥龙
	 * @date 2012-11-29
	 * @description 模块排序
	 * @param request
	 * @param response
	 * @param btpModule
	 * @return 
	 */
	@RequestMapping("btp/module/moduleOrder")
	public @ResponseBody Map<String, Object> moduleOrder(HttpServletRequest request,HttpServletResponse response,BtpModule btpModule){
		int result = btpModuleService.moduleOrderByorderNo(btpModule);
		Map<String, Object> jObject = new HashMap<String, Object>();
		boolean bool = false;
		if(result>0){
			bool = true;
		}
		jObject.put("success", bool);
		return jObject;
	}
	
	
}
