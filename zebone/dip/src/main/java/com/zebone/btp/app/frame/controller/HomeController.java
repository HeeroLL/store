package com.zebone.btp.app.frame.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.Constant;
import com.zebone.btp.app.frame.service.ShortcutMenuService;
import com.zebone.btp.app.frame.vo.ShortcutMenu;
import com.zebone.btp.app.module.service.BtpModuleService;
import com.zebone.btp.app.module.vo.BtpModule;
import com.zebone.btp.app.role.vo.Role;
import com.zebone.btp.core.web.BaseController;
import com.zebone.btp.security.UserDetails;

/**
 * 平台主页相关Controller
 * 
 * @author 宋俊杰
 * 
 */
@Controller
public class HomeController extends BaseController {
	private static final Log log = LogFactory.getLog(HomeController.class);
	@Resource
	private ShortcutMenuService shortcutMenuService;
	@Resource(name = "btpModuleService")
	private BtpModuleService moduleService;

	/**
	 * 平台首页面
	 * 
	 * @param reqs
	 * @param resp
	 * @return
	 */
	@RequestMapping("home")
	public String home(HttpServletRequest reqs, HttpServletResponse resp) {
		// 得到当前用户信息
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		List<Role> roleList = userDetails.getRoleList();
		List<String> roleIdList = new ArrayList<String>();
		for (Role role : roleList) {
			roleIdList.add(role.getRoleId());
		}
		String loginName = userDetails.getLoginName();
		List<BtpModule> moduleList = new ArrayList<BtpModule>();
		// 如果是超级管理员
		if (loginName.equals(Constant.SUPERADMIN_LOGINNAME)) {
			moduleList = moduleService.getModuleByParentId(Constant.TOP_MODULE_ID);
		} else if (roleList != null && !roleList.isEmpty()) {
			List<String> roleIds = new ArrayList<String>();
			for (Role role : roleList) {
				roleIds.add(role.getRoleId());
			}
			moduleList = moduleService.getModuleByRoleIdAndParentId(roleIds,Constant.TOP_MODULE_ID);
		}
		String personnelId = userDetails.getPersonnelId();
		List<ShortcutMenu> shortcutMenuList = shortcutMenuService.getShortcutMenuByPersonnelId(personnelId);
		reqs.setAttribute("moduleList", moduleList);
		reqs.setAttribute("userDetails", userDetails);
		reqs.setAttribute("shortcutMenuList", shortcutMenuList);
		return "home";
	}
	
	/**
	 * 得到当前用户的快捷方式
	 * @param moduleId
	 * @param orderNo
	 * @return
	 */
	@RequestMapping("addShortcutMenu")
	@ResponseBody
	public Map<String,Object> addShortcutMenu(@RequestParam("moduleId") String moduleId,@RequestParam("orderNo")Integer orderNo){
		// 得到当前用户信息
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject().getPrincipal();
		String personnelId = userDetails.getPersonnelId();
		ShortcutMenu shortcutMenu = new ShortcutMenu();
		shortcutMenu.setModuleId(moduleId);
		shortcutMenu.setPersonnelId(personnelId);
		shortcutMenu.setOrderNo(orderNo);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			this.shortcutMenuService.saveShortcutMenu(shortcutMenu);
			resultMap.put("success", true);
		} catch (Exception e) {
			log.error(" 添加快捷方式出现错误",e);
			resultMap.put("success", false);
			resultMap.put("msg", e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 更新排序号。
	 * @param orders
	 * @return
	 */
	@RequestMapping("upateShortcutMenuOrderNo")
	@ResponseBody
	public Map<String,Object> upateShortcutMenuOrderNo(@RequestParam("orders")String orders){
		// 得到当前用户信息
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject().getPrincipal();
		String personnelId = userDetails.getPersonnelId();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(StringUtils.isEmpty(orders)){
			resultMap.put("success", false);
			return resultMap;
		}
		String[] moduleIdOrderList = orders.split(",");
		try {
			for(String moduleIdOrder : moduleIdOrderList){
				String[] moduleId_Order = moduleIdOrder.split("-");
				String moduleId = moduleId_Order[0];
				String orderNoStr = moduleId_Order[1];
				Integer orderNo = Integer.parseInt(orderNoStr);
				ShortcutMenu shortcutMenu = new ShortcutMenu();
				shortcutMenu.setModuleId(moduleId);
				shortcutMenu.setPersonnelId(personnelId);
				shortcutMenu.setOrderNo(orderNo);
				this.shortcutMenuService.updateOrderNo(shortcutMenu);
			}
		} catch (NumberFormatException e) {
			resultMap.put("success", false);
			resultMap.put("msg", e.getMessage());
			return resultMap;
		}
		resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 删除当前用户的一个快捷方式
	 * @param moduleId
	 * @return
	 */
	@RequestMapping("deleteShortcutMenuOrder")
	@ResponseBody
	public Map<String,Object> deleteShortcutMenuOrder(@RequestParam("moduleId") String moduleId){
		// 得到当前用户信息
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject().getPrincipal();
		String personnelId = userDetails.getPersonnelId();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			this.shortcutMenuService.deleteByModuleIdAndPersonnelId(moduleId, personnelId);
			resultMap.put("success", true);
		} catch (Exception e) {
			log.error("删除快捷方式出现错误",e);
			resultMap.put("success", false);
			resultMap.put("msg", e.getMessage());
		}
		return resultMap;
	}
	

	/**
	 * 根据父模块id得到下面的子模块。对当前用户角色有权限的模块。
	 * 
	 * @param moduleId
	 *            如果参数为null，那么返回的是顶级的模块
	 * @return
	 */
	@RequestMapping("home/getModulesByParentId")
	@ResponseBody
	public List<BtpModule> getModulesByParentId(
			@RequestParam(required = false) String moduleId) {
		// 得到当前用户信息
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		List<Role> roleList = userDetails.getRoleList();
		List<String> roleIdList = new ArrayList<String>();
		for (Role role : roleList) {
			roleIdList.add(role.getRoleId());
		}
		if (StringUtils.isEmpty(moduleId)) {
			moduleId = "1000";
		}
		String loginName = userDetails.getLoginName();
		List<BtpModule> moduleList = new ArrayList<BtpModule>();
		// 如果是超级管理员
		if (loginName.equals(Constant.SUPERADMIN_LOGINNAME)) {
			moduleList = moduleService.getModuleByParentId(moduleId);
		} else if (roleList != null && !roleList.isEmpty()) {
			List<String> roleIds = new ArrayList<String>();
			for (Role role : roleList) {
				roleIds.add(role.getRoleId());
			}
			moduleList = moduleService.getModuleByRoleIdAndParentId(roleIds,
					moduleId);
		}
		return moduleList;
	}
}
