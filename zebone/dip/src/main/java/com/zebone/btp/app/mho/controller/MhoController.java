/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * dell              New             2012-11-23
 */
package com.zebone.btp.app.mho.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.Constant;
import com.zebone.btp.app.mho.service.MhoService;
import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.app.personnel.service.PersonnelService;
import com.zebone.btp.app.personnel.vo.PersonnelMhoR;
import com.zebone.btp.app.role.service.RoleService;
import com.zebone.btp.app.role.vo.Role;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.btp.security.UserDetails;
import com.zebone.util.JsonUtil;

@Controller
public class MhoController extends BaseController {
	@Autowired
	private MhoService mhoService;
	@Autowired
	private PersonnelService personnelService;
	@Autowired
	private RoleService roleService;

	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-23
	 * @description 醫療機構左邊機構樹
	 * @param request
	 * @param response
	 * @return String
	 */

	@RequestMapping("btp/mho/index")
	public String getMhoIndex(HttpServletRequest request,
			HttpServletResponse response) {
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		List<PersonnelMhoR> list = userDetails.getPersonnelMhoList();
		String[] mhoIds = new String[list.size()];
		for (int n = 0; n < list.size(); n++) {
			mhoIds[n] = list.get(n).getMhoId();
			request.setAttribute("mhoId", list.get(n).getMhoId());
		}
		return "btp/mho/mho_index";
	}

	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-27
	 * @description 返回机构树页面
	 * @param request
	 * @param response
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("btp/mho/mhoTree")
	public String mhoTree(HttpServletRequest request,
			HttpServletResponse response) {
		String[] mhoId = request.getParameterValues("mhoId");
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		Set<Mho> set = new HashSet<Mho>();
		if (mhoId == null || mhoId[0] == "") {
			List<PersonnelMhoR> list = userDetails.getPersonnelMhoList();
			String[] mhoIds = new String[list.size()];
			for (int n = 0; n < list.size(); n++) {
				mhoIds[n] = list.get(n).getMhoId();
				for (int j = 0; j < mhoIds.length; j++) {
					String levelCode = mhoService.findLevelCodeByMid(mhoIds[j]);
					List<Mho> mhoList1 = mhoService.findAllMhoInfo(levelCode);
					for (Mho o : mhoList1) {
						set.add(o);
					}
					request.setAttribute("levelCode", levelCode);
				}
				List<Mho> mhoList = new ArrayList<Mho>(set);
				String ztreeData = JsonUtil.writeValueAsString(mhoList);
				System.out.println(ztreeData);
				request.setAttribute("mhoList", ztreeData);
			}
		} else {
			for (int i = 0; i < mhoId.length; i++) {
				String levelCode = mhoService.findLevelCodeByMid(mhoId[i]);
				List<Mho> mhoList1 = mhoService.findAllMhoInfo(levelCode);
				for (Mho o : mhoList1) {
					set.add(o);
				}
				request.setAttribute("levelCode", levelCode);
			}
			List<Mho> mhoList = new ArrayList<Mho>(set);
			String ztreeData = JsonUtil.writeValueAsString(mhoList);
			System.out.println(ztreeData);
			request.setAttribute("mhoList", ztreeData);
		}

		return "btp/mho/mho_tree";
	}

	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-30
	 * @description 树的控件
	 * @param request
	 * @param response
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("btp/mho/mhoInnerTree")
	public String mhoInnerTree(HttpServletRequest request,
			HttpServletResponse response) {
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		Set<Mho> set = new HashSet<Mho>();
		String[] mhoId = request.getParameterValues("mhoId");
		String type = request.getParameter("type");
		if (mhoId == null || mhoId[0] == "") {
			List<PersonnelMhoR> list = userDetails.getPersonnelMhoList();
			String[] mhoIds = new String[list.size()];
			for (int n = 0; n < list.size(); n++) {
				mhoIds[n] = list.get(n).getMhoId();
				for (int j = 0; j < mhoIds.length; j++) {
					String levelCode = mhoService.findLevelCodeByMid(mhoIds[j]);
					List<Mho> mhoList1 = mhoService.findAllMhoInfo(levelCode);
					for (Mho o : mhoList1) {
						set.add(o);
					}
					request.setAttribute("levelCode", levelCode);
				}
				List<Mho> mhoList = new ArrayList<Mho>(set);
				String ztreeData = JsonUtil.writeValueAsString(mhoList);
				System.out.println(ztreeData);
				request.setAttribute("ztree", ztreeData);
				request.setAttribute("type", type);
			}
		} else {
			for (int i = 0; i < mhoId.length; i++) {
				String levelCode = mhoService.findLevelCodeByMid(mhoId[i]);
				List<Mho> mhoList1 = mhoService.findAllMhoInfo(levelCode);
				for (Mho o : mhoList1) {
					set.add(o);
				}
				request.setAttribute("levelCode", levelCode);

			}
			List<Mho> mhoList = new ArrayList<Mho>(set);
			String ztreeData = JsonUtil.writeValueAsString(mhoList);
			System.out.println(ztreeData);
			request.setAttribute("ztree", ztreeData);
			request.setAttribute("type", type);
		}

		return "btp/mho/mho_inner_tree";
	}

	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-30
	 * @description 弹出的修改页面
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping("btp/mho/mhoEdit")
	public String mhoEdit(HttpServletRequest request,
			HttpServletResponse response) {
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		String parentId = request.getParameter("mhoId");
		String mhoId = request.getParameter("mhoId2");
		// String levelCode=request.getParameter("levelCode");
		Mho mho = mhoService.findById(mhoId);

		List<Mho> listm = userDetails.getMhoList();
		for (int i = 0; i < listm.size(); i++) {
			if (parentId.equals("undefined")) {
				request.setAttribute("parentId", listm.get(i).getMhoId());
			} else {
				request.setAttribute("parentId", parentId);
			}
		}
		request.setAttribute("mho", mho);
		request.setAttribute("mhoId", mhoId);
		// request.setAttribute("levelCode", levelCode);
		return "btp/mho/mho_edit";
	}

	/**
	 * 
	 * @author 范雄磊
	 * @date 2012-11-23
	 * @description 醫療機構右邊頁面
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping("btp/mho/mhoMain")
	public String mho(HttpServletRequest request, HttpServletResponse response) {
		return "btp/mho/mho";
	}

	/**
	 * 
	 * @author fxl
	 * @date 2012-11-24
	 * @description 保存和修改
	 * @param request
	 * @param response
	 *            void
	 */
	@RequestMapping("btp/mho/mhoSave")
	public @ResponseBody
	Map<String, Object> saveMho(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("mho")
			Mho mho) {
		boolean bool = true;
		String message = "";
		boolean b = true;
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		Mho mho2 = new Mho();
		String levelCode3 = mhoService.findLevelCodeByMhoId(mho.getParentId());
		String mhoName = mhoService.findMhoName(mho.getMhoName());

		if (mho.getMhoId() == null || mho.getMhoId().equals("null")) {
			if (StringUtils.isNotEmpty(mhoName)) {
				message = "机构名称已存在，请重新输入";
				b = false;
			} else {
				List<Mho> list = mhoService.findLevelCodeByPid(mho
						.getParentId());
				if (null != list && list.size() > 0) {
					String levelCode = mhoService.findMaxLevelCode(mho
							.getParentId());
					String levelCode2 = String
							.valueOf(Long.valueOf(levelCode) + 1);
					mho2.setLevelCode(levelCode2);
					mho2.setMhoCode(levelCode3);
				} else {

					mho2.setMhoCode(levelCode3);
					mho2.setLevelCode(levelCode3
							+ Constant.ORGAN_INCREMENT_VALUE);

				}

				mho2.setMhoId(UUID.randomUUID().toString().replace("-", ""));
				mho2.setMhoName(mho.getMhoName());
				mho2.setParentId(mho.getParentId());
				mho2.setAddress(mho.getAddress());
				mho2.setPhone(mho.getPhone());
				mho2.setTypeCode(mho.getTypeCode());
				mho2.setRemark(mho.getRemark());
				mho2.setIsNcms(mho.getIsNcms());
				mho2.setManager(mho.getManager());
				mho2.setHospitalNature(mho.getHospitalNature());
				mho2.setHospitalGrade(mho.getHospitalGrade());
				mho2.setHospitalType(mho.getHospitalType());
				mho2.setIsDesignatedHospital(mho.getIsDesignatedHospital());
				mho2.setIsDeleted((short) 0);
				mho2.setCreatorId(userDetails.getPersonnelId());
				mho2.setCreateTime(new Date());
				mho2.setArea(mho.getArea());
				mho2.setWebsite(mho.getWebsite());
				mhoService.insert(mho2);
				bool = true;
				b = true;
			}
		} else {
			String levelCode = mhoService.findLevelCodeByMhoId(mho.getMhoId());
			mho.setLevelCode(levelCode);
			mho.setOperatorId(userDetails.getPersonnelId());
			mho.setUpdateTime(new Date());
			mhoService.updateById(mho);
			bool = true;
			b = true;

		}

		Map<String, Object> map = new HashMap<String, Object>();
		if (bool == true && b == true) {
			message = "保存成功！";
			map.put("bool", bool);
			map.put("b", b);
			map.put("msg", message);
		} else if (bool == false && b == false) {
			message = "保存失败！";
			map.put("bool", bool);
			map.put("b", b);
			map.put("msg", message);
		} else if (b == false) {
			message = "机构名称已存在，请重新输入";
			map.put("b", b);
			map.put("msg", message);
		}
		return map;

	}

	/**
	 * 
	 * @author fxl
	 * @date 2012-11-24
	 * @description 删除操作
	 * @param request
	 * @param response
	 *            void
	 */
	@RequestMapping("btp/mho/mhoRemove")
	public @ResponseBody
	Map<String, Object> removeMho(HttpServletRequest request,
			HttpServletResponse response) {
		String mhoId = request.getParameter("id");
		//List<PersonnelMhoR> listp = personnelService.findPersonByMhoId(mhoId);
		String levelCode=mhoService.findLevelCodeByMhoId(mhoId);
		List<PersonnelMhoR> listp=personnelService.getPersonList(levelCode);
		List<Role> listr = roleService.getRoleList(levelCode);
		boolean bool = false;
		if ((listp != null && listp.size() > 0)|| (listr != null && listr.size() > 0)) {
			bool = false;
		} else {
			List<Mho> list = mhoService.findMhoByParentId(mhoId);
			if (list != null && list.size() > 0) {
				mhoService.deleteById(mhoId);
				for (int i = 0; i < list.size(); i++) {
					int result = mhoService.deleteById(list.get(i).getMhoId());
					if (result > 0) {
						// 数据删除成功
						bool = true;
					}
				}

			} else {
				int result = mhoService.deleteById(mhoId);
				if (result > 0) {
					// 数据删除成功
					bool = true;
				}

			}
		
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", bool);
		return map;

	}

	/**
	 * 
	 * @author 范熊磊
	 * @date 2012-11-24
	 * @description 分页显示机构列表
	 * @param request
	 * @param response
	 *            void
	 */
	@RequestMapping("btp/mho/mhoList")
	public @ResponseBody
	JsonGrid getMho(HttpServletRequest request, Pagination<Mho> page, Mho mho) {
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		if (StringUtils.isEmpty(mho.getMhoId())) {
			List<Mho> list = userDetails.getMhoList();
			for (int i = 0; i < list.size(); i++) {
				mho.setMhoId(list.get(i).getMhoId());
			}
		}
		Pagination<Mho> pagination = mhoService.getPagination(page, mho);
		JsonGrid jGrid = new JsonGrid("true", pagination.getTotalCount(),
				pagination.getData());
		return jGrid;

	}

}
