package com.zebone.ehrview.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zebone.ehrview.service.HomeService;
import com.zebone.ehrview.service.LifePeriodService;
import com.zebone.ehrview.vo.HealthMenuItem;
import com.zebone.ehrview.vo.LifePeriod;

/**
 * 健康和疾病问题controller
 * @author YinCM
 *
 */
@Controller
public class LifePeriodController {
	
	@Resource
	LifePeriodService lifePeriodService;
	@Resource
	HomeService homeService;
	/**
	 * 健康与疾病问题列表
	 * @param model
	 * @return
	 */
	@RequestMapping("lifePeriodList")
	public String lifePeriodList(HttpServletRequest request, Model model, @RequestParam(value="empiId", required=false) String empiId, @RequestParam(value="period", required=false) String period,@RequestParam(value="periodCode", required=false) String periodCode,
                                 @RequestParam(value = "searchInfo", required = false) String searchInfo,
                                 @RequestParam(value = "patientInfo", required = false) String patientInfo){
		if(empiId==null){
			empiId = "";
			return "LifePeriod/empty";
		}
		//为了在前端显示当前grid所属菜单名称，将code与名称的键值对存入session，如果存在则从session获取，不存在则直接获取
//		Object healthMenuMap = request.getSession().getAttribute("healthMenuMap");
//		Map<String, String> codeNameMap = new HashMap<String, String>();
//		if(healthMenuMap==null){
//			List<HealthMenuItem> healthMenuItemList = homeService.getHealthMenuItems("");
//			for(HealthMenuItem hmi : healthMenuItemList){
//				codeNameMap.put(hmi.getCode(), hmi.getName());
//			}
//			request.getSession().setAttribute("healthMenuMap",codeNameMap);
//		}else{
//			codeNameMap = (Map<String,String>)healthMenuMap;
//		}
		
		List<LifePeriod> lifePeriodList = lifePeriodService.getLifePeriodList(empiId, period);
		model.addAttribute("lifePeriodList",lifePeriodList);
		model.addAttribute("empiId", empiId);
        model.addAttribute("searchInfo", searchInfo);
        model.addAttribute("patientInfo", patientInfo);
		//获取当前所属的阶段
		String periodName="";
		if(periodCode.trim().equals("0")){
			periodName = "婴儿期";
		}else if(periodCode.trim().equals("1")){
			periodName = "幼儿期";
		}else if(periodCode.trim().equals("2")){
			periodName = "学龄前期";
		}else if(periodCode.trim().equals("3")){
			periodName = "学龄期";
		}else if(periodCode.trim().equals("4")){
			periodName = "青春期";
		}else if(periodCode.trim().equals("5")){
			periodName = "青年期";
		}else if(periodCode.trim().equals("6")){
			periodName = "中年期";
		}else if(periodCode.trim().equals("7")){
			periodName = "老年期";
		}
		model.addAttribute("periodName", periodName);
		return "LifePeriod/lifePeriodList";
	}
 
	public LifePeriodService getLifePeriodService() {
		return lifePeriodService;
	}

	public void setLifePeriodService(LifePeriodService lifePeriodService) {
		this.lifePeriodService = lifePeriodService;
	}

	public HomeService getHomeService() {
		return homeService;
	}

	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}
	
}