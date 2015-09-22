package com.zebone.btp.app.role.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.Constant;
import com.zebone.btp.app.mho.service.MhoService;
import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.app.personnel.service.PersonnelService;
import com.zebone.btp.app.personnel.vo.Personnel;
import com.zebone.btp.app.personnel.vo.PersonnelMhoR;
import com.zebone.btp.app.role.service.RoleAccountService;
import com.zebone.btp.app.role.service.RoleService;
import com.zebone.btp.app.role.vo.Role;
import com.zebone.btp.app.role.vo.RoleAccountR;
import com.zebone.btp.core.util.ChineseToPinYin;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.btp.security.UserDetails;

/**
 * 
 * @author 杨柳
 * 2012-11-23
 */
@Controller
public class RoleController extends BaseController{
	
	@Resource
	private RoleService roleService;
	@Resource
	private PersonnelService personnelService;
	@Resource
	private RoleAccountService roleAccountService;
	@Resource
	private MhoService mhoService;
	
	
	int result = 0;
	String msg = null;
	boolean resultMsg = false;
	
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	
	/**
	 * 角色首页面
	 * @param request
	 * @param response
	 * @return role_index.jsp
	 */
	@RequestMapping("btp/role/roleIndex")
	public String roleIndex(HttpServletRequest request , HttpServletResponse response){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		List<PersonnelMhoR> list=personnelService.getPersonnelMhoRInfo(userDetails.getPersonnelId());
         for (int i = 0; i < list.size(); i++) {
        	 request.setAttribute("mhoId", list.get(i).getMhoId());
		 }
		return "btp/role/role_index";
	}
	/**
	 * 加载角色首页面右边的 角色主页面
	 * @return 角色主页面
	 */
	@RequestMapping("btp/role/btpRoleMain")
	public String btpRoleMain(){
		return "btp/role/role_main";
	}
	/**
	 * 用于新增和修改页面
	 * @param request
	 * @param role
	 * @return
	 */
	@RequestMapping("btp/role/goUpdateAndAddRole")
	public String goUpdateAndAddRole(HttpServletRequest request ,Role role){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		//List<PersonnelMhoR> list=personnelService.getPersonnelMhoRInfo(userDetails.getPersonnelId());
		List<Mho> list=userDetails.getMhoList();
        for (int i = 0; i < list.size(); i++) {
        	String mhoId = list.get(i).getMhoId();
        	String mhoName = list.get(i).getMhoName();
        	role.setTemp(mhoName);
        	role.setMedicalOrganId(mhoId);
        	request.setAttribute("mhoId", mhoId);
        }
		String loginName = userDetails.getLoginName();
		if(loginName.equals(Constant.SUPERADMIN_LOGINNAME)){
			request.setAttribute("loginName", loginName);
		}
		//属于前往更新页面
		if(!StringUtils.isEmpty(role.getRoleId())){
			role = roleService.findById(role.getRoleId());
		}
		request.setAttribute("data", role);
		return "btp/role/role_update";
	} 
	/**
	 * 新增或修改角色
	 * @param request
	 * @param role
	 */
	@RequestMapping("btp/role/updateAndSaveRole")
	public @ResponseBody Map<String,Object> updateAndSaveRole(HttpServletRequest request ,Role role){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		//roleId != null 属于更新操作
		if(!StringUtils.isEmpty(role.getRoleId())){  
			role.setUpdateTime(new Date());//设置最后修改时间
			role.setOperatorId(userDetails.getPersonnelId());//设置操作人ID
			if(!userDetails.getLoginName().equals(Constant.SUPERADMIN_LOGINNAME)){
				role.setIsPublicRole((short)0);//如果不是超级管理员登录，则默认设置【公共角色】为0
			}
			result = roleService.updateById(role);
			if(result > 0){
				msg = "更新角色成功";
			}else{
				msg = "更新角色失败";
			}
			map.put("msg", msg);
			return map; 
		//属于新增操作
		}else{
			if(role.getMedicalOrganId() != null && !"".equals(role.getMedicalOrganId())){
				String[] array = role.getMedicalOrganId().split("-");//因为新建一个角色，分配到多个机构下，所以进行分割成数组
				for(int i=0;i<array.length;i++){//遍历每一个医疗机构ID，循环保存到数据库中去
					role.setMedicalOrganId(array[i]);//设置角色所在医疗机构
					role.setRoleId(UUID.randomUUID().toString().replace("-", ""));//设置角色ID
					role.setNameSpell(ChineseToPinYin.chineseToPinyin(role.getName()));//设置角色名称拼音
					role.setCreatorId(userDetails.getPersonnelId()); //设置角色创建者ID
					role.setCreateTime(new Date());//设置角色创建时间
					role.setIsDelete((short)0);//设置删除标志 默认是0
					if(!userDetails.getLoginName().equals(Constant.SUPERADMIN_LOGINNAME)){
						role.setIsPublicRole((short)0);//如果不是超级管理员登录，则默认设置【公共角色】为0
					}
					result = roleService.insert(role);
				}
				if(result > 0){
					msg = "新增角色成功";
				}else{
					msg = "新增角色失败";
				}
			}
			map.put("msg", msg);
			return map;
		}
	}
	/**
	 * 检查角色名称是否唯一
	 * @param request
	 * @param role
	 */
	@RequestMapping("btp/role/roleCheckName")
	public @ResponseBody Map<String,Object> roleCheckName(HttpServletRequest request ,Role role){
		//roleId != null 代表属于更新时的角色名称检查唯一性
//		if(role.getRoleId() != null){
//			//当前用户可能对应多个医疗机构ID,所以进行for遍历
//			for(int i=0;i<userDetails.getOrganIds().size();i++){
//				
//			}
//		}
		return map;
	}
	/**
	 * 角色分页查询
	 * @param role
	 * @param page 分页对象
	 */
	@RequestMapping("btp/role/roleQueryPage")
	public @ResponseBody JsonGrid roleQueryPage(Role role,Pagination<Role> page){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		if(role.getMedicalOrganId() == null || ("").equals(role.getMedicalOrganId())){
			role.setMedicalOrganId(userDetails.getLevelCode());
		}
		page = roleService.pageRole(page.getRowBounds(), role.getMedicalOrganId());
		JsonGrid jsonGrid = new JsonGrid("success",page.getTotalCount(),page.getData());//调用 JSONGrid的构造方法，对分页数据进行在处理
		return jsonGrid ;
	}
	/**
	 * 角色的逻辑删除
	 * @param roleId
	 * @return jobject
	 */
	@RequestMapping("btp/role/removeRole")
	public @ResponseBody Map<String,Object> removeRole(@RequestParam("id") String roleId){
		result = roleService.logicDelete(roleId);//根据角色ID，逻辑删除
		if(result > 0){
			result = roleAccountService.deleteByRoleId(roleId);//删除当前角色所分给的工作人员，
			resultMsg = true;//数据删除成功
			msg = "角色删除成功";
		}
		map.put("success", resultMsg);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 角色分配页面
	 * @return role_allot.jsp
	 */
	@RequestMapping("btp/role/roleAllot")
	public String roleAllot(){
		return "btp/role/role_allot";
	}
	/**
	 * 角色分配右边页面
	 * @return role_allot_right.jsp
	 */
	@RequestMapping("btp/role/roleAllotRight")
	public String roleAllotRight(){
		return "btp/role/role_allot_right";
	}
	/**
	 * 前往角色分配页面
	 * @param request
	 * @param id 工作人员ID
	 * @param organId 医疗机构ID
	 * @return role_allot_update.jsp
	 */
	@RequestMapping("btp/role/goAllotRole")
	public String goAllotRole(HttpServletRequest request,@RequestParam("id") String id,@RequestParam("organId") String organId){
		Role role = new Role();//声明一个角色对象
		role.setMedicalOrganId(organId);//把机构ID放到role对象中去
		List<Role> roleList = roleService.SearchRoleByRole(role);//存放当前机构下所拥有的所有角色
		List<Role> roleOwnerList = new ArrayList<Role>();//存放当前工作人员拥有的角色
		List<RoleAccountR> roleAccountList = roleAccountService.findById(id);//查询角色与工作人员的关系表；根据工作人员id找到所有的信息
		for(int j=0;j<roleAccountList.size();j++){//如果，有信息，进行for遍历
			roleOwnerList.add(roleService.findById(roleAccountList.get(j).getRoleId()));//根据角色ID查询所有角色信息，然后放到roleOwnerList，作为 拥有的角色
		}
		List<Role> roleNoList = new  ArrayList<Role>();//存放当前工作人员没有的角色
		if(roleOwnerList.size() > 0){//如果当前工作人员所拥有的角色不为空，继续向下执行
			for(int i=0;i<roleList.size();i++){
				if(roleOwnerList.contains(roleList.get(i))==false){
					roleNoList.add(roleList.get(i));
				}
			}
		}
		else{//如果当前人员所拥有的角色为空
			if(roleList.size() > 0){
				for(int n=0;n<roleList.size();n++){
					roleNoList.add(roleList.get(n));//如果当前工作人员所拥有的角色为空，则设置 所有角色，都为 此工作人员所没有的角色
				}
			}
			
		}
		//获取工作人员信息
		Personnel person = personnelService.getPersonnelInfoOnly(id);
		request.setAttribute("person", person);
		request.setAttribute("roleOwnerList", roleOwnerList);
		request.setAttribute("roleNoList",roleNoList);
		return "btp/role/role_allot_update";
	}
	/**
	 * 保存角色分配信息
	 * @param ownerRoleIds 角色ID(多个)
	 * @param personId 工作人员ID
	 * @return map
	 */
	@RequestMapping("btp/role/roleAllotSave")
	public @ResponseBody Map<String,Object> roleAllotSave(@RequestParam("ownerRoleIds") String ownerRoleIds,@RequestParam("personId") String personId){ 
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		RoleAccountR roleAccount = new RoleAccountR();
		result = roleAccountService.deleteById(personId);//删除当前用户所拥有的角色
		if(ownerRoleIds != null && !ownerRoleIds.equals("") && !ownerRoleIds.equals("undefined")){ 
			String[] ids = ownerRoleIds.split("-");
			for(int i=0;i<ids.length;i++){
				roleAccount.setRoleId(ids[i]);
				roleAccount.setPersonnelId(personId);
				roleAccount.setCreateTime(new Date());
				roleAccount.setCreatorId(userDetails.getPersonnelId());
				result = roleAccountService.insert(roleAccount);
			}
		}
		msg = "角色分配成功";
		map.put("msg", msg);
		return map;
	}
}
