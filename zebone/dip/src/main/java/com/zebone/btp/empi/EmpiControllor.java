package com.zebone.btp.empi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.empi.service.EmpiDataService;
import com.zebone.btp.empi.service.EmpiService;
import com.zebone.btp.empi.vo.EmpiCard;
import com.zebone.btp.empi.vo.EmpiConfig;
import com.zebone.btp.empi.vo.EmpiUser;
import com.zebone.btp.empi.vo.ImportData;
import com.zebone.btp.empi.vo.QueryInfo;
import com.zebone.btp.security.UserDetails;

/**
 * 
 * EMPI管理系统
 * 
 * @author ouyangxin
 */
@Controller
@RequestMapping("empi")
public class EmpiControllor {

	public static final String load_fail_message = "加载失败！";
	
	@Autowired
	private EmpiService empiService;

	@Autowired
	private EmpiDataService empiDataService;

	/**
	 * 导向用户管理界面
	 * 
	 * @return
	 */
	@RequestMapping("/goempi")
	public String goEmpi() {
		return "empi/users_index";
	}

	/**
	 * 导向用户管理界面
	 * 
	 * @return
	 */
	@RequestMapping("/goempiIndex")
	public String goEmpiIndex() {
		return "empi/empi_index";
	}

	/**
	 * 导向用户管理界面
	 * 
	 * @return
	 */
	@RequestMapping("/goempiDetail")
	public String goEmpiUserDetail() {
		return "empi/user_detail";
	}

	@RequestMapping("uploadFile")
	public String uploadFile() {
		return "empi/importEmpiData";
	}

	/* ========================================EmpiUser============================================= */
	/**
	 * 获取EmpiUser 列表
	 */
	@RequestMapping("empi-user-list")
	public @ResponseBody
	JsonGrid empiUserList(Pagination<EmpiUser> page, EmpiUser empiUser) {

		page = empiService.queryEmpiUserList(page, empiUser);
		JsonGrid jGrid = new JsonGrid("success", page.getTotalCount(), page
				.getData());
		return jGrid;
	}

	/**
	 * 
	 * EmpiUser操作 新增 更新
	 * 
	 * @param reqs
	 * @param empiUser
	 * @return
	 */
	@RequestMapping("empi-user-operate")
	@ResponseBody
	public Map<String, Object> operateEmpiUser(@ModelAttribute
	EmpiUser empiUser) {

		// 获取登录信息
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		empiUser.setCreator(userDetails.getFullname());

		boolean bool = empiService.operateEmpiUser(empiUser);

		Map<String, Object> map = new HashMap<String, Object>();
		String message = bool ? "操作成功！" : "操作失败！";
		map.put("success", bool);
		map.put("msg", message);
		return map;

	};

	/**
	 * 
	 * EmpiUser删除 支持批量删除
	 * 
	 * @param mdIds
	 * @return
	 */
	@RequestMapping("empi-user-remove")
	@ResponseBody
	public Map<String, Object> removeEmpiUserBatch(@RequestParam("id")
	String ids) {

		QueryInfo info = new QueryInfo();
		info.setId(ids);

		boolean bool = empiService.removeEmpiUser(info);
		Map<String, Object> map = new HashMap<String, Object>();
		String message = bool ? "删除成功！" : "删除失败！";
		map.put("success", bool);
		map.put("msg", message);
		return map;
	}

	/**
	 * 
	 * EmpiUser 加载 通过empiId加载信息
	 * 
	 * @param EmpiUser
	 * @return
	 */
	@RequestMapping("empi-user-load")
	public @ResponseBody
	Map<String, Object> loadEmpiUser(@ModelAttribute
	EmpiUser empiUser) {

		EmpiUser eu = empiService.loadEmpiUser(empiUser);
		boolean bool = (eu == null) ? false : true;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", bool);
		map.put("msg", load_fail_message);
		map.put("object", eu);

		return map;
	}

	
	
	@RequestMapping("empi-user-change-empiid")
	public @ResponseBody
	Map<String, Object> updateEmpiId(@ModelAttribute
	EmpiUser empiUser) {

		boolean bool = empiService.updateEmpiId(empiUser);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", bool);
		map.put("msg", "更新失败");

		return map;
	}
	
	/* ========================================EmpiCard============================================= */
	/**
	 * 获取EmpiCard 列表
	 */
	@RequestMapping("empi-card-list")
	public @ResponseBody
	JsonGrid empiCardList(Pagination<EmpiCard> page, EmpiCard empiCard) {

		page = empiService.queryEmpiCardList(page, empiCard);
		JsonGrid jGrid = new JsonGrid("success", page.getTotalCount(), page
				.getData());
		return jGrid;
	}

	/**
	 * 
	 * EmpiCard操作 新增 更新 注销
	 * 
	 * @param reqs
	 * @param empiCard
	 * @return
	 */
	@RequestMapping("empi-card-operate")
	@ResponseBody
	public Map<String, Object> operateEmpiCard(HttpServletRequest reqs,
			@ModelAttribute
			EmpiCard empiCard) {

		boolean bool = empiService.operateEmpiCard(empiCard);

		Map<String, Object> map = new HashMap<String, Object>();
		String message = bool ? "操作成功！" : "操作失败！检查改卡是否已经注册";
		map.put("success", bool);
		map.put("msg", message);
		return map;

	};

	/**
	 * 
	 * 加载Card
	 * 
	 * @param empiCard
	 * @return
	 */
	@RequestMapping("empi-card-load")
	public @ResponseBody
	Map<String, Object> loadEmpiCard(@ModelAttribute
	EmpiCard empiCard) {

		EmpiCard ec = empiService.loadEmpiCard(empiCard);
		boolean bool = (ec == null) ? false : true;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", bool);
		map.put("msg", load_fail_message);
		map.put("object", ec);

		return map;
	}

	/**
	 * 
	 * EmpiCard删除 支持批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("empi-card-remove")
	@ResponseBody
	public Map<String, Object> removeEmpiCardBatch(@RequestParam("id")
	String ids) {

		QueryInfo info = new QueryInfo();
		info.setId(ids);

		boolean bool = empiService.removeEmpiCard(info);
		Map<String, Object> map = new HashMap<String, Object>();
		String message = bool ? "删除成功！" : "删除失败！";
		map.put("success", bool);
		map.put("msg", message);
		return map;
	}

	/* ========================================EmpiConfig============================================= */
	@RequestMapping("empi-config")
	public @ResponseBody
	Map<String, Object> getEmpiConfig() {
		
		EmpiConfig ec = empiService.getEmpiConfig();
		boolean bool = (ec == null) ? false : true;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", bool);
		map.put("msg", load_fail_message);
		map.put("object", ec);
		
		return map;
	}

	@RequestMapping("empi-config-operate")
	@ResponseBody
	public Map<String, Object> operateEmpiConfig(@ModelAttribute
	EmpiConfig empiConfig) {

		boolean bool = empiService.operateEmpiConfig(empiConfig);

		Map<String, Object> map = new HashMap<String, Object>();
		String message = bool ? "操作成功！" : "操作失败！";
		map.put("success", bool);
		map.put("msg", message);
		return map;

	};

	/* ========================================导入 导出============================================= */
	/**
	 * 进入导入数据页面
	 * 
	 * @param file
	 * @param data
	 * @return
	 */
	@RequestMapping("/uploadEmpiFile")
	public String empiUploadFile() {
		return "empi/empiUpload";
	}
	
	
	/**
	 * 导入数据
	 * 
	 * @param file
	 * @param data
	 * @return
	 */
	@RequestMapping("importEmpiData")
	public String uploadProcess(HttpServletRequest request, @RequestParam("file")
	MultipartFile file, @ModelAttribute
	ImportData data) {

		// 获取登录信息
		UserDetails userDetails = (UserDetails) SecurityUtils.getSubject()
				.getPrincipal();
		data.setLoginName(userDetails.getFullname());

		boolean result = empiDataService.proImportEmpiData(file, data);
		String message = result ? "导入成功" : "导入失败，点击详情";
		request.setAttribute("success", result);
		request.setAttribute("msg", message);
		return "empi/importResponse";
	}

}
