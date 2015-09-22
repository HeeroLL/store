package com.zebone.dip.resources.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.service.DipDictionaryService;
import com.zebone.dip.md.vo.NameCode;
import com.zebone.dip.resources.service.ResourcesManageService;
import com.zebone.dip.resources.vo.MdMedicalOrg;
import com.zebone.dip.resources.vo.ResourceDept;
import com.zebone.dip.resources.vo.ResourceFamily;
import com.zebone.dip.resources.vo.ResourceFamilyMember;
import com.zebone.dip.resources.vo.ResourceUser;
import com.zebone.util.JsonUtil;

/**
 * 资源管理
 * @author LinBin
 *
 */
@RequestMapping("resourcesManage")
@Controller
public class ResourcesManageController {
	
	@Resource
	private ResourcesManageService resourcesManageService;
	@Resource
	private DipDictionaryService dipDictionaryService;

	@RequestMapping("/index")
    public String resourcesManageIndex (HttpServletRequest request, HttpServletResponse response){
		//获取主数据中的医疗机构信息
		List<MdMedicalOrg> list = resourcesManageService.getMasterMedOrg();
		request.setAttribute("treeList", JsonUtil.writeValueAsString(list));
        return "dip/resources/resources_content";
    }
	
	@RequestMapping("/deptInfo")
    public String deptInfo (HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("orgCode", request.getParameter("orgCode"));
        return "dip/resources/deptInfo";
    }
	
	/**
	 * 通过机构名称查询机构
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOrgInfo")
    public Map<String, Object> getOrgInfo (@RequestParam("query") String name){
		List<MdMedicalOrg> list = resourcesManageService.getMasterMedOrgByName(name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("success", true);
		int total =0;
		if(list != null && list.size() > 0){
			total = list.size();
		}
		map.put("query", name);
		map.put("total", total);
		return map;
    }
	
	/**
	 * 分页查询科室信息
	 * @param page
	 * @param nameOrCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/depts")
    public JsonGrid getDeptInfoList (Pagination<ResourceDept> page,ResourceDept dept){
		Pagination<ResourceDept> p = resourcesManageService.getDeptInfoPage(page,dept);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
    }
	
	@RequestMapping("/userInfo")
    public String userInfo (HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("orgCode", request.getParameter("orgCode"));
		List<NameCode> list = dipDictionaryService.getTypeByCode("EXV00.00.068");
		request.setAttribute("list", list);
        return "dip/resources/userInfo";
    }
	
	/**
	 * 分页查询人员信息
	 * @param page
	 * @param nameOrCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/users")
    public JsonGrid getUserInfoList (Pagination<ResourceUser> page,ResourceUser user,String deptName){
		if(deptName!=null&&deptName!=""&&user.getDepartmentCode()==null){
			user.setDepartmentCode(" ");
		}
		Pagination<ResourceUser> p = resourcesManageService.getUserInfoPage(page,user);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
    }
	
	/**
	 * 通过机构和名称查询科室
	 * @param page
	 * @param nameOrCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDeptByOrgCode")
    public Map<String, Object> getDeptByOrgCode (@RequestParam("orgCode")String orgCode,@RequestParam("query") String name){
		List<ResourceDept> objs = resourcesManageService.getDeptByOrgCode(orgCode,name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", objs);
		map.put("success", true);
		int total =0;
		if(objs != null && objs.size() > 0){
			total = objs.size();
		}
		map.put("query", name);
		map.put("total", total);
		return map;
    }
	
	@RequestMapping("/familyInfo")
    public String familyInfo (HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("orgCode", request.getParameter("orgCode"));
        return "dip/resources/familyInfo";
    }
	
	/**
	 * 分页查询家庭信息
	 * @param page
	 * @param nameOrCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/familys")
    public JsonGrid getFamilyInfoList (Pagination<ResourceFamily> page,ResourceFamily family){
		Pagination<ResourceFamily> p = resourcesManageService.getFamilyInfoPage(page,family);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
    }
	
	/**
	 * 家庭档案详细信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/familyEdit")
    public String familyEdit (HttpServletRequest request, HttpServletResponse response){
		String familyId = request.getParameter("id");
		ResourceFamily family = resourcesManageService.getFamilyInfoByFamilyId(familyId);
		request.setAttribute("resourceFamily", family);
        return "dip/resources/familyEdit";
    }
	
	/**
	 * 查询家庭成员信息
	 * @param page
	 * @param nameOrCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/familyMembers")
    public JsonGrid getFamilyMembers (String familyId ){
		List<ResourceFamilyMember> list = resourcesManageService.getFamilyMembers(familyId);
		JsonGrid jGrid = new JsonGrid("true",list.size(),list);
		return jGrid;
    }
	
}
