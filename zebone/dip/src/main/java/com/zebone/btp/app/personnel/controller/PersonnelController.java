package com.zebone.btp.app.personnel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.app.mho.service.MhoService;
import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.app.personnel.service.PersonnelService;
import com.zebone.btp.app.personnel.vo.Personnel;
import com.zebone.btp.app.personnel.vo.PersonnelMhoR;
import com.zebone.btp.app.role.service.RoleService;
import com.zebone.btp.app.role.vo.Role;
import com.zebone.btp.app.role.vo.RoleAccountR;
import com.zebone.btp.core.util.ChineseToPinYin;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.btp.security.UserDetails;
import com.zebone.util.JsonUtil;
import com.zebone.util.UUIDUtil;

/**
 * 医疗工作人员表示层
 * @author LinBin
 * 2012-11-26
 */
@Controller
public class PersonnelController extends BaseController {
	private static Log log = LogFactory.getLog(PersonnelController.class);
	@Resource(name="personnelService")
	private PersonnelService personnelService;
	@Resource
	private MhoService mhoService;
	@Resource
	private RoleService roleService;
	
	/**
	 * 医疗工作人员主页面
	 * @author LinBin
	 * 2012-11-26
	 * @return String
	 */
	@RequestMapping("btp/personnel/personnelIndex")
	public String personnelIndex(){
		return "btp/personnel/personnel_index";
	}
	
	/**
	 * 医疗工作人员页面,左侧医疗机构树
	 * @author LinBin
	 * 2012-11-26
	 * @return String
	 */
	@RequestMapping("btp/personnel/personnelTree")
	public String personnelTree(HttpServletRequest request,HttpServletResponse response){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		List<PersonnelMhoR> list=userDetails.getPersonnelMhoList();
		String[] mhoIds = new String[list.size()];
	    for (int i = 0; i < list.size(); i++) {
	          mhoIds[i]= list.get(i).getMhoId();
	    }
	    List<Mho> mhoList = mhoService.findByMhoIds(mhoIds);
	    request.setAttribute("mhoList", JsonUtil.writeValueAsString(mhoList));
		return "btp/personnel/personnel_tree";
	}
	
	/**
	 * 医疗工作人员页面,右侧展示页面
	 * @author LinBin
	 * 2012-11-26
	 * @return String
	 */
	@RequestMapping("btp/personnel/personnelMain")
	public String personnelRightView(HttpServletRequest request,HttpServletResponse response){
		return "btp/personnel/personnel_main";
	}
	
	/**
	 * 医疗工作人员页面,分页查询
	 * @author LinBin
	 * 2012-11-26
	 * @return String
	 */
	@RequestMapping("btp/personnel/personnelRightPage")
	public @ResponseBody JsonGrid personnelQueryPage(Pagination<PersonnelMhoR> page,PersonnelMhoR personnelMhoR){
		page = personnelService.personnelQueryPage(page, personnelMhoR);
		JsonGrid jGrid = new JsonGrid("true",page.getTotalCount(),page.getData());
		return jGrid;
	}
	
	/**
	 * 医疗工作人员页面,新建修改页面加载
	 * @author LinBin
	 * 2012-11-26
	 * @return String
	 */
	@RequestMapping("btp/personnel/personnelEditViewLoad")
	public @ResponseBody Map<String, Object> personnelEditViewLoad(@RequestParam("personnelId")String personnelId){
		Personnel personnel= new Personnel();
		List<PersonnelMhoR> personnelMhoRs = new ArrayList<PersonnelMhoR>();
		List<Role> roles = new ArrayList<Role>();
		List<Role> otherRoles = new ArrayList<Role>();
		if(StringUtils.isNotEmpty(personnelId)){
			//查询医疗人员基本信息
			personnel= personnelService.getPersonnelInfoOnly(personnelId);
			if(personnel!=null){
				personnel.setOldePassword(personnel.getPassword());
				//查询医疗机构与人员关系
				personnelMhoRs = personnelService.getPersonnelMhoRInfo(personnelId);
				//查询已经拥有的角色
				roles = roleService.getPersonnelRolesById(personnelId);
				if(roles!=null&&roles.size()>0){
					for(Role o : roles){
						o.setName(o.getName()+"("+o.getMhoName()+")");
						o.setRoleId(o.getRoleId()+":"+o.getMedicalOrganId());
					}
				}
				//查询所属医疗机构拥有的所有角色
				List<String> mhoIds = new ArrayList<String>();
				if(personnelMhoRs!=null&&personnelMhoRs.size()>0){
					for(PersonnelMhoR o : personnelMhoRs){
						mhoIds.add(o.getMhoId());
					}
				}
				List<Role> allRoles = roleService.getRoles(mhoIds);
				//找出不属于这个人的其他角色
				if(allRoles!=null&&allRoles.size()>0){
					for(Role allRole : allRoles){
						allRole.setName(allRole.getName()+"("+allRole.getMhoName()+")");
						allRole.setRoleId(allRole.getRoleId()+":"+allRole.getMedicalOrganId());
						int flag = 0;
						for(Role role : roles){
							if(allRole.getRoleId().equals(role.getRoleId())){
								flag = 1;
								break;
							}
						}
						if(flag==0){
							otherRoles.add(allRole);
						}
					}
				}
				
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personnel", personnel);
		map.put("personnelMhoRs", personnelMhoRs);
		map.put("roles", roles);
		map.put("otherRoles", otherRoles);
		return map;
	}
	
	/**
	 * 医疗工作人员页面,新建修改页面保存
	 * @author LinBin
	 * 2012-11-26
	 * @return String
	 */
	@RequestMapping("btp/personnel/personnelEditViewSave")
	public @ResponseBody Map<String, Object> personnelEditViewSave(@ModelAttribute Personnel personnel){
		boolean bool = false;
		String message = "";
		//获取登录信息
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		//处理医疗人员基本信息的数据
		String[] fullAndForShortSpell = ChineseToPinYin.chineseToPinyinAndShort(personnel.getFullname()).split(",");
		if(fullAndForShortSpell!=null&&fullAndForShortSpell.length>1){
			personnel.setFullnameJianpin(fullAndForShortSpell[1]);
		}
		personnel.setFullnamePinyin(fullAndForShortSpell[0]);
		personnel.setCreatorId(userDetails.getPersonnelId());
		personnel.setCreateTime(new Date());
		personnel.setOperatorId(userDetails.getPersonnelId());
		personnel.setUpdateTime(new Date());
//		MD5 md5 = new MD5();
//		personnel.setPassword(md5.getMD5Password(personnel.getPassword()));
		//处理医疗人员与机构关系的数据
		List<PersonnelMhoR> personnelMhoRs = new ArrayList<PersonnelMhoR>();
		String[] mhoIds = personnel.getMhoId();
		String[] departments = personnel.getDepartment();
		String[] personnelTypes = personnel.getPersonnelType();
		String[] deptPersonnelCodes = personnel.getDeptPersonnelCode();
		if(mhoIds!=null&&mhoIds.length>0){
			for(int i = 0;i<mhoIds.length;i++){
				PersonnelMhoR o = new PersonnelMhoR();
				o.setPersonnelId(personnel.getPersonnelId());
				o.setMhoId(mhoIds[i]);
				if(deptPersonnelCodes!=null&&(deptPersonnelCodes.length>0)){
					o.setDeptPersonnelCode(deptPersonnelCodes[i]);
				}
				if(departments!=null && departments.length>0){
					o.setDepartment(departments[i]);
				}
				if(personnelTypes!=null && personnelTypes.length>0){
					o.setPersonnelType(personnelTypes[i]);
				}
				o.setCreatorId(userDetails.getPersonnelId());
				o.setCreateTime(new Date());
				personnelMhoRs.add(o);
			}
		}
		//处理医疗人员与角色关系的数据
		String haveRole = personnel.getHaveRole();
		List<RoleAccountR> roleAccountRs = new ArrayList<RoleAccountR>();
		if(StringUtils.isNotEmpty(haveRole)){
			String [] haveRoles = haveRole.split(",");
			for(String roleId : haveRoles){
				RoleAccountR roleAccountR = new RoleAccountR();
				roleAccountR.setCreatorId(userDetails.getPersonnelId());
				roleAccountR.setCreateTime(new Date());
				roleAccountR.setPersonnelId(personnel.getPersonnelId());
				roleAccountR.setRoleId(roleId.split(":")[0]);
				roleAccountRs.add(roleAccountR);
			}
		}
		//判断新建数据还是更新数据
		if(StringUtils.isEmpty(personnel.getPersonnelId())){
			//保存新建工作人员基本信息
			String id= UUIDUtil.getUuid();
			personnel.setPersonnelId(id);
			for(PersonnelMhoR o : personnelMhoRs){
				o.setPersonnelId(id);
			}
			for(RoleAccountR r : roleAccountRs){
				r.setPersonnelId(id);
			}
			personnel.setPersonnelMhoRs(personnelMhoRs);
			personnel.setRoleAccountRs(roleAccountRs);
			String password = personnel.getPassword();
			if(StringUtils.isEmpty(password)){
				password = "000000";
			}
			String md5Pwd = DigestUtils.md5Hex(password);
			personnel.setPassword(md5Pwd);
			bool = personnelService.savePersonnel(personnel);
		}else{
			//更新修改工作人员基本信息
			personnel.setPersonnelMhoRs(personnelMhoRs);
			personnel.setRoleAccountRs(roleAccountRs);
			//如果密码和原来保持一样，密码字段就不做更新
//			if((personnel.getPassword()).equals(personnel.getOldePassword())){
//				personnel.setPassword(null);
//			}
			/**
			 * 密码由用户自己来修改。 
			 */
			personnel.setPassword(null);
			bool = personnelService.updatePersonnel(personnel);
		}
		Map<String, Object> map = new HashMap<String,Object>();
		if(bool){
			message="保存成功！";
			map.put("bool", bool);
			map.put("msg", message);
			map.put("personnelId", personnel.getPersonnelId());
		}else{
			message="保存失败！";
			map.put("bool", bool);
			map.put("msg", message);
		}
		return map;
	}
	
	/**
	 * 医疗工作人员页面,删除医疗工作人员
	 * @author LinBin
	 * 2012-11-26
	 * @return String
	 */
	@RequestMapping("btp/personnel/personnelDeletePersonnelInfo")
	public @ResponseBody Map<String, Object> deletePersonnelInfo(@RequestParam("id") String personnelIds){
		boolean bool = false;
		String message = "";
		String[] personnelId = personnelIds.split(",");
		List<String> list = new ArrayList<String>(Arrays.asList(personnelId));
		bool = personnelService.deletePersonnel(list);
		if(bool){
			message = "删除成功！";
		}else{
			message = "删除失败！";
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("msg", message);
		map.put("success", bool);
		return map;
	}
	
	/**
	 * 账号停用启用
	 * @author LinBin
	 * 2012-11-26
	 * @return String
	 */
	@RequestMapping("btp/personnel/isAccountEnableModify")
	public @ResponseBody Map<String, Object> isAccountEnableModify(@RequestParam("personnelIds") String personnelIds,@RequestParam("isAccountEnables") String isAccountEnables){
		String message = "";
		boolean bool = false;
		List<Personnel> list = new ArrayList<Personnel>();
		String[] personnelId = personnelIds.split(",");
		String[] isAccountEnable = isAccountEnables.split(",");
		if(personnelId!=null&&personnelId.length>0){
			for(int i = 0;i<personnelId.length;i++){
				Personnel personnel = new Personnel();
				personnel.setPersonnelId(personnelId[i]);
				if("0".equals(isAccountEnable[i])){
					personnel.setIsAccountEnable(1);
				}else{
					personnel.setIsAccountEnable(0);
				}
				list.add(personnel);
			}
		}
		bool = personnelService.isAccountEnableModify(list);
		if(bool){
			message = "操作成功！";
		}else{
			message = "操作失败！";
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("msg", message);
		return map;
	}
	
	/**
	 * 校验账号是否存在
	 * LinBin
	 * Nov 29, 2012
	 * @param loginName
	 * @param personnelId
	 * @return JSONObject
	 */
	@RequestMapping("btp/personnel/checkLoginName")
	public @ResponseBody Map<String, Object> checkLoginName(@RequestParam("loginName") String loginName,@RequestParam("personnelId") String personnelId){
		String message = "";
		boolean bool = false;
		bool = personnelService.checkLoginName(loginName,personnelId);
		if(bool){
			message = "账号已经存在！";
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("msg", message);
		map.put("bool", bool);
		return map;
	}
	
	/**
	 * 初始化密码
	 * LinBin
	 * Nov 30, 2012
	 * @param personnelIds
	 * @return JSONObject
	 */
	@RequestMapping("btp/personnel/passwordInitialization")
	public @ResponseBody Map<String, Object> passwordInitialization(@RequestParam("personnelIds") String personnelIds){
		String message = "";
		boolean bool = false;
		String[] personnelId = personnelIds.split(",");
		List<Personnel> list = new ArrayList<Personnel>();
		if(personnelId!=null&&personnelId.length>0){
			for(int i=0;i<personnelId.length;i++){
				Personnel personnel = new Personnel();
				personnel.setPersonnelId(personnelId[i]);
				personnel.setPassword(DigestUtils.md5Hex("000000"));
				list.add(personnel);
			}
		}
		bool = personnelService.passwordInitialization(list);
		if(bool){
			message = "初始化密码成功！";
		}else{
			message = "初始化密码失败！";
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("msg", message);
		return map;
	}
	
	/**
	 * 根据医疗机构查询角色
	 * LinBin
	 * Dec 7, 2012
	 * @param mhoId
	 * @return
	 */
	@RequestMapping("btp/personnel/getRoles")
	public @ResponseBody Map<String, Object> getRoles(@RequestParam("mhoId") String mhoId){
		List<String> mhoIds = new ArrayList<String>();
		mhoIds.add(mhoId);
		List<Role> list = roleService.getRoles(mhoIds);
		if(list!=null&&list.size()>0){
			for(Role o : list){
				o.setName(o.getName()+"("+o.getMhoName()+")");
				o.setRoleId(o.getRoleId()+":"+o.getMedicalOrganId());
			}
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("roles", list);
		return map;
	}
	/**
	 * 个人信息修改框架页
	 * @author 张春雨
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("btp/personnel/oneselfSetting")
	public String oneselfSetting(HttpServletRequest request,HttpServletResponse response){
		return "btp/personnel/personnel_setting";
	}
	/**
	 * 个人信息修改表单页面
	 * @author 宋俊杰 
	 * @return
	 */
	@RequestMapping("btp/personnel/oneselfModifyForm")
	public String oneselfModifyForm(HttpServletRequest request,HttpServletResponse response){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		Personnel personnel= new Personnel();
		personnel= personnelService.getPersonnelInfoOnly(userDetails.getPersonnelId());
//		personnel= personnelService.getPersonnelInfoOnly("89c0484524204928b1eaac2cab4ad080");
		request.setAttribute("personnel", personnel);
		return "btp/personnel/personnel_oneself_modify";
	}
	
	/**
	 * 个人信息修改表单页面,信息保存
	 * LinBin
	 * Dec 10, 2012
	 * @param personnel
	 * @return
	 */
	@RequestMapping("btp/personnel/oneselfModifySave")
	public @ResponseBody Map<String, Object> oneselfModifySave(@ModelAttribute Personnel personnel){
		boolean bool = false;
		//获取登录信息
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		//处理医疗人员基本信息的数据
		String[] fullAndForShortSpell = ChineseToPinYin.chineseToPinyinAndShort(personnel.getFullname()).split(",");
		if(fullAndForShortSpell!=null&&fullAndForShortSpell.length>1){
			personnel.setFullnameJianpin(fullAndForShortSpell[1]);
		}
		personnel.setFullnamePinyin(fullAndForShortSpell[0]);
		personnel.setOperatorId(userDetails.getPersonnelId());
		personnel.setUpdateTime(new Date());
		bool = personnelService.saveOneselfModify(personnel);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("bool", bool);
		return map;
	}
	
	/**
	 * 修改密码页面
	 * LinBin
	 * Dec 10, 2012
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("btp/personnel/passwordModifyForm")
	public String passwordModifyForm(){
		return "btp/personnel/personnel_password_modify";
	}
	
	/**
	 * 个人密码保存
	 * LinBin
	 * Dec 10, 2012
	 * @param password
	 * @return
	 */
	@RequestMapping("btp/personnel/passwordModifySave")
	public @ResponseBody Map<String, Object> passwordModifySave(@RequestParam("password") String password,@RequestParam("oldePassword") String oldePassword){
		boolean bool = false;
		Map<String, Object> map = new HashMap<String,Object>();
		//获取登录信息
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		//校验旧密码
		Personnel person= new Personnel();
		person= personnelService.getPersonnelInfoOnly(userDetails.getPersonnelId());
//		person= personnelService.getPersonnelInfoOnly("89c0484524204928b1eaac2cab4ad080");
		if(oldePassword.equals(person.getPassword())){
			bool = true;
		}
		if(bool){
			bool = false;
			//更新新密码
			List<Personnel> list = new ArrayList<Personnel>();
			Personnel personnel = new Personnel();
			personnel.setPassword(DigestUtils.md5Hex(password));
			personnel.setPersonnelId(userDetails.getPersonnelId());
//			personnel.setPersonnelId("89c0484524204928b1eaac2cab4ad080");
			list.add(personnel);
			bool = personnelService.passwordInitialization(list);
		}else{
			map.put("msg", "保存失败，原密码错误！");
		}
		map.put("bool", bool);
		return map;
	}
	
	/**
	 * 校验原密码是否正确
	 * LinBin
	 * Dec 11, 2012
	 * @param password
	 * @return
	 */
	@RequestMapping("btp/personnel/checkPassword")
	public @ResponseBody Map<String, Object> checkPassword(@RequestParam("password") String password){
		boolean bool = false;
		//获取登录信息
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		Personnel personnel= new Personnel();
		personnel= personnelService.getPersonnelInfoOnly(userDetails.getPersonnelId());
//		personnel= personnelService.getPersonnelInfoOnly("89c0484524204928b1eaac2cab4ad080");
		if(password.equals(personnel.getPassword())){
			bool = true;
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("bool", bool);
		return map;
	}
	
	/**
	 * 更改用户的桌面皮肤。
	 * @param skin 皮肤名字。
	 * @return
	 */
	@RequestMapping("btp/personnel/changeSkin")
	@ResponseBody
	public Map<String,Object> changeSkin(String skin,HttpServletRequest req){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		String personnelId = userDetails.getPersonnelId();
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			this.personnelService.updatePersonnelSkin(skin, personnelId);
			req.getSession().setAttribute("skin", skin);
			result.put("success", true);
		} catch (Exception e) {
			log.error("",e);
			result.put("success", false);
		}
		return result;
	}
	
}
