package com.zebone.btp.app.authorize.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.app.authorize.service.AuthorizeService;
import com.zebone.btp.app.authorize.vo.BtpModuleMho;
import com.zebone.btp.app.authorize.vo.BtpRoleModuleR;
import com.zebone.btp.app.authorize.vo.ModuleRoleVo;
import com.zebone.btp.app.mho.service.MhoService;
import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.app.module.service.BtpModuleService;
import com.zebone.btp.app.module.vo.BtpModule;
import com.zebone.btp.core.web.BaseController;
import com.zebone.btp.security.UserDetails;
import com.zebone.util.JsonUtil;
/**
 * 授权管理的控制层
 * @author 蔡祥龙
 * 2012-11-24
 */
@Controller
@Scope(value="prototype")
public class AuthorizeController  extends BaseController{
	@Resource
	private MhoService mhoService;
	@Resource
	private BtpModuleService btpModuleService;
	@Resource
	private AuthorizeService authorizeService;
	/**
	 * 
	 * @author caixianglong
	 * @date 2012-11-29
	 * @description 机构授权
	 * @return String
	 */
	@RequestMapping("btp/authorize/moduleMhoIndex")
	public String moduleMhoIndex(){
		return "btp/authorize/module_mho_index";
	}
	
	/**
	 * 
	 * @author caixianglong
	 * @date 2012-11-29
	 * @description 医疗机构树页面
	 * @return String
	 */
	@RequestMapping("btp/authorize/mhoLeftTree")
	public String moduleMhoLeftTree(HttpServletRequest request,HttpServletResponse response){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		List<Mho> mhoList =  userDetails.getMhoList();
		List<Mho> list = new ArrayList<Mho>();
		String mhoLevelCode = "";
		if(mhoList!=null && mhoList.size()>0){
			String[] mhoLevelCodes = new String[mhoList.size()];
			mhoLevelCode = mhoList.get(0).getLevelCode();
			for(int i=0;i<mhoList.size();i++){
				mhoLevelCodes[i] = mhoList.get(i).getLevelCode();
			}
			list = mhoService.findByMhoIds(mhoLevelCodes);
		}
		String ztreeData = JsonUtil.writeValueAsString(list);
		System.out.println(ztreeData);
		
		request.setAttribute("list", ztreeData);
		request.setAttribute("mhoLevelCode", mhoLevelCode);
		return "btp/authorize/mho_left_tree";
	}
	/**
	 * 
	 * @author caixianglong
	 * @date 2012-11-29
	 * @description 右侧模块树页面
	 * @return String
	 */
	@RequestMapping("btp/authorize/moduleMhoRightTree")
	public String moduleMhoRightTree(HttpServletRequest request,HttpServletResponse response){
		List<BtpModule> list = btpModuleService.getBtpModuleInfos();
		request.setAttribute("list", JsonUtil.writeValueAsString(list));
		return "btp/authorize/module_mho_right_tree";
	}
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-30
	 * @description 角色模块主页面
	 * @return String
	 */
	@RequestMapping("btp/authorize/moduleRoleIndex")
	public String moduleRoleIndex(){
		return "btp/authorize/module_role_index";
	}
	@RequestMapping("btp/authorize/moduleRoleRightTree")
	public String moduleRoleRightTree(HttpServletRequest request){
		return "btp/authorize/module_role_right_tree";
	}
	/**
	 * 
	 * @author 蔡祥龙
	 * @date 2012-11-29
	 * @description 医疗机构功能授权
	 * @param btpModuleMho
	 * @return 
	 */
	@RequestMapping("btp/authorize/moduleMhoSave")
	public @ResponseBody Map<String, Object> moduleMhoSave(HttpServletRequest request,BtpModuleMho btpModuleMho){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		btpModuleMho.setCreatorId(userDetails.getPersonnelId());
		String sId = request.getParameter("sId");
		int result = authorizeService.saveBtpModuleMho(btpModuleMho,sId);
		Map<String, Object> jObject = new HashMap<String, Object>();
		boolean bool = false;
		if(result >0 ) bool = true;
		jObject.put("success", bool);
		return jObject;
	}
	/**
	 * @author caixianglong
	 * @date 2012-11-30
	 * @description 根据医疗机构id加载模块数据
	 * @param request
	 * @param 
	 */
	@RequestMapping("btp/authorize/loadModuleByMhoId")
	public @ResponseBody List<BtpModuleMho> loadModuleByMhoId(HttpServletRequest request){
		String mhoId = request.getParameter("mhoId");
		List<BtpModuleMho> list = authorizeService.getModuleByMhoId(mhoId);
		return list;
	}
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-30
	 * @description 根据医疗机构获取角色和模块相关信息
	 * @param request
	 * @return ModuleRoleDto
	 */
	@RequestMapping("btp/authorize/getModuleRoleByMhoId")
	public @ResponseBody ModuleRoleVo getModuleRoleByMhoId(HttpServletRequest request){
		String mhoId = request.getParameter("mhoId");
		ModuleRoleVo moduleRoleDto = authorizeService.getModuleRoleByMhoId(mhoId);
		return moduleRoleDto;
	}
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-30
	 * @description 根据角色id获取模块信息
	 * @param request
	 * @return List<BtpRoleModuleR>
	 */
	@RequestMapping("btp/authorize/getModuleByRoleId")
	public @ResponseBody List<BtpRoleModuleR> getModuleByRoleId(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		List<BtpRoleModuleR> list = authorizeService.getModuleByRoleId(roleId);
		return list;
	}
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-30
	 * @description 给角色分配权限
	 * @param btpRoleModuleR
	 * @return 
	 */
	@RequestMapping("btp/authorize/saveModuleRole")
	public @ResponseBody Map<String, Object> saveModuleRole(BtpRoleModuleR btpRoleModuleR){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		btpRoleModuleR.setCreatorId(userDetails.getPersonnelId());
		int result = authorizeService.saveBtpRoleModuleR(btpRoleModuleR);
		Map<String, Object> jObject = new HashMap<String, Object>();
		boolean bool = false;
		if(result>0){
			bool = true;
		}
		jObject.put("success", bool);
		return jObject;
	}
}
